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
import java.util.Collections;
import java.util.List;

@RestController
public class JudgeRestController {

    public static final String JUDGE_PATH = "/judge/";
//
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
            Assert.notNull(judge.getJudgeId(), "Judge Id must not be null");
            Assert.notNull(judgeDao.read(judge.getJudgeId()), "Could not update judge " + judge.getJudgeId() + " - record not found.");
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
     * @param judge_id {@link Judge#getJudgeId()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Judge} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Judge by judgeId")
    @GetMapping(value = JUDGE_PATH + "/judge_id/{judge_id}")
    public Judge readByJudgeId(@PathVariable Long judge_id, @ApiIgnore HttpServletResponse response) throws IOException {
        Judge judge = judgeDao.readByJudgeID(judge_id);

        if (judge == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Judge with ID: " + judge_id + " not found.");
            return null;
        }

        return judge;
    }

   /**
    * Get the {@link Judge}
    *
    * @return {@link List<Judge>} retrieved from the database
    * @throws IOException if error response cannot be created.
    */
   @ApiOperation(value = "Read All Judges")
   @GetMapping(value = JUDGE_PATH)
   public List<Judge> readAllJudges(@ApiIgnore HttpServletResponse response) throws IOException {
       List<Judge> judges = judgeDao.read();

       if (judges == null) {
           response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Judges were not found.");
           return Collections.emptyList();
       }

       return judges;
   }


    /**
     * Delete the {@link Judge} by judgeId
     *
     * @param judge_id {@link Judge#getJudgeId()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Judge by judgeId")
    @DeleteMapping(value = JUDGE_PATH + "/judge_id/{judge_id}")
    public void deleteByJudgeId(@PathVariable Long judge_id, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            judgeDao.deleteByJudgeId(judge_id);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value = "Clear judges table")
    @DeleteMapping(value = JUDGE_PATH + "/all")
    public void clearTable(@ApiIgnore HttpServletResponse response) throws IOException{
        try{
            judgeDao.clearTable();
        } catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,e.getMessage());
        }
    }

    @ApiOperation(value = "Insert from csv")
    @PostMapping(value = JUDGE_PATH + "/all")
    public void importCSV(@RequestBody List<Judge> judges){
        for(Judge judge : judges){
            judgeDao.create(judge);
        }
    }

}
