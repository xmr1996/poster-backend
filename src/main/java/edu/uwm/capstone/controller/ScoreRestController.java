package edu.uwm.capstone.controller;

import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.db.ScoreDao;
import edu.uwm.capstone.model.Score.Score;
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
public class ScoreRestController {

    public static final String SCORE_PATH = "/score/";

    private static final Logger logger = LoggerFactory.getLogger(ProfileRestController.class);
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreRestController(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }


    /**
     * Creates the provided {@link Score}
     *
     * @param score  {@link Score}
     * @param response {@link HttpServletResponse}
     * @return {@link Score}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Score")
    @PostMapping(value = SCORE_PATH)
    public Score create(@RequestBody Score score, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(score, "Received null Profile object");
            Assert.isNull(score.getScoreID(), "Profile ID must be null");
            return scoreDao.create(score);
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
     * Get the {@link Score} by Id
     *
     * @param scoreId {@link Score#getScoreID()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Score} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Score by ID")
    @GetMapping(value = SCORE_PATH + "{scoreId}")
    public Score readById(@PathVariable Long scoreId, @ApiIgnore HttpServletResponse response) throws IOException {
        Score score = scoreDao.read(scoreId);

        if (score == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Score with ID: " + scoreId + " not found.");
            return null;
        }

        return score;
    }

    /**
     * Get the provided {@link List<Score>}
     *
     * @param poster  {@link Poster}
     * @param response {@link HttpServletResponse}
     * @return {@link List<Score>}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Get Poster Scores")
    @GetMapping(value = SCORE_PATH)
    public List<Score> read(@RequestBody Poster poster, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            List<Score> scores = scoreDao.read(poster);
            if(scores.isEmpty()){
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Scores for " + poster.getId() + " were not found.");
                return null;
            }
            return scores;
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
}
