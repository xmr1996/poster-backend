package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.ProfileDao;
import edu.uwm.capstone.model.profile.Profile;
import edu.uwm.capstone.sql.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static edu.uwm.capstone.util.TestDataUtility.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
 * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileRestControllerUnitTest {

    @Mock
    private ProfileDao profileDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ProfileRestController profileRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void create() throws IOException {
        Profile profileToCreate = profileWithTestValues();
        when(profileDao.create(any(Profile.class))).thenReturn(profileToCreate);

        Profile createdProfile = profileRestController.create(profileToCreate, response);
        assertEquals(profileToCreate, createdProfile);
    }

    @Test
    public void createNull() throws IOException {
        profileRestController.create(null, response);

        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Received null Profile object");
    }

    @Test
    public void createPreconditionFailed() throws IOException {
        Profile profileToCreate = profileWithTestValues();
        profileToCreate.setId(randomLong());
        profileRestController.create(profileToCreate, response);

        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Profile ID must be null");
    }

    @Test
    public void createException() throws IOException {
        Profile profileToCreate = profileWithTestValues();
        when(profileDao.create(any(Profile.class))).thenThrow(new DaoException(TEST_ERROR_MESSAGE));

        profileRestController.create(profileToCreate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }

    @Test
    public void update() throws IOException {
        Profile profileToUpdate = profileWithTestValues();
        profileToUpdate.setId(randomLong());
        when(profileDao.read(anyLong())).thenReturn(profileToUpdate);

        profileRestController.update(profileToUpdate, response);
        verify(profileDao, times(1)).update(profileToUpdate);
    }

    @Test
    public void updateException() throws IOException {
        Profile profileToUpdate = profileWithTestValues();
        profileToUpdate.setId(randomLong());
        when(profileDao.read(anyLong())).thenReturn(profileToUpdate);
        doThrow(new DaoException(TEST_ERROR_MESSAGE)).when(profileDao).update(any(Profile.class));

        profileRestController.update(profileToUpdate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }

    @Test
    public void updatePreconditionFailed() throws IOException {
        profileRestController.update(profileWithTestValues(), response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Profile Id must not be null");
    }

    @Test
    public void readById() throws IOException {
        Profile profile = profileWithTestValues();
        profile.setId(randomLong());
        when(profileDao.read(anyLong())).thenReturn(profile);

        Profile receivedProfile = profileRestController.readById(profile.getId(), response);
        assertEquals(profile, receivedProfile);
    }

    @Test
    public void readByIdNotFound() throws IOException {
        Long profileId = randomLong();
        when(profileDao.read(anyLong())).thenReturn(null);

        profileRestController.readById(profileId, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_NOT_FOUND, "Profile with ID: " + profileId + " not found.");
    }

    @Test
    public void delete() throws IOException {
        Long profileId = randomLong();
        profileRestController.delete(profileId, response);

        verify(profileDao, times(1)).delete(profileId);
    }

    @Test
    public void deleteIdNotFound() throws IOException {
        Long profileId = randomLong();
        doThrow(new DaoException(TEST_ERROR_MESSAGE)).when(profileDao).delete(anyLong());

        profileRestController.delete(profileId, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_NOT_FOUND, TEST_ERROR_MESSAGE);
    }

}
