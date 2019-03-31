package edu.uwm.capstone.controller;

import edu.uwm.capstone.model.Round.Round;
import edu.uwm.capstone.db.RoundDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class RoundRestController {
    public static final String ROUND_PATH = "/round/";
    private final RoundDao roundDao;

    private static final Logger logger = LoggerFactory.getLogger(RoundRestController.class);

    @Autowired
    public RoundRestController(RoundDao roundDao){
        this.roundDao = roundDao;
    }

    @ApiOperation(value = "Active rounds")
    @GetMapping(value = ROUND_PATH)
    public List<Round> getRounds(@ApiIgnore HttpServletResponse response) throws IOException {
        //Round round = roundDao.read();
        List<Round> round = roundDao.read();
        if (round.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Rounds were not found.");
            return null;
        }
        return round;
    }

    @ApiOperation(value = "Update round")
    @PutMapping (value = ROUND_PATH + "{roundNum}" + "/{roundValue}")
    public void updateRound(@PathVariable String roundNum,@PathVariable String roundValue,@ApiIgnore HttpServletResponse response) throws IOException{
        int num = Integer.parseInt(roundNum);
        boolean value = Boolean.parseBoolean(roundValue);
        int round = roundDao.update(num,value);
        if(round == 0){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Round num " + roundNum + " is not found");
        }
    }

}
