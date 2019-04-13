package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.sql.dao.BaseDao;
import edu.uwm.capstone.sql.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Collections;
import java.util.List;

public class AdminDao extends BaseDao<Admin> {

    private static final Logger LOG = LoggerFactory.getLogger(AdminDao.class);

    /**
     * Create a {@link Admin object.
     *
     * @param profile {@link Admin}
     * @return {@link Admin}
     */
    @Override
    public Admin create(Admin admin) {
        // validate input
        if (admin == null) {
            throw new DaoException("Request to create a new Profile received null");
        }

        admin.setRole("admin");

        LOG.trace("Creating Admin {}", admin);

        int result = this.jdbcTemplate.update(sql("createAdmin"), new MapSqlParameterSource(rowMapper.mapObject(admin)));

        if (result != 1) {
            throw new DaoException("Failed attempt to create admin " + admin.toString() + " affected " + result + " rows");
        }

        return admin;
    }

    /**
     * Create a {@link Admin object.
     *
     * @return {@link List<Admin>}
     */
    public List<Admin> readAll(){
        LOG.trace("Reading all admins");
        try {
            return (List<Admin>) this.jdbcTemplate.query(sql("readAllAdmins"), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieve a {@link Admin} object by its {@link Admin#getEmail()}.
     *
     * @param email String
     * @return {@link Admin}
     */
    public Admin read(String email){
        LOG.trace("Reading Admin Account {}", email);
        try {
            return (Admin) this.jdbcTemplate.queryForObject(sql("readAdmin"), new MapSqlParameterSource("email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Admin read(String email, String pin){
        LOG.trace("Reading admin");
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("email", email);
            parameters.addValue("pin", pin);
            return (Admin) this.jdbcTemplate.queryForObject(sql("readAdminEmailPin"),parameters, rowMapper);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update the provided {@link Admin} object.
     *
     * @param admin {@link Admin}
     */
    @Override
    public void update(Admin admin) {
        if (admin == null) {
            throw new DaoException("Request to update a Profile received null");
        }

        LOG.trace("Updating Admin {}", admin);
        int result = this.jdbcTemplate.update(sql("updateAdmin"), new MapSqlParameterSource(rowMapper.mapObject(admin)));

        if (result != 1) {
            throw new DaoException("Failed attempt to update profile " + admin.toString() + " affected " + result + " rows");
        }
    }

    /**
     * Delete a {@link Admin} object by its {@link Admin#getEmail()}.
     *
     * @param email String
     */
    public void delete(String email) {
        LOG.trace("Deleting Admin Account {}", email);
        int result = this.jdbcTemplate.update(sql("deleteAdmin"), new MapSqlParameterSource("email", email));
        if (result != 1) {
            throw new DaoException("Failed attempt to delete account " + email + " affected " + result + " rows");
        }
    }

    public void clearTable(){
        LOG.trace("Clearing judges table");
        int result = this.jdbcTemplate.update(sql("clearAdmins"), Collections.emptyMap());
        if(result < 0){
            throw new DaoException("Failed attempt to clear judges table");
        }
    }

    @Override
    public Admin read(long id) {
        return null;
    }


    @Override
    public void delete(long id) {
        return;

    }
}
