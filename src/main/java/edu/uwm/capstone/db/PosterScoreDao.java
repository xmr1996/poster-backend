package edu.uwm.capstone.db;

import edu.uwm.capstone.model.PosterScore.PosterScore;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import edu.uwm.capstone.sql.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSourceExtensionsKt;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.util.List;

public class PosterScoreDao extends BaseDao<PosterScore> {
    private static final Logger LOG = LoggerFactory.getLogger(PosterScoreDao.class);

    public List<PosterScore> readByRoundandJudge(long round, long judgeID){
        LOG.trace("Reading Score {}", round, judgeID);
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("round", round);
            parameters.addValue("judge_id", judgeID);
            return (List<PosterScore>) this.jdbcTemplate.query(sql("readScoreByRoundandJudge"), parameters, rowMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Retrieve a {@link Score} object by its {@link Score#getScoreID()}.
     *
     * @param id long
     * @return {@link Score}
     */
    @Override
    public PosterScore read(long id) {
        return null;
    }



    /**
     * Update the provided {@link Score} object.
     *
     * @param score {@link Score}
     */
    @Override
    public void update(PosterScore score) {
        return;
    }

    /**
     * Delete a {@link Score} object by its {@link Score#getScoreID()}.
     *
     * @param id long
     */
    @Override
    public void delete(long id) {
        return;
    }


    @Override
    public PosterScore create(PosterScore score) {
        return null;
    }


}
