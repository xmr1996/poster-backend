package edu.uwm.capstone.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.sql.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;



public class PosterDao extends BaseDao<Poster> {

    private static final Logger LOG = LoggerFactory.getLogger(PosterDao.class);

    /**
     * Create a {@link Poster} object.
     *
     * @param poster {@link Poster}
     * @return {@link Poster}
     */

    @Override
    public Poster create(Poster poster) {
        // validate input
        if (poster == null) {
            throw new RuntimeException("Request to create a new Poster received null");
       }

        LOG.trace("Creating poster {}", poster);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        poster.setRole("student");
        poster.setVotes(0);
        int result = this.jdbcTemplate.update(sql("createPoster"),
                new MapSqlParameterSource(rowMapper.mapObject(poster)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new RuntimeException("Failed attempt to create poster " + poster.toString() + " affected " + result + " rows");
        }

        Long id = keyHolder.getKey().longValue();
        poster.setId(id);

        return poster;
    }

    /**
     * Retrieve a {@link Poster} object by its {@link Poster#getId()}.
     *
     * @param id long
     * @return {@link Poster}
     */
    @Override
    public Poster read(long id) {
        LOG.trace("Reading poster {}", id);
        try {
            return (Poster) this.jdbcTemplate.queryForObject(sql("readPoster"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Poster> read() {
        LOG.trace("Reading poster {}");
        try {
            return (List<Poster>) this.jdbcTemplate.query(sql("getPosters"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieve a {@link Poster} object by its {@link Poster#getId()}.
     *
     * @param posterID string
     * @return {@link Poster}
     */
    public Poster read(String posterID){
        LOG.trace("Reading poster {}", posterID);
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("poster_id", posterID);
            return (Poster) this.jdbcTemplate.queryForObject(sql("readPosterByID"),parameters, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Poster read(String email, int pin){
        LOG.trace("Reading Poster []");
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("email", email);
            parameters.addValue("pin", pin);
            return (Poster) this.jdbcTemplate.queryForObject(sql("readPosterEmailPin"),parameters, rowMapper);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Poster> readGradWinners(){
        try {
            return (List<Poster>) this.jdbcTemplate.query(sql("readGradWinners"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Poster> readUndergradWinners(){
        try {
            return (List<Poster>) this.jdbcTemplate.query(sql("readUndergradWinners"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void calculateAvgSingle(String poster_id){
        LOG.trace("calculateAvg{}",poster_id);
        int result = this.jdbcTemplate.update(sql("insertAvgR1"), new MapSqlParameterSource("poster_id", poster_id));
        if(result != 1){
            throw new DaoException("Failed attempt to insert average ");
        }
    }





    /**
     * Update the provided {@link Poster} object.
     *
     * @param poster {@link Poster}
     */
    @Override
    public void update(Poster poster) {
        if (poster == null) {
            throw new RuntimeException("Request to update a Poster received null");
        } else if (poster.getId() == null) {
            throw new RuntimeException("When updating a Poster the id should not be null");
        }

        LOG.trace("Updating poster {}", poster);

        int result = this.jdbcTemplate.update(sql("updatePoster"), new MapSqlParameterSource(rowMapper.mapObject(poster)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update poster " + poster.toString() + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Poster} object by its {@link Poster#getId()}.
     *
     * @param id long
     */
    @Override
    public void delete(long id) {
        LOG.trace("Deleting poster {}", id);
        int result = this.jdbcTemplate.update(sql("deletePoster"), new MapSqlParameterSource("id", id));
        if (result != 1) {
            throw new RuntimeException("Failed attempt to update poster " + id + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Poster} object by its {@link Poster#getId()}.
     *
     * @param posterID String
     */
    public void delete(String posterID){
        LOG.trace("Deleting poster{}",posterID);
        int result = this.jdbcTemplate.update(sql("deletePoster"), new MapSqlParameterSource("PosterID", posterID));
        if(result != 1){
            throw new DaoException("Failed attempt to delete poster " + posterID + " affected " + result + " rows");
        }
    }

    public List<Poster> getTop6(String status){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("status", status);
            return (List<Poster>) this.jdbcTemplate.query(sql("getTop6Posters"),parameters,rowMapper);
        } catch(EmptyResultDataAccessException e){
            return null;
        }

    }

    public void calculateAvg(int round ) {
        LOG.trace("calculateAvgForAll{}");
        this.jdbcTemplate.update(sql("test"), new MapSqlParameterSource("round", round));
    }

    public void clearTable(){
        LOG.trace("Clearing posters table{}");
        int result = this.jdbcTemplate.update(sql("clearPosters"), Collections.emptyMap());
        if(result < 0){
            throw new DaoException("Failed attempt to clear posters table");
        }
    }

//    public void importCSV(List<Poster> posters){
//        LOG.trace("Importing from CSV");
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        for(int i=0;i<posters.size();i++) {
//            jdbcTemplate.update(sql("importCSV"), new MapSqlParameterSource(rowMapper.mapObject(posters)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});
//        }
//    }
}
