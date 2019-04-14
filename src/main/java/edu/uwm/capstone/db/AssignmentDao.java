package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


import java.util.Collections;
import java.util.List;

public class AssignmentDao extends BaseDao<Assignment> {

    private static final Logger LOG = LoggerFactory.getLogger(AssignmentDao.class);


    public Assignment create(Assignment assignment) {
        return null;
    }

    public Assignment read(long id) {
            return null;
    }

    public void delete(long id) {
        //Delete method with long parameter is not needed
    }
    public void update(Assignment score) {
        //Update method with Assignment parameter is not needed
    }
    //  Get All Assignments

    //read all assignments
    public List<Assignment> readAssignments(int round){
        LOG.trace("Read Assignment by round");
        try {
            return (List<Assignment>) this.jdbcTemplate.query(sql("getAllAssignments"), new MapSqlParameterSource("round", round), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
