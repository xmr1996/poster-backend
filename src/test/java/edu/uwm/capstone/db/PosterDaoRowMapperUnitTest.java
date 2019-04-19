package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class PosterDaoRowMapperUnitTest {

    @Autowired
    PosterDaoRowMapper posterDaoRowMapper;

    @Mock
    private ResultSet resultSet;

    /**
     * Verify that {@link PosterDaoRowMapper.PosterColumnType#values()} is working correctly.
     */
    @Test
    public void posterColumnType() {
        for (PosterDaoRowMapper.PosterColumnType columnType : PosterDaoRowMapper.PosterColumnType.values()) {
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link PosterDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject() {
        // generate a profile object with test values
        Poster poster = TestDataUtility.posterWithTestValues();
        assertNotNull(poster);

        Map<String, Object> mapObject = posterDaoRowMapper.mapObject(poster);
        assertNotNull(mapObject);

        assertEquals(poster.getPosterId(), mapObject.get(PosterDaoRowMapper.PosterColumnType.POSTER_ID.getColumnName()));
        assertEquals(poster.getTitle(), mapObject.get(PosterDaoRowMapper.PosterColumnType.TITLE.getColumnName()));
        assertEquals(poster.getEmail(), mapObject.get(PosterDaoRowMapper.PosterColumnType.EMAIL.getColumnName()));
        assertEquals(poster.getFirstName(), mapObject.get(PosterDaoRowMapper.PosterColumnType.FIRST_NAME.getColumnName()));
        assertEquals(poster.getLastName(), mapObject.get(PosterDaoRowMapper.PosterColumnType.LAST_NAME.getColumnName()));
        assertEquals(poster.getStatus(), mapObject.get(PosterDaoRowMapper.PosterColumnType.STATUS.getColumnName()));
        assertEquals(poster.getPin(), mapObject.get(PosterDaoRowMapper.PosterColumnType.PIN.getColumnName()));
        assertEquals(poster.getDepartment(), mapObject.get(PosterDaoRowMapper.PosterColumnType.DEPARTMENT.getColumnName()));
        assertEquals(poster.getVotedFor(), mapObject.get(PosterDaoRowMapper.PosterColumnType.VOTED_FOR.getColumnName()));
        assertEquals(poster.getRole(), mapObject.get(PosterDaoRowMapper.PosterColumnType.ROLE.getColumnName()));
        assertEquals(poster.getAvgR1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_R1.getColumnName()));
        assertEquals(poster.getAvgR2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_R2.getColumnName()));
        assertEquals(poster.getAvgCommR1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R1.getColumnName()));
        assertEquals(poster.getAvgCommR2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R2.getColumnName()));
        assertEquals(poster.getAvgResearchR1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R1.getColumnName()));
        assertEquals(poster.getAvgResearchR2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R2.getColumnName()));
        assertEquals(poster.getAvgPresR1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R1.getColumnName()));
        assertEquals(poster.getAvgPresR2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R2.getColumnName()));
    }

    /**
     * Verify that {@link PosterDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException {
        // generate a profile object with test values
        Poster poster = TestDataUtility.posterWithTestValues();
        assertNotNull(poster);

        // define the behavior of the resultSet that is being mocked
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.POSTER_ID.getColumnName())).thenReturn(poster.getPosterId());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.EMAIL.getColumnName())).thenReturn(poster.getEmail());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.TITLE.getColumnName())).thenReturn(poster.getTitle());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.FIRST_NAME.getColumnName())).thenReturn(poster.getFirstName());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.LAST_NAME.getColumnName())).thenReturn(poster.getLastName());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.STATUS.getColumnName())).thenReturn(poster.getStatus());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.PIN.getColumnName())).thenReturn(poster.getPin());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.DEPARTMENT.getColumnName())).thenReturn(poster.getDepartment());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.VOTED_FOR.getColumnName())).thenReturn(poster.getVotedFor());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.ROLE.getColumnName())).thenReturn(poster.getRole());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_R1.getColumnName())).thenReturn(poster.getAvgR1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_R2.getColumnName())).thenReturn(poster.getAvgR2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R1.getColumnName())).thenReturn(poster.getAvgCommR1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R2.getColumnName())).thenReturn(poster.getAvgCommR2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R1.getColumnName())).thenReturn(poster.getAvgResearchR1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R2.getColumnName())).thenReturn(poster.getAvgResearchR2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R1.getColumnName())).thenReturn(poster.getAvgPresR1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R2.getColumnName())).thenReturn(poster.getAvgPresR2());

        // exercise the mapRow functionality and verify the expected results
        Poster verifyProfile = posterDaoRowMapper.mapRow(resultSet, 0);
        assertNotNull(verifyProfile);

        assertEquals(poster.getPosterId(), verifyProfile.getPosterId());
        assertEquals(poster.getEmail(), verifyProfile.getEmail());
        assertEquals(poster.getTitle(), verifyProfile.getTitle());
        assertEquals(poster.getFirstName(), verifyProfile.getFirstName());
        assertEquals(poster.getLastName(), verifyProfile.getLastName());
        assertEquals(poster.getStatus(), verifyProfile.getStatus());
        assertEquals(poster.getPin(), verifyProfile.getPin());
        assertEquals(poster.getDepartment(), verifyProfile.getDepartment());
        assertEquals(poster.getVotedFor(), verifyProfile.getVotedFor());
        assertEquals(poster.getRole(), verifyProfile.getRole());
        assertEquals(poster.getAvgR1(), verifyProfile.getAvgR1());
        assertEquals(poster.getAvgR2(), verifyProfile.getAvgR2());
        assertEquals(poster.getAvgCommR1(), verifyProfile.getAvgCommR1());
        assertEquals(poster.getAvgCommR2(), verifyProfile.getAvgCommR2());
        assertEquals(poster.getAvgResearchR1(), verifyProfile.getAvgResearchR1());
        assertEquals(poster.getAvgResearchR2(), verifyProfile.getAvgResearchR2());
        assertEquals(poster.getAvgPresR1(), verifyProfile.getAvgPresR1());
        assertEquals(poster.getAvgPresR2(), verifyProfile.getAvgPresR2());
    }
}
