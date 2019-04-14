package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.db.VoteDao;
import edu.uwm.capstone.model.Vote.Vote;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteRestController {

    public static final String VOTE_PATH = "/vote/";
    private final PosterDao posterDao;
    private final VoteDao voteDao;


    @Autowired
    public VoteRestController(PosterDao posterDao, VoteDao voteDao) {
        this.posterDao = posterDao;
        this.voteDao = voteDao;
    }

    @ApiOperation(value = "Submit vote")
    @PutMapping(value = VOTE_PATH + "{poster_id}" + "/{vote}")
    public void vote(@PathVariable String poster_id, @PathVariable String vote){
        posterDao.setVote(poster_id, vote);
    }

    @ApiOperation(value = "Get Vote Info")
    @GetMapping(value = VOTE_PATH + "{status}")
    public List<Vote> getGradWinner(@PathVariable String status){
        return voteDao.read(status);
    }

}
