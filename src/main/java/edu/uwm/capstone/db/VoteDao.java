package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Vote.Vote;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class VoteDao extends BaseDao<Vote> {

    private static final Logger LOG = LoggerFactory.getLogger(VoteDao.class);


    public Vote create(Vote vote) {
        return null;
    }
    public Vote read(long id) {
        return null;
    }
    public void delete(long id) {
    }
    public void update(Vote vote) {
    }

    //Get Votes by Status
    public List<Vote> read(String status){
        LOG.trace("Read Votes{}");
        try {

            return (List<Vote>) this.jdbcTemplate.query(sql("readVotesByStatus"), new MapSqlParameterSource("status", status), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
