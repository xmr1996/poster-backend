package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Assignment.Assignment;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.util.TestDataUtility;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.dateFromJavaTime;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.javaTimeFromDate;
import static edu.uwm.capstone.util.TestDataUtility.randomLocalDateTime;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class AssignmentDaoRowMapperUnitTest{
    @Autowired
    AssignmentDaoRowMapper assignmentDaoRowMapper;

    @Mock
    private ResultSet resultset;

    @Before
    public void setUp() throws IOException{
        assertNotNull(assignmentDaoRowMapper);
    }

    /**
     * Verify that {@link AssignmentDaoRowMapper.AssignmentColumnType#values()} is working correctly.
     */
    @Test
    public void AssignmentColumnType() {
        for(AssignmentDaoRowMapper.AssignmentColumnType columnType : AssignmentDaoRowMapper.AssignmentColumnType.values()){
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }


    /**
     * Verify that {@link AssignmentDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject(){
        Assignment assignment = TestDataUtility.assignmentWithTestValues();
        assertNotNull(assignment);

        Map<String, Object> mapObject = assignmentDaoRowMapper.mapObject(assignment);
        assertNull(mapObject);

    }


    /**
     * Verify that {@link AssignmentDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException{
        Assignment assignment = TestDataUtility.assignmentWithTestValues();
        assertNotNull(assignment);

        when(resultset.getString(AssignmentDaoRowMapper.AssignmentColumnType.POSTER_ID.getColumnName())).thenReturn(assignment.getPoster_id());
        when(resultset.getLong(AssignmentDaoRowMapper.AssignmentColumnType.JUDGE_ID.getColumnName())).thenReturn(assignment.getJudge_id());

        Assignment verifyResult = assignmentDaoRowMapper.mapRow(resultset,0);
        assertNotNull(verifyResult);

        assertEquals(assignment.getJudge_id(),verifyResult.getJudge_id());
        assertEquals(assignment.getPoster_id(),verifyResult.getPoster_id());
    }

}