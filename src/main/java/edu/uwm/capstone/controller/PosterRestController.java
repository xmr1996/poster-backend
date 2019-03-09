package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Poster.Poster;
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
public class PosterRestController{

    public static final String POSTER_PATH = "/poster";
    private static final Logger logger = LoggerFactory.getLogger(PosterRestController.class);
    private final PosterDao posterDao;

    @Autowired
    public PosterRestController(PosterDao posterDao){
        this.posterDao = posterDao;
    }

     /**
     * Creates the provided {@link Poster}
     *
     * @param poster  {@link Poster}
     * @param response {@link HttpServletResponse}
     * @return {@link Poster}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Poster")
    @PostMapping(value = POSTER_PATH)
    public Poster create(@RequestBody Poster poster, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(poster, "Received null Poster object");
            Assert.isNull(poster.getPosterID(), "Poster ID must be null");
            return posterDao.create(poster);
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
     * Updates the provided {@link Poster}
     *
     * @param poster  {@link Poster}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Update Poster")
    @PutMapping(value = POSTER_PATH)
    public void update(@RequestBody Poster poster, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(poster.getPosterID(), "Poster Id must not be null");
            Assert.notNull(posterDao.read(poster.getPosterID()), "Could not update Poster " + poster.getPosterID()+ " - record not found.");
            posterDao.update(poster);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

     /**
     * Get the {@link Poster} by posterID
     *
     * @param posterID {@link Poster#getPosterID()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Poster} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Poster by ID")
    @GetMapping(value = POSTER_PATH + "{posterID}")
    public Poster readById(@PathVariable String posterID, @ApiIgnore HttpServletResponse response) throws IOException {
        Poster poster = posterDao.read(posterID);

        if (poster == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Poster with ID: " + posterID + " not found.");
            return null;
        }

        return poster;
    }

     /**
     * Delete the {@link Poster} by Id
     *
     * @param PosterID {@link Poster#getPosterID()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Poster by ID")
    @DeleteMapping(value = POSTER_PATH + "{posterID}")
    public void delete(@PathVariable String posterID, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            posterDao.delete(posterID);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    


}