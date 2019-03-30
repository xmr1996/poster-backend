package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import edu.uwm.capstone.sql.exception.DaoException;
import java.util.List;
import java.util.Map;


public class JudgeDao extends BaseDao<Judge> {

    private static final Logger LOG = LoggerFactory.getLogger(JudgeDao.class);

    /**
     * Create a {@link Judge} object.
     *
     * @param judge {@link Judge}
     * @return {@link Judge}
     */
    @Override
    public Judge create(Judge judge) {
        // validate input
        if (judge == null) {
            throw new RuntimeException("Request to create a new Judge received null");
        } else if (judge.getId() != null) {
            throw new RuntimeException("When creating a new Judge the id should be null, but was set to " + judge.getId());
        }

        LOG.trace("Creating judge {}", judge);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource(rowMapper.mapObject(judge));

        int result = this.jdbcTemplate.update(sql("createJudge"),
                parameters, keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new RuntimeException("Failed attempt to create judge " + judge.toString() + " affected " + result + " rows");
        }

        Long id = keyHolder.getKey().longValue();
        judge.setId(id);

        return judge;
    }

    /**
     * Retrieve a {@link Judge} object by its {@link Judge#getId}.
     *
     * @param id long
     * @return judge {@link Judge}
     */
    @Override
    public Judge read(long id) {
        LOG.trace("Reading judge {}", id);
        try {
            return (Judge) this.jdbcTemplate.queryForObject(sql("readJudge"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieve a {@link Judge} object by its {@link Judge#getId}.
     *
     * @return judge {@link List<Judge>}
     */
    public List<Judge> read() {
        LOG.trace("Reading judges {}");
        try {
            return (List<Judge>) this.jdbcTemplate.query(sql("readJudges"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Judge read(String email, int pin){
        LOG.trace("Reading Judge {}");
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("email", email);
            parameters.addValue("pin", pin);
            return (Judge) this.jdbcTemplate.queryForObject(sql("readJudgeEmailPin"),parameters, rowMapper);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Judge> readAllJudges(String status){
        try{
            if(status.toLowerCase().equals("graduate"))
                return (List<Judge>) this.jdbcTemplate.query(sql("readJudgesByStatus"), new MapSqlParameterSource("status", "Graduate"), rowMapper);
            else
                return (List<Judge>) this.jdbcTemplate.query(sql("readJudgesByStatus"), new MapSqlParameterSource("status", "Undergraduate"), rowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Update the provided {@link Judge} object.
     *
     * @param judge {@link Judge}
     */
    @Override
    public void update(Judge judge) {
        if (judge == null) {
            throw new RuntimeException("Request to update a Judge received null");
        } else if (judge.getId() == null) {
            throw new RuntimeException("When updating a Judge the id should not be null");
        }

        LOG.trace("Updating judge {}", judge);

        int result = this.jdbcTemplate.update(sql("updateJudge"), new MapSqlParameterSource(rowMapper.mapObject(judge)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update judge " + judge.toString() + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Judge} object by its {@link Judge#getId}.
     *
     * @param id long
     */
    @Override
    public void delete(long id) {
        LOG.trace("Deleting judge {}", id);
        int result = this.jdbcTemplate.update(sql("deleteJudge"), new MapSqlParameterSource("id", id));
        if (result != 1) {
            throw new RuntimeException("Failed attempt to update judge " + id + " affected " + result + " rows");
        }
    }

    public void clearTable(){
        LOG.trace("Clearing judges table{}");
        int result = this.jdbcTemplate.update(sql("clearJudges"), Collections.emptyMap());
        if(result < 0){
            throw new DaoException("Failed attempt to clear judges table");
        }
    }
}
