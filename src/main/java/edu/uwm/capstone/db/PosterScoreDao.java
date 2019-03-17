package edu.uwm.capstone.db;

import edu.uwm.capstone.sql.dao.BaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class PosterScoreDao extends BaseDao<PosterScore> {
    public List<PosterScore> readByRound(long round, long judgeID){
        LOG.trace("Reading Score {}", round, judgeID);
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("round", round);
            parameters.addValue("judge_id", judgeID);
            return (List<PosterScore>) this.jdbcTemplate.query(sql("readScoreByRound"), PosterScoreRowMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
