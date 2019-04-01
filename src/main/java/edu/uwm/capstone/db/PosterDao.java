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

        poster.setRole("student");

        int result = this.jdbcTemplate.update(sql("createPoster"),
                new MapSqlParameterSource(rowMapper.mapObject(poster)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to create poster " + poster.toString() + " affected " + result + " rows");
        }

        return poster;
    }

    @Override
    public Poster read(long id) {
        return null;
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
     * Retrieve a {@link Poster} object by its {@link Poster#getPoster_id()}.
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

    public Poster read(String email, String pin){
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

    public List<Poster> getPosterByStatus(String status){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("status", status);
            return (List<Poster>) this.jdbcTemplate.query(sql("readPostersByStatus"),parameters,rowMapper);
        } catch(EmptyResultDataAccessException e){
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
        } else if (poster.getPoster_id() == null) {
            throw new RuntimeException("When updating a Poster the id should not be null");
        }

        LOG.trace("Updating poster {}", poster);

        int result = this.jdbcTemplate.update(sql("updatePoster"), new MapSqlParameterSource(rowMapper.mapObject(poster)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update poster " + poster.toString() + " affected " + result + " rows");
        }
    }

    public void setVote(String poster_id, String vote){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("poster_id", poster_id);
        parameters.addValue("vote", vote);
        int result = this.jdbcTemplate.update(sql("setVote"), parameters);

        if (result != 1){
            throw new RuntimeException("Failed to cast vote");
        }
    }

    @Override
    public void delete(long id) {

    }

    /**
     * Delete a {@link Poster} object by its {@link Poster#getPoster_id()}.
     *
     * @param posterID String
     */
    public void delete(String posterID){
        LOG.trace("Deleting poster{}",posterID);
        int result = this.jdbcTemplate.update(sql("deletePoster"), new MapSqlParameterSource("poster_id", posterID));
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

    public void calculateAvgRound1() {
        LOG.trace("calculateAvgForAllR1{}");
        this.jdbcTemplate.update(sql("insertAvgTotalR1"), Collections.emptyMap());
    }

    public void calculateAvgRound2() {
        LOG.trace("calculateAvgForAllR2{}");
        this.jdbcTemplate.update(sql("insertAvgTotalR2"), Collections.emptyMap());
    }

    public void avgCommR1() {
        LOG.trace("calculate Avg For All round1 comm_score{}");
        this.jdbcTemplate.update(sql("insertAvgCommR1"), Collections.emptyMap());
    }

    public void avgCommR2() {
        LOG.trace("calculate Avg For All round2 comm_score{}");
        this.jdbcTemplate.update(sql("insertAvgCommR2"), Collections.emptyMap());
    }

    public void avgResearchR1() {
        LOG.trace("calculate Avg For All round1 research_score{}");
        this.jdbcTemplate.update(sql("insertAvgResearchR1"), Collections.emptyMap());
    }

    public void avgResearchR2() {
        LOG.trace("calculate Avg For All round2 research_score{}");
        this.jdbcTemplate.update(sql("insertAvgResearchR2"), Collections.emptyMap());
    }

    public void avgPresR1() {
        LOG.trace("calculate Avg For All round1 pres_score{}");
        this.jdbcTemplate.update(sql("insertAvgPresR1"), Collections.emptyMap());
    }

    public void avgPresR2() {
        LOG.trace("calculate Avg For All round2 pres_score{}");
        this.jdbcTemplate.update(sql("insertAvgPresR1"), Collections.emptyMap());
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
