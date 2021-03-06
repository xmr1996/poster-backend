package edu.uwm.capstone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uwm.capstone.Application;
import edu.uwm.capstone.db.ProfileDao;
import edu.uwm.capstone.model.profile.Profile;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static edu.uwm.capstone.util.TestDataUtility.profileWithTestValues;
import static edu.uwm.capstone.util.TestDataUtility.randomLong;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * This test class exercises the spring boot based {@link Application} running in memory to verify that
 * the REST endpoints provided by the {@link ProfileRestController} are working correctly.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProfileRestControllerComponentTest {

    private static final Logger logger = LoggerFactory.getLogger(ProfileRestController.class);

    @Value("${local.server.port}")
    private int port;

    @Value("${server.servlet.context-path}")
    private String basePath;

    @Autowired
    private ProfileDao profileDao;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Profile> profilesToCleanup = new ArrayList<>();

    @Before
    public void setUp() {
        assertNotNull(basePath);
        assertNotNull(profileDao);

        RestAssured.port = port;
        RestAssured.basePath = basePath;
    }

    @After
    public void tearDown() {
        profilesToCleanup.forEach(cleanUpWrapper(profile -> profileDao.delete(profile.getId())));
        profilesToCleanup.clear();
    }

    @Test
    public void create() throws JsonProcessingException {
        Profile profileToCreate = profileWithTestValues();

        // exercise endpoint
        ExtractableResponse<Response> response = given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(mapper.writeValueAsString(profileToCreate))
                .when()
                .post(ProfileRestController.PROFILE_PATH)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.OK.value()).extract();

        Profile receivedProfile = response.body().as(Profile.class);

        //if Id is populated - record created successfully
        assertNotNull(receivedProfile.getId());
        profilesToCleanup.add(receivedProfile);

        assertNotNull(receivedProfile.getCreatedDate());
        assertEquals(profileDao.read(receivedProfile.getId()), receivedProfile);
    }

    @Test
    public void createPreconditionFailed() throws JsonProcessingException {
        Profile profile = profileWithTestValues();
        profile.setId(randomLong());

        // exercise endpoint
        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(mapper.writeValueAsString(profile))
                .when()
                .post(ProfileRestController.PROFILE_PATH)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.PRECONDITION_FAILED.value()).body("message", equalTo("Profile ID must be null"));
    }

    @Test
    public void update() throws JsonProcessingException {
        Profile profile = profileWithTestValues();
        profilesToCleanup.add(profileDao.create(profile));

        Profile profileToUpdate = profileWithTestValues();
        profileToUpdate.setId(profile.getId());

        // exercise endpoint
        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(mapper.writeValueAsString(profileToUpdate))
                .when()
                .put(ProfileRestController.PROFILE_PATH)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.OK.value()).extract();

        Profile verifyProfile = profileDao.read(profile.getId());
        assertNotNull(verifyProfile.getUpdatedDate());
        assertEquals(profileToUpdate.getId(), verifyProfile.getId());
    }

    @Test
    public void updatePreconditionFailed() throws JsonProcessingException {
        Profile profileToUpdate = profileWithTestValues();
        profileToUpdate.setId(randomLong());

        // exercise endpoint
        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(mapper.writeValueAsString(profileToUpdate))
                .when()
                .put(ProfileRestController.PROFILE_PATH)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.PRECONDITION_FAILED.value()).body("message", equalTo("Could not update Profile " + profileToUpdate.getId() + " - record not found."));
    }

    @Test
    public void readById() {
        Profile profile = profileWithTestValues();
        profilesToCleanup.add(profileDao.create(profile));

        // exercise endpoint
        ExtractableResponse<Response> response = given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .get(ProfileRestController.PROFILE_PATH + profile.getId())
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.OK.value()).extract();

        Profile receivedProfile = response.body().as(Profile.class);
        assertEquals(profile, receivedProfile);
    }

    @Test
    public void readByIdNotFound() {
        Long profileId = randomLong();

        // exercise endpoint
        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .get(ProfileRestController.PROFILE_PATH + profileId)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.NOT_FOUND.value()).body("message", equalTo("Profile with ID: " + profileId + " not found."));
    }

    @Test
    public void delete() {
        Profile profile = profileWithTestValues();
        //in case test fails - record should still get cleaned up
        profilesToCleanup.add(profileDao.create(profile));

        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .delete(ProfileRestController.PROFILE_PATH + profile.getId())
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.OK.value());

        assertNull(profileDao.read(profile.getId()));
    }

    @Test
    public void deleteIdNotFound() {
        Long profileId = randomLong();

        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .delete(ProfileRestController.PROFILE_PATH + profileId)
                .then().log().ifValidationFails()
                .statusCode(HttpStatus.NOT_FOUND.value()).body("message", equalTo("Failed attempt to delete profile " + profileId + " affected 0 rows"));
    }

    //lambda wrapper designed to handle exceptions thrown from lambda function
    private static Consumer<Profile> cleanUpWrapper(Consumer<Profile> consumer) {
        return profile -> {
            try {
                consumer.accept(profile);
            } catch (RuntimeException e) {
                logger.error(e.getMessage(), e);
            }
        };
    }
}
