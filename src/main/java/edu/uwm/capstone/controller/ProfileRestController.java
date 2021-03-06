package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.ProfileDao;
import edu.uwm.capstone.model.profile.Profile;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ProfileRestController {

    public static final String PROFILE_PATH = "/profile/";

    private static final Logger logger = LoggerFactory.getLogger(ProfileRestController.class);
    private final ProfileDao profileDao;

    @Autowired
    public ProfileRestController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    /**
     * Creates the provided {@link Profile}
     *
     * @param profile  {@link Profile}
     * @param response {@link HttpServletResponse}
     * @return {@link Profile}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Profile")
    @PostMapping(value = PROFILE_PATH)
    public Profile create(@RequestBody Profile profile, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(profile, "Received null Profile object");
            Assert.isNull(profile.getId(), "Profile ID must be null");
            return profileDao.create(profile);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return null;
        }
    }

    /**
     * Updates the provided {@link Profile}
     *
     * @param profile  {@link Profile}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Update Profile")
    @PutMapping(value = PROFILE_PATH)
    public void update(@RequestBody Profile profile, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(profile.getId(), "Profile Id must not be null");
            Assert.notNull(profileDao.read(profile.getId()), "Could not update Profile " + profile.getId() + " - record not found.");
            profileDao.update(profile);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get the {@link Profile} by Id
     *
     * @param profileId {@link Profile#getId()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Profile} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Profile by ID")
    @GetMapping(value = PROFILE_PATH + "{profileId}")
    public Profile readById(@PathVariable Long profileId, @ApiIgnore HttpServletResponse response) throws IOException {
        Profile profile = profileDao.read(profileId);

        if (profile == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Profile with ID: " + profileId + " not found.");
            return null;
        }

        return profile;
    }


    /**
     * Get the {@link Profile}
     *
     * @return {@link List<Profile>} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read All Profiles")
    @GetMapping(value = PROFILE_PATH)
    public List<Profile> read(@ApiIgnore HttpServletResponse response) throws IOException {
        List<Profile> profile = profileDao.read();

        if (profile == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Profiles Were Found.");
            return null;
        }

        return profile;
    }

    /**
     * Delete the {@link Profile} by Id
     *
     * @param profileId {@link Profile#getId()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Profile by ID")
    @DeleteMapping(value = PROFILE_PATH + "{profileId}")
    public void delete(@PathVariable Long profileId, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            profileDao.delete(profileId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
