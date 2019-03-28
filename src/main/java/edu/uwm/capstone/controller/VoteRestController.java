package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Poster.Poster;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteRestController {

    public static final String VOTE_PATH = "/vote/";
    private final PosterDao posterDao;

    private static final Logger logger = LoggerFactory.getLogger(ScoreRestController.class);

    @Autowired
    public VoteRestController(PosterDao posterDao) {
        this.posterDao = posterDao;
    }

    @ApiOperation(value = "Submit vote")
    @PutMapping(value = VOTE_PATH + "{vote}")
    public Poster vote(@RequestBody Poster poster, @PathVariable String vote){

        if(poster.getVoted_for() != null) {
            String oldVote = poster.getVoted_for();
            Poster oldPoster = posterDao.read(oldVote);
            oldPoster.setVotes(oldPoster.getVotes() - 1);
            posterDao.update(oldPoster);
        }

        if(poster.getPoster_id().equals(vote)){
            poster.setVotes(poster.getVotes() + 1);
            poster.setVoted_for(vote);
            posterDao.update(poster);
            return poster;
        }

        Poster poster_voted = posterDao.read(vote);

        poster_voted.setVotes(poster_voted.getVotes() + 1);
        posterDao.update(poster_voted);
        poster.setVoted_for(vote);
        posterDao.update(poster);

        return poster;
    }

    @ApiOperation(value = "Get Graduate Vote Winner")
    @GetMapping(value = VOTE_PATH + "graduate")
    public List<Poster> getGradWinner(){
        return posterDao.readGradWinners();
    }

    @ApiOperation(value = "Get Undergrad Vote Winner")
    @GetMapping(value = VOTE_PATH + "undergrad")
    public List<Poster> getUndergradWinner(){
        return posterDao.readUndergradWinners();
    }
}
