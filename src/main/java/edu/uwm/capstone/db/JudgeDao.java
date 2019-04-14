package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.sql.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import java.util.Collections;
import edu.uwm.capstone.sql.exception.DaoException;
import java.util.List;


public class JudgeDao extends BaseDao<Judge> {

    private static final Logger LOG = LoggerFactory.getLogger(JudgeDao.class);

    /**
     * Create a {@link Judge} object.
     * @param judge {@link Judge}
     * @return {@link Judge}
     */
    @Override
    public Judge create(Judge judge) {
        // validate input
        if (judge == null) {
            throw new RuntimeException("Request to create a new Judge received null");
        }
        LOG.trace("Creating judge {}", judge);
        judge.setRole("judge");
        MapSqlParameterSource parameters = new MapSqlParameterSource(rowMapper.mapObject(judge));

        int result = this.jdbcTemplate.update(sql("upsertJudge"),
                parameters);
        if (result != 1) {
            throw new RuntimeException("Failed attempt to create judge " + judge.toString() + " affected " + result + " rows");
        }
        return judge;
    }

    @Override
    public Judge read(long id) {
        return null;
    }

    public Judge readByJudgeID(long judge_id) {
        LOG.trace("Reading judge {}", judge_id);
        try {
            return (Judge) this.jdbcTemplate.queryForObject(sql("readJudgeByJudgeId"), new MapSqlParameterSource("judge_id", judge_id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Judge> read() {
        LOG.trace("Reading All Judges");
        try {
            return (List<Judge>) this.jdbcTemplate.query(sql("readJudges"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Judge read(String email, String pin){
        LOG.trace("Reading Judge {}", email);
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
            if(status.equalsIgnoreCase("graduate"))
                return (List<Judge>) this.jdbcTemplate.query(sql("readJudgesByStatus"), new MapSqlParameterSource("status", "Graduate"), rowMapper);
            else
                return (List<Judge>) this.jdbcTemplate.query(sql("readJudgesByStatus"), new MapSqlParameterSource("status", "Undergraduate"), rowMapper);
        }catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
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
        } else if (judge.getJudge_id() == null) {
            throw new RuntimeException("When updating a Judge the id should not be null");
        }

        LOG.trace("Updating judge {}", judge);

        int result = this.jdbcTemplate.update(sql("updateJudge"), new MapSqlParameterSource(rowMapper.mapObject(judge)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update judge " + judge.toString() + " affected " + result + " rows");
        }
    }

    @Override
    public void delete(long id) {
        //Delete method with long paramenter is not used
    }

    public void deleteByJudgeId(long judge_id) {
        LOG.trace("Deleting judge by judge_id{}", judge_id);
        int result = this.jdbcTemplate.update(sql("deleteJudgeByJudgeId"), new MapSqlParameterSource("judge_id", judge_id));
        if (result != 1) {
            throw new RuntimeException("Failed attempt to update judge " + judge_id + " affected " + result + " rows");
        }
    }

    public void clearTable(){
        LOG.trace("Clearing judges table");
        int result = this.jdbcTemplate.update(sql("clearJudges"), Collections.emptyMap());
        if(result < 0){
            throw new DaoException("Failed attempt to clear judges table");
        }
    }
}
