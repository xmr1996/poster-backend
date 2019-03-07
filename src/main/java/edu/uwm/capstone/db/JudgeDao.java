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


public class JudgeDao extends BaseDao<Judge> {

    private static final Logger LOG = LoggerFactory.getLogger(JudgeDao.class);

    /**
     * Create a {@link Judge} object.
     *
     * @param judge {@link Judge}
     * @return {@link Judge
     */

    @Override
    public Judge create(Judge judge) {
        // validate input
        if (judge == null) {
            throw new RuntimeException("Request to create a new Judge received null");
        } else if (judge.getJudgeID() != null) {
            throw new RuntimeException("When creating a new Judge the id should be null, but was set to " + judge.getJudgeID());
        }

        LOG.trace("Creating judge {}", judge);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = this.jdbcTemplate.update(sql("createJudge"),
                new MapSqlParameterSource(rowMapper.mapObject(judge)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new RuntimeException("Failed attempt to create judge " + judge.toString() + " affected " + result + " rows");
        }

        Long id = keyHolder.getKey().longValue();
        judge.setJudgeID(id);

        return judge;
    }

    /**
     * Retrieve a {@link Judge} object by its {@link Judge#getJudgeID}.
     *
     * @param id long
     * @return {@link Judge}
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
     * Update the provided {@link Judge} object.
     *
     * @param judge {@link Judge}
     */
    @Override
    public void update(Judge judge) {
        if (judge == null) {
            throw new RuntimeException("Request to update a Judge received null");
        } else if (judge.getJudgeID() == null) {
            throw new RuntimeException("When updating a Judge the id should not be null");
        }

        LOG.trace("Updating judge {}", judge);

        int result = this.jdbcTemplate.update(sql("updateJudge"), new MapSqlParameterSource(rowMapper.mapObject(judge)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update judge " + judge.toString() + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Judge} object by its {@link Judge#getJudgeID}.
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
}
