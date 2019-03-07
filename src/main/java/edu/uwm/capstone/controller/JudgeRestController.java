package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.model.Judge.Judge;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class JudgeRestController {

    public static final String JUDGE_PATH = "/judge/";

    private static final Logger logger = LoggerFactory.getLogger(JudgeRestController.class);
    private final JudgeDao judgeDao;

    @Autowired
    public JudgeRestController(JudgeDao judgeDao) {
        this.judgeDao = judgeDao;
    }

    /**
     * Creates the provided {@link Judge}
     *
     * @param judge  {@link Judge}
     * @param response {@link HttpServletResponse}
     * @return {@link Judge}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Judge")
    @PostMapping(value = JUDGE_PATH)
    public Judge create(@RequestBody Judge judge, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(judge, "Received null Judge object");
            Assert.isNull(judge.getJudgeID(), "Judge ID must be null");
            return judgeDao.create(judge);
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
     * Updates the provided {@link Judge}
     *
     * @param judge  {@link Judge}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Update Judge")
    @PutMapping(value = JUDGE_PATH)
    public void update(@RequestBody Judge judge, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(judge.getJudgeID(), "Judge Id must not be null");
            Assert.notNull(judgeDao.read(judge.getJudgeID()), "Could not update judge " + judge.getJudgeID() + " - record not found.");
            judgeDao.update(judge);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get the {@link Judge} by Id
     *
     * @param judgeId {@link Judge#getJudgeID()} ()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Judge} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Judge by ID")
    @GetMapping(value = JUDGE_PATH + "{judgeId}")
    public Judge readById(@PathVariable Long judgeId, @ApiIgnore HttpServletResponse response) throws IOException {
        Judge judge = judgeDao.read(judgeId);

        if (judge == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Judge with ID: " + judgeId + " not found.");
            return null;
        }

        return judge;
    }

    /**
     * Delete the {@link Judge} by Id
     *
     * @param judgeId {@link Judge#getJudgeID()} ()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Judge by ID")
    @DeleteMapping(value = JUDGE_PATH + "{judgeId}")
    public void delete(@PathVariable Long judgeId, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            judgeDao.delete(judgeId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
