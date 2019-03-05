package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.JudgeDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class AddJudgeController {

    public static final String CONCATENATE_PATH = "";
    private JudgeDao judge = new JudgeDao();

    /**
     * This endpoint takes two separate values and combines them together.

     *
     */
    @ApiOperation(value = "two create a new Judge table")
    @RequestMapping(value = CONCATENATE_PATH, method = RequestMethod.GET)
    public void createJudge() {


    }

}
