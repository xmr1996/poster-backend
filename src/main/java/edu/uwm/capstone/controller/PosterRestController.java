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
import java.util.ArrayList;
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
            Assert.notNull(poster.getPoster_id(), "Poster Id must not be null");
            Assert.notNull(posterDao.read(poster.getPoster_id()), "Could not update Poster " + poster.getPoster_id()+ " - record not found.");
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
    @ApiOperation(value = "Calculate the average for total score of all poster")
    @PutMapping(value = POSTER_PATH+ "average/total/{round}")
    public void calculateAverage(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            if(round ==1){
                posterDao.calculateAvgRound1();
            }
            else if(round ==2){
                posterDao.calculateAvgRound2();
            }

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    /**
     * calculate all avg fields
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Calculate all average fields for poster")
    @PutMapping(value = POSTER_PATH+ "average/all/{round}")
    public void calculateAllAverage(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            if(round ==1){
                posterDao.calculateAvgRound1();
                posterDao.avgResearchR1();
                posterDao.avgPresR1();
                posterDao.avgCommR1();

            }
            else if(round ==2){
                posterDao.calculateAvgRound2();
                posterDao.avgResearchR2();
                posterDao.avgPresR2();
                posterDao.avgCommR2();
            }

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "Calculate the average for comm_score of all poster")
    @PutMapping(value = POSTER_PATH+ "average/comm/{round}")
    public void calculateCommAvgR1(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            if(round ==1){
                posterDao.avgCommR1();
            }
            else if(round ==2){
                posterDao.avgCommR2();
            }

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "Calculate the average for research_score of all poster")
    @PutMapping(value = POSTER_PATH+ "average/research/{round}")
    public void calculateResearchAvgR1(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            if(round ==1){
                posterDao.avgResearchR1();
            }
            else if(round ==2){
                posterDao.avgResearchR2();
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "Calculate the average for pres_score of all poster")
    @PutMapping(value = POSTER_PATH+ "average/pres/{round}")
    public void calculatePresAvgR1(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            if(round ==1){
                posterDao.avgPresR1();
            }
            else if(round ==2){
                posterDao.avgPresR2();
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get the {@link Poster}
     *
     * @return {@link List<Poster>} retrieved from the database
     */
    @ApiOperation(value = "Read All Posters")
    @GetMapping(value = POSTER_PATH)
    public List<Poster> read(){
        return posterDao.read();
    }

    @ApiOperation(value = "Read poster by Poster_ID")
    @GetMapping(value = POSTER_PATH + "/poster_id/" + "{posterId}")
    public Poster readByPosterID(@PathVariable String posterId){
        return posterDao.read(posterId);
    }

    @ApiOperation(value = "Read poster by student status")
    @GetMapping(value = POSTER_PATH + "/status/" + "{status}")
    public List<Poster> getPostersByStatus(@PathVariable String status){
        return posterDao.getPosterByStatus(status);
    }

    /**
     * Get the {@link Poster} by Round and Status
     *
     * @param status
     * @return {@link List<Poster>} retrieved from the database
     **/
    @ApiOperation(value = "Get top 6 posters for round1")
    @GetMapping(value = POSTER_PATH + "top/round1/{status}")
    public List<Poster> getTop6R1(@PathVariable String status){
        return posterDao.getTop6R1(status);
    }

    /**
     * Get the {@link Poster} by Round and Status
     *
     * @param status
     * @return {@link List<Poster>} retrieved from the database
     **/
    @ApiOperation(value = "Get top 6 posters for round2")
    @GetMapping(value = POSTER_PATH + "top/round2/{status}")
    public List<Poster> getTop6R2(@PathVariable String status){
        return posterDao.getTop6R2(status);
    }



    /**
     * Get the top 6 {@link Poster} by Round
     *
     * @param round
     * @param response  {@link HttpServletResponse}
     * @return {@link List<Poster>} retrieved from the database
     * @throws IOException if error response cannot be created.
     **/
    @ApiOperation(value = "Get top 6 posters by round")
    @GetMapping(value = POSTER_PATH + "top/{round}")
    public List<Poster> getTop6ByRound(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException{

        List<Poster> uPoster =null;
        List<Poster> gPoster =null;
        if(round ==1){
            uPoster = posterDao.getTop6R1("Undergraduate");
            gPoster = posterDao.getTop6R1("Graduate");
            uPoster.addAll(gPoster);
        }
        else if(round ==2) {
            uPoster = posterDao.getTop6R2("Undergraduate");
            gPoster = posterDao.getTop6R2("Graduate");
            uPoster.addAll(gPoster);
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "invalid round number,No Posters Were Found.");
            return new ArrayList<>();
        }
        return uPoster;
    }

    @ApiOperation(value = "Clear posters table")
    @DeleteMapping(value = POSTER_PATH + "/all")
    public void clearTable(@ApiIgnore HttpServletResponse response) throws IOException{
        try{
            posterDao.clearTable();
        } catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,e.getMessage());
        }
    }

    /**
     * Delete the {@link Poster} by poster_id
     *
     * @param posterId {@link Poster#getPoster_id()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Poster by poster_id")
    @DeleteMapping(value = POSTER_PATH + "{posterId}")
    public void deleteByJudgeId(@PathVariable String posterId, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            posterDao.delete(posterId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value = "Insert from csv")
    @PostMapping(value = POSTER_PATH + "/all")
    public void importCSV(@RequestBody List<Poster> posters){
        for(Poster poster : posters){
            posterDao.create(poster);
        }
    }

}