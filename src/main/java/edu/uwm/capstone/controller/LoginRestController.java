package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.AdminDao;
import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginRestController {

    public static final String LOGIN_PATH = "/login/{email}/{pin}";

    private final JudgeDao judgeDao;
    private final PosterDao posterDao;
    private final AdminDao adminDao;

    @Autowired
    public LoginRestController(JudgeDao judgeDao, PosterDao posterDao, AdminDao adminDao) {
        this.judgeDao = judgeDao;
        this.posterDao = posterDao;
        this.adminDao = adminDao;
    }

    /**
     * Creates the provided {@link Object}
     *
     * @param email {@link String}
     * @param pin {@link String}
     * @return {@link Object}
     */
    @ApiOperation(value = "Read Judge by ID")
    @GetMapping(value = LOGIN_PATH)
    public Object create(@PathVariable String email, @PathVariable String pin){

            Judge judge = judgeDao.read(email, pin);
            if(judge != null)
                return judge;

            Poster poster = posterDao.read(email, pin);
            if(poster != null)
                return poster;

            Admin admin = adminDao.read(email, pin);
            if(admin != null)
                return admin;

            return null;
    }


}
