package edu.uwm.capstone.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.sql.exception.DaoException;


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
       } else if (poster.getPosterID() != null) {
           throw new RuntimeException("When creating a new Poster the id should be null, but was set to " + poster.getPosterID());
        }

        LOG.trace("Creating poster {}", poster);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = this.jdbcTemplate.update(sql("createPoster"),
                new MapSqlParameterSource(rowMapper.mapObject(poster)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new RuntimeException("Failed attempt to create poster " + poster.toString() + " affected " + result + " rows");
        }

        String id = keyHolder.getKey().toString();
        poster.setPosterID(id);

        return poster;
    }

    /**
     * Retrieve a {@link Poster} object by its {@link Poster#getPosterID}.
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

    /**
     * Retrieve a {@link Poster} object by its {@link Poster#getPosterID}.
     *
     * @param posterID string
     * @return {@link Poster}
     */
    public Poster read(String posterID){
        LOG.trace("Reading poster {}", posterID);
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("PosterID", posterID);
            return (Poster) this.jdbcTemplate.queryForObject(sql("readPoster"),parameters, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
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
        } else if (poster.getPosterID() == null) {
            throw new RuntimeException("When updating a Poster the id should not be null");
        }

        LOG.trace("Updating poster {}", poster);

        int result = this.jdbcTemplate.update(sql("updatePoster"), new MapSqlParameterSource(rowMapper.mapObject(poster)));

        if (result != 1) {
            throw new RuntimeException("Failed attempt to update poster " + poster.toString() + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Poster} object by its {@link Poster#getPosterID}.
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
     * Delete a {@link Poster} object by its {@link Poster#getPosterID}.
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
}