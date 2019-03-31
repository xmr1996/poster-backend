package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginRestController {

    public static final String LOGIN_PATH = "/login/{email}/{pin}";

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    private final JudgeDao judgeDao;
    private final PosterDao posterDao;

    @Autowired
    public LoginRestController(JudgeDao judgeDao, PosterDao posterDao) {
        this.judgeDao = judgeDao;
        this.posterDao = posterDao;
    }

    /**
     * Creates the provided {@link Object}
     *
     * @param email {@link String}
     * @param pin {@link String}
     * @param response {@link HttpServletResponse}
     * @return {@link Object}
     * @throws IOException if error response cannot be created.
     */
    @ApiOperation(value = "Read Judge by ID")
    @GetMapping(value = LOGIN_PATH)
    public Object create(@PathVariable String email, @PathVariable String pin, @ApiIgnore HttpServletResponse response) throws IOException {

            Judge judge = judgeDao.read(email, pin);
            if(judge != null)
                return judge;

            Poster poster = posterDao.read(email, pin);
            if(poster != null)
                return poster;

            return null;
    }


}
