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
            Assert.isNull(score.getId(), "Score ID must be null");
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
    public void create(@ApiIgnore HttpServletResponse response) throws IOException{
        List<Poster> undergradPosters = posterDao.readUndergradWinners();
        List<Poster> gradPosters = posterDao.readGradWinners();
        List<Judge> undergradJudges  = judgeDao.readAllJudges("undergraduate");
        List<Judge> gradJudges = judgeDao.readAllJudges("graduate");

        for (Poster poster : undergradPosters){
            for (Judge judge : undergradJudges){
                Score score = new Score();
                score.setJudge_id(judge.getId());
                score.setPoster_id(poster.getPoster_id());
                score.setRound(2);
                scoreDao.create(score);
            }
        }
        for (Poster poster : gradPosters){
            for (Judge judge : gradJudges){
                Score score = new Score();
                score.setJudge_id(judge.getId());
                score.setPoster_id(poster.getPoster_id());
                score.setRound(2);
                scoreDao.create(score);
            }
        }
    }

    @ApiOperation(value = "Read score by round and judge_id")
    @GetMapping(value = SCORE_PATH + "{round}/{judge_id}")
    public List<PosterScore> readByRoundandJudge(@PathVariable String round, @PathVariable String judge_id, @ApiIgnore HttpServletResponse response) throws IOException {
        List<PosterScore> posters = posterScoreDao.readByRoundandJudge(Long.parseLong(round),Long.parseLong(judge_id));
        if(posters == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Score round number" + round + " and judge id: " + judge_id + " not found.");
            return null;
        }
        return posters;
    }
    /**
     * Get the {@link Score} by Id
     *
     * @param scoreId  {@link Score#getId()}
     * @param response {@link HttpServletResponse}
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
            Assert.notNull(score.getId(), "Score Id must not be null");
            Assert.notNull(scoreDao.read(score.getId()), "Could not update score " + score.getId() + " - record not found.");
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
     * Delete the {@link Score} by Id
     *
     * @param scoreId {@link Score#getId}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Score by ID")
    @DeleteMapping(value = SCORE_PATH + "{scoreId}")
    public void delete(@PathVariable Long scoreId, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            scoreDao.delete(scoreId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
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

        if (scores.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Scores were not found.");
            return null;
        }

        return scores;
    }


    /**
     * Get the {@link Assignment}
     *
     * @return {@link List<Assignment>} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read All Assignments")
    @GetMapping(value = SCORE_PATH + "assignments")
    public List<Assignment> readAllAssignments(@ApiIgnore HttpServletResponse response) throws IOException {
        List<Assignment> assignments = assignmentDao.read();

        if (assignments.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No assignments were found.");
            return null;
        }

        return assignments;
    }
}
