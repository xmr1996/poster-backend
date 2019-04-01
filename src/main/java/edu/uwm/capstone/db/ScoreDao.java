package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import java.util.List;

public class ScoreDao extends BaseDao<Score> {

    private static final Logger LOG = LoggerFactory.getLogger(ScoreDao.class);

    /**
     * Create a {@link Score} object.
     *
     * @param score {@link Score}
     * @return {@link Score}
     */
    public Score create(Score score) {
        // validate input
        if (score == null) {
            throw new DaoException("Request to create a new Score received null");
        }
        else if (score.getPoster_id() == null || score.getJudge_id() == null) {
            throw new DaoException("When creating a new Score the PosterID and JudgeID should not be empty.");
        }

        LOG.trace("Creating score {}", score);

        MapSqlParameterSource parameters = new MapSqlParameterSource(rowMapper.mapObject(score));

        int result = this.jdbcTemplate.update(sql("upsertScore"),
                parameters);


        if (result != 1) {
            throw new DaoException("Failed attempt to create score " + score.toString() + " affected " + result + " rows");
        }

        return score;
    }

    @Override
    public Score read(long id) {
        return null;
    }

    /**
     * Retrieve a {@link Score} object by its {@link Score#getJudge_id() #getPoster_id()}.
     *
     * @param judgeID long
     * @param posterID long
     * @return {@link Score}
     */
    public Score read(long judgeID, String posterID) {
        LOG.trace("Reading Score {}", judgeID, posterID);
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("JudgeID", judgeID);
            parameters.addValue("PosterID", posterID);
            return (Score) this.jdbcTemplate.queryForObject(sql("readScore"),parameters, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Score> read(Poster poster){
        LOG.trace("Getting Reading scores {}", poster);
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("Poster", poster);
            return (List<Score>) this.jdbcTemplate.queryForObject(sql("getScoreByPosterID"), parameters, rowMapper);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    //read all scores
    public List<Score> read(){
        LOG.trace("Read Score{}");
        try {
            return (List<Score>) this.jdbcTemplate.query(sql("readAllScores"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update the provided {@link Score} object.
     *
     * @param score {@link Score}
     */
    @Override
    public void update(Score score) {
        if (score == null) {
            throw new DaoException("Request to update a Score received null");
        }

        LOG.trace("Updating score {}", score);
        //score.setUpdatedDate(LocalDateTime.now());
        int result = this.jdbcTemplate.update(sql("upsertScore"), new MapSqlParameterSource(rowMapper.mapObject(score)));

        if (result != 1) {
            throw new DaoException("Failed attempt to update score " + score.toString() + " affected " + result + " rows");
        }
    }

    @Override
    public void delete(long id) {

    }

    public void clearTable(){
        LOG.trace("Clearing score table{}");
        int result = this.jdbcTemplate.update(sql("clearScore"), Collections.emptyMap());
        if(result < 0){
            throw new DaoException("Failed attempt to clear score table");
        }
    }
}
