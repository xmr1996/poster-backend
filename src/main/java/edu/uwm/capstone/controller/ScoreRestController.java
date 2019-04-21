package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.*;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.PosterScore.PosterScore;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.model.Assignment.Assignment;
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
import java.util.Collections;
import java.util.List;

@RestController
public class ScoreRestController {

    public static final String SCORE_PATH = "/score/";

    private static final Logger logger = LoggerFactory.getLogger(ScoreRestController.class);
    private final ScoreDao scoreDao;
    private final AssignmentDao assignmentDao;
    private final PosterScoreDao posterScoreDao;
    private final JudgeDao judgeDao;
    private final PosterDao posterDao;

    @Autowired
    public ScoreRestController(ScoreDao scoreDao, AssignmentDao assignmentDao, PosterScoreDao posterScoreDao, JudgeDao judgeDao, PosterDao posterDao) {
        this.scoreDao = scoreDao;
        this.assignmentDao = assignmentDao;
        this.posterScoreDao = posterScoreDao;
        this.judgeDao = judgeDao;
        this.posterDao = posterDao;
    }

    /**
     * Creates the provided {@link Score}
     *
     * @param score    {@link Score}
     * @param response {@link HttpServletResponse}
     * @return {@link Score}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Score")
    @PostMapping(value = SCORE_PATH)
    public Score create(@RequestBody Score score, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(score, "Received null Score object");
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
     * Creates the provided {@link Score}
     *
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Generate Round 2 Assignments")
    @PostMapping(value = SCORE_PATH + "generateRound2")
    public void GenerateRoundTwoAssignments(@RequestBody List<String> posters, @ApiIgnore HttpServletResponse response) throws IOException{
        scoreDao.deleteScoreByRound(2);

        for(String poster_id : posters){
            Poster poster = posterDao.read(poster_id);
            assignJudges(poster_id, poster.getStatus());
        }
    }

    private void assignJudges(String posterId, String status){
        List<Judge> judges = new ArrayList<>();

        if(status.equals("Graduate")) {
            judges = judgeDao.readAllJudges("Graduate");
        }
        else if(status.equals("Undergraduate")) {
            judges = judgeDao.readAllJudges("Undergraduate");
        }
        createScores(posterId, judges);

    }

    private void createScores(String posterId, List<Judge> judges){
        for(Judge judge : judges){
            Score score = new Score();
            score.setJudge_id(judge.getJudgeId());
            score.setPoster_id(posterId);
            score.setRound(2);
            scoreDao.create(score);
        }
    }

    @ApiOperation(value = "Read score by round and judgeId")
    @GetMapping(value = SCORE_PATH + "{round}/{judgeId}")
    public List<PosterScore> readByRoundandJudge(@PathVariable String round, @PathVariable String judgeId, @ApiIgnore HttpServletResponse response) throws IOException {
        List<PosterScore> posters = posterScoreDao.readByRoundandJudge(Long.parseLong(round),Long.parseLong(judgeId));
        if(posters == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Score round number" + round + " and judge id: " + judgeId + " not found.");
            return Collections.emptyList();
        }
        return posters;
    }

    @ApiOperation(value = "Read scores by round")
    @GetMapping(value = SCORE_PATH + "{round}")
    public List<Score> readByRound(@PathVariable int round){
        return scoreDao.readByRound(round);
    }

    /**
     * Updates the provided {@link Score}
     *
     * @param score    {@link Score}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Update Score")
    @PutMapping(value = SCORE_PATH)
    public void update(@RequestBody Score score, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(score.getPoster_id(), "Poster Id must not be null");
            Assert.notNull(score.getJudge_id(), "Judge Id must not be null");
            Assert.notNull(scoreDao.read(score.getJudge_id(), score.getPoster_id()), "Could not update score " + score.getPoster_id()+ " " + score.getJudge_id() + " - record not found.");
            scoreDao.update(score);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    /**
     * Get the {@link Score}
     *
     * @return {@link List<Score>} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read All Scores")
    @GetMapping(value = SCORE_PATH)
    public List<Score> readAllScore(@ApiIgnore HttpServletResponse response) throws IOException {
        List<Score> scores = scoreDao.read();

        if (scores == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Scores were not found.");
            return Collections.emptyList();
        }

        return scores;
    }


    /**
     * Get the {@link Assignment}
     *
     * @return {@link List<Assignment>} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read All Assignments by round number")
    @GetMapping(value = SCORE_PATH + "assignments/{round}")
    public List<Assignment> readAllAssignments(@PathVariable int round, @ApiIgnore HttpServletResponse response) throws IOException {
        List<Assignment> assignments = assignmentDao.readAssignments(round);

        if (assignments == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No assignments were found.");
            return Collections.emptyList();
        }

        return assignments;
    }

    @ApiOperation(value = "Clear score table")
    @DeleteMapping(value = SCORE_PATH + "/all")
    public void clearTable(@ApiIgnore HttpServletResponse response) throws IOException{
        try{
            scoreDao.clearTable();
        } catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,e.getMessage());
        }
    }

    @ApiOperation(value = "Delete score by judgeId and poster_id")
    @DeleteMapping(value = SCORE_PATH + "{judgeId}/{poster_id}")
    public void delete(@PathVariable Long judgeId, @PathVariable String posterId, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            scoreDao.deleteScoreByID(judgeId, posterId);
        } catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value = "Insert from csv")
    @PostMapping(value = SCORE_PATH + "/all")
    public void importCSV(@RequestBody List<Score> scores){
        for(Score score : scores){
            score.setRound(1);
            try {
                scoreDao.create(score);
            }catch (Exception e){
                logger.error(e.getMessage(), e);
            }
        }
    }
}
