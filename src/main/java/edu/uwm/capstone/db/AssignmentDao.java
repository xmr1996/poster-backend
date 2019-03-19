package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.sql.exception.DaoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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

    public List<Assignment> read(){
        LOG.trace("Read Assignment{}");
        try {
            return (List<Assignment>) this.jdbcTemplate.query(sql("getAllAssignments"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
