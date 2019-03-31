package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Round.Round;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class RoundDao extends BaseDao<Round> {
    private static final Logger LOG = LoggerFactory.getLogger(RoundDao.class);


    @Override
    public Round create(Round object) {
        return null;
    }

    @Override
    public Round read(long id) {
        return null;
    }

    @Override
    public void delete(long id) {}

    @Override
    public void update(Round object) {}

    public List<Round> read(){
        LOG.trace("Reading by round");
        try{
            return (List<Round>)this.jdbcTemplate.query(sql("readAllRounds"),rowMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public int update(int roundNum, boolean roundValue){
        LOG.trace("Updating round");
        if(roundNum == 1)
            return this.jdbcTemplate.update(sql("updateRound1"),new MapSqlParameterSource("round1", roundValue));
        else if (roundNum == 2)
            return this.jdbcTemplate.update(sql("updateRound2"),new MapSqlParameterSource("round2", roundValue));
        return 0;
    }
}