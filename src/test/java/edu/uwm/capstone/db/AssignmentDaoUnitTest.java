package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)

public class AssignmentDaoUnitTest {

    @Autowired
    ApplicationContext applicationContex;

    @Autowired
    AssignmentDao assignmentDao;

    @Autowired
    PosterDao posterDao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    JudgeDao judgeDao;

    @Before
    public void setUp(){
        assignmentDao.sql("getAllAssignments");
    }


    @Test
    public void create(){
        Assignment assignemnt = TestDataUtility.assignmentWithTestValues();
        Assignment createdAssignment = assignmentDao.create(assignemnt);
        assertNull(createdAssignment);
    }
    @Test
    public void readAssignments(){
        Poster poster = TestDataUtility.posterWithTestValues();
        Poster createdPoster = posterDao.create(poster);
        assertNotNull(createdPoster);

        Judge judge = TestDataUtility.judgeWithTestValues();
        Judge createdJudge = judgeDao.create(judge);
        assertNotNull(createdJudge);

        Score score = TestDataUtility.scoreWithTestValues();
        score.setJudge_id(createdJudge.getJudge_id());
        score.setPoster_id(createdPoster.getPoster_id());
        Score createdScore = scoreDao.create(score);
        assertNotNull(createdScore);

        List<Assignment> assignmentsList = assignmentDao.readAssignments(createdScore.getRound());
        assertNotNull(assignmentsList);
        assertTrue(assignmentsList.size() > 0);
        assertEquals(assignmentsList.get(0).getJudge_id(), createdJudge.getJudge_id());
    }
}
