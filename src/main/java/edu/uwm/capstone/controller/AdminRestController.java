package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.AdminDao;
import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.db.ScoreDao;
import edu.uwm.capstone.model.Admin.Admin;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
public class AdminRestController {

    public static final String ADMIN_PATH = "/admin/";

    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
    private final AdminDao adminDao;
    private final ScoreDao scoreDao;
    private final PosterDao posterDao;
    private final JudgeDao judgeDao;

    @Autowired
    public AdminRestController(AdminDao adminDao,ScoreDao scoreDao, PosterDao posterDao,JudgeDao judgeDao) {
        this.adminDao = adminDao;
        this.scoreDao = scoreDao;
        this.posterDao = posterDao;
        this.judgeDao = judgeDao;
    }

    /**
     * Creates the provided {@link Admin}
     *
     * @param admin  {@link Admin}
     * @param response {@link HttpServletResponse}
     * @return {@link Admin}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Create Admin Account")
    @PostMapping(value = ADMIN_PATH)
    public Admin create(@RequestBody Admin admin, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(admin, "Received null Admin object");
            return adminDao.create(admin);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return null;
        }
    }

    /**
     * Get the {@link Admin} by Email
     *
     * @param email {@link Admin#getEmail()}
     * @param response  {@link HttpServletResponse}
     * @return {@link Admin} retrieved from the database
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Admin Account by Email")
    @GetMapping(value = ADMIN_PATH + "{email}")
    public Admin readById(@PathVariable String email, @ApiIgnore HttpServletResponse response) throws IOException {
        Admin admin = adminDao.read(email);

        if (admin == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Admin with Email: " + email + " not found.");
            return null;
        }

        return admin;
    }

    /**
     * Creates the provided {@link Admin}
     *
     * @param response {@link HttpServletResponse}
     * @return {@link List<Admin>}
     * @throws IOException if not admins found.
     */
    @ApiOperation(value = "Read All Admin Accounts")
    @GetMapping(value = ADMIN_PATH)
    public List<Admin> readAll(@ApiIgnore HttpServletResponse response) throws IOException{
        List<Admin> admins = adminDao.readAll();

        if (admins == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No Admins Were Found.");
            return Collections.emptyList();
        }

        return admins;
    }

    /**
     * Updates the provided {@link Admin}
     *
     * @param admin  {@link Admin}
     * @param response {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Update Admin Account")
    @PutMapping(value = ADMIN_PATH)
    public void update(@RequestBody Admin admin, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(adminDao.read(admin.getEmail()), "Could not update Admin " + admin.getEmail() + " - record not found.");
            adminDao.update(admin);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Delete the {@link Admin} by Email
     *
     * @param email {@link Admin#getEmail()}
     * @param response  {@link HttpServletResponse}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Delete Admin Account by Email")
    @DeleteMapping(value = ADMIN_PATH + "{email}")
    public void delete(@PathVariable String email, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            adminDao.delete(email);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value = "Clear Admin table")
    @DeleteMapping(value = ADMIN_PATH + "/all")
    public void clearTable(@ApiIgnore HttpServletResponse response) throws IOException{
        try{
            adminDao.clearTable();
        } catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,e.getMessage());
        }
    }

    @ApiOperation(value = "Insert from csv")
    @PostMapping(value = ADMIN_PATH + "/all")
    public void importCSV(@RequestBody List<Admin> admins){
        for(Admin admin : admins){
            adminDao.create(admin);
        }
    }

    @ApiOperation(value = "Reset all tables")
    @PostMapping(value = ADMIN_PATH + "/reset")
    public void resetTables(){
        scoreDao.clearTable();
        posterDao.clearTable();
        judgeDao.clearTable();
    }
}
