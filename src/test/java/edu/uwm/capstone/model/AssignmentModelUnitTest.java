package edu.uwm.capstone.model;

import edu.uwm.capstone.model.Assignment.Assignment;
import org.junit.Test;
import static edu.uwm.capstone.helper.TestHelper.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssignmentModelUnitTest {

    @Test
    public void getValidPosterIdUnitTest() {
        Assignment assignment = new Assignment();
        String posterId = randomNumeric(5);
        assignment.setPoster_id(posterId);
        assertTrue(assignment.getPoster_id().toString() == posterId);
    }

    @Test
    public void getInValidPosterIdUnitTest() {
        Assignment assignment = new Assignment();
        String posterId = randomNumeric( 5);
        assignment.setPoster_id(posterId);
        assertFalse(assignment.getPoster_id().toString() == randomNumeric(5));
    }

    @Test
    public void getValidJudgeIdUnitTest() {
        Assignment assignment = new Assignment();
        Long judgeId = randomLong();
        assignment.setJudge_id(judgeId);
        assertTrue(assignment.getJudge_id() == judgeId);
    }

    @Test
    public void getInValidJudgeIdUnitTest() {
        Assignment assignment = new Assignment();
        Long judgeId = randomLong();
        assignment.setJudge_id(judgeId);
        assertFalse(assignment.getJudge_id() == randomLong());
    }


}
