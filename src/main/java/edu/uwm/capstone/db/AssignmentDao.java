package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


import java.util.List;

public class AssignmentDao extends BaseDao<Assignment> {

    private static final Logger LOG = LoggerFactory.getLogger(AssignmentDao.class);


    public Assignment create(Assignment Assignment) {
        return null;
    }
    public Assignment read(long id) {
            return null;
    }
    public void delete(long id) {
    }
    public void update(Assignment score) {
    }
    //  Get All Assignments

    //read all assignments
    public List<Assignment> readAssignments(int round){
        LOG.trace("Read Assignment by round");
        try {
            //MapSqlParameterSource parameters = new MapSqlParameterSource();
            //parameters.addValue("round", round);
            return (List<Assignment>) this.jdbcTemplate.query(sql("getAllAssignments"), new MapSqlParameterSource("round", round), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
