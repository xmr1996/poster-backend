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

    public static final String POSTER_PATH = "/poster/";
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
            Assert.notNull(poster.getId(), "Poster Id must not be null");
            Assert.notNull(posterDao.read(poster.getId()), "Could not update Poster " + poster.getId()+ " - record not found.");
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
     * calculate {@link Poster#getAvg_r1()}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Calculate the average for all poster")
    @PutMapping(value = POSTER_PATH+ "average")
    public void calculateAverage( @ApiIgnore HttpServletResponse response) throws IOException {

        List<Poster> posters = posterDao.read();
        for(Poster poster: posters){
            posterDao.calculateAvg(poster.getPoster_id());
        }

    }

     /**
     * Get the {@link Poster} by id
     *
     * @param id {@link Poster#getId()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Poster} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Poster by ID")
    @GetMapping(value = POSTER_PATH + "{id}")
    public Poster readById(@PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        Poster poster = posterDao.read(id);

        if (poster == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Poster with ID: " + id + " not found.");
            return null;
        }

        return poster;
    }

    /**
     * Get the {@link Poster}
     *
     * @return {@link List<Poster>} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read All Posters")
    @GetMapping(value = POSTER_PATH)
    public List<Poster> read(@ApiIgnore HttpServletResponse response) throws IOException {
        List<Poster> poster = posterDao.read();

        if (poster == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Posters Were Found.");
            return null;
        }

        return poster;
    }

    @ApiOperation(value = "Read poster by Poster_ID")
    @GetMapping(value = POSTER_PATH + "/poster_id/" + "{poster_id}")
    public Poster readByPosterID(@PathVariable String poster_id, @ApiIgnore HttpServletResponse response) throws IOException{
        Poster poster = posterDao.read(poster_id);

        if(poster == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Poster with ID: " + poster_id + "not found.");
        }
        return poster;
    }

     /**
     * Delete the {@link Poster} by Id
     *
     * @param id {@link Poster#getId()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Poster by ID")
    @DeleteMapping(value = POSTER_PATH + "{id}")
    public void delete(@PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            posterDao.delete(id);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Get the {@link Poster} by Round and Status
     *
     * @param status
     * @param response  {@link HttpServletResponse}
     * @return {@link List<Poster>} retrieved from the database
     * @throws IOException if error response cannot be created.
     **/
    @ApiOperation(value = "Get top 6 posters")
    @GetMapping(value = POSTER_PATH + "top/{status}")
    public List<Poster> getTop6(@PathVariable String status, @ApiIgnore HttpServletResponse response) throws IOException{
        List<Poster> posters = posterDao.getTop6(status);
        if (posters == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Posters Were Found.");
            return null;
        }
        return posters;
    }

}