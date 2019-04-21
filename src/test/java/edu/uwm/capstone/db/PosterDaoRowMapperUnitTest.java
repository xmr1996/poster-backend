package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static edu.uwm.capstone.sql.dao.BaseRowMapper.dateFromJavaTime;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.javaTimeFromDate;
import static edu.uwm.capstone.util.TestDataUtility.randomLocalDateTime;
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

    @Before
    public void setUp() throws IOException {
        assertNotNull(posterDaoRowMapper);
    }

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

        assertEquals(poster.getPoster_id(), mapObject.get(PosterDaoRowMapper.PosterColumnType.POSTER_ID.getColumnName()));
        assertEquals(poster.getTitle(), mapObject.get(PosterDaoRowMapper.PosterColumnType.TITLE.getColumnName()));
        assertEquals(poster.getEmail(), mapObject.get(PosterDaoRowMapper.PosterColumnType.EMAIL.getColumnName()));
        assertEquals(poster.getFirst_name(), mapObject.get(PosterDaoRowMapper.PosterColumnType.FIRST_NAME.getColumnName()));
        assertEquals(poster.getLast_name(), mapObject.get(PosterDaoRowMapper.PosterColumnType.LAST_NAME.getColumnName()));
        assertEquals(poster.getStatus(), mapObject.get(PosterDaoRowMapper.PosterColumnType.STATUS.getColumnName()));
        assertEquals(poster.getPin(), mapObject.get(PosterDaoRowMapper.PosterColumnType.PIN.getColumnName()));
        assertEquals(poster.getDepartment(), mapObject.get(PosterDaoRowMapper.PosterColumnType.DEPARTMENT.getColumnName()));
        assertEquals(poster.getVoted_for(), mapObject.get(PosterDaoRowMapper.PosterColumnType.VOTED_FOR.getColumnName()));
        assertEquals(poster.getRole(), mapObject.get(PosterDaoRowMapper.PosterColumnType.ROLE.getColumnName()));
        assertEquals(poster.getAvg_r1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_R1.getColumnName()));
        assertEquals(poster.getAvg_r2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_R2.getColumnName()));
        assertEquals(poster.getAvg_comm_r1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R1.getColumnName()));
        assertEquals(poster.getAvg_comm_r2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R2.getColumnName()));
        assertEquals(poster.getAvg_research_r1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R1.getColumnName()));
        assertEquals(poster.getAvg_research_r2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R2.getColumnName()));
        assertEquals(poster.getAvg_pres_r1(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R1.getColumnName()));
        assertEquals(poster.getAvg_pres_r2(), mapObject.get(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R2.getColumnName()));


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
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.POSTER_ID.getColumnName())).thenReturn(poster.getPoster_id());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.EMAIL.getColumnName())).thenReturn(poster.getEmail());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.TITLE.getColumnName())).thenReturn(poster.getTitle());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.FIRST_NAME.getColumnName())).thenReturn(poster.getFirst_name());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.LAST_NAME.getColumnName())).thenReturn(poster.getLast_name());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.STATUS.getColumnName())).thenReturn(poster.getStatus());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.PIN.getColumnName())).thenReturn(poster.getPin());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.DEPARTMENT.getColumnName())).thenReturn(poster.getDepartment());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.VOTED_FOR.getColumnName())).thenReturn(poster.getVoted_for());
        when(resultSet.getString(PosterDaoRowMapper.PosterColumnType.ROLE.getColumnName())).thenReturn(poster.getRole());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_R1.getColumnName())).thenReturn(poster.getAvg_r1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_R2.getColumnName())).thenReturn(poster.getAvg_r2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R1.getColumnName())).thenReturn(poster.getAvg_comm_r1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_COMM_R2.getColumnName())).thenReturn(poster.getAvg_comm_r2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R1.getColumnName())).thenReturn(poster.getAvg_research_r1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_RESEARCH_R2.getColumnName())).thenReturn(poster.getAvg_research_r2());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R1.getColumnName())).thenReturn(poster.getAvg_pres_r1());
        when(resultSet.getDouble(PosterDaoRowMapper.PosterColumnType.AVG_PRES_R2.getColumnName())).thenReturn(poster.getAvg_pres_r2());

        // exercise the mapRow functionality and verify the expected results
        Poster verifyProfile = posterDaoRowMapper.mapRow(resultSet, 0);
        assertNotNull(verifyProfile);

        assertEquals(poster.getPoster_id(), verifyProfile.getPoster_id());
        assertEquals(poster.getEmail(), verifyProfile.getEmail());
        assertEquals(poster.getTitle(), verifyProfile.getTitle());
        assertEquals(poster.getFirst_name(), verifyProfile.getFirst_name());
        assertEquals(poster.getLast_name(), verifyProfile.getLast_name());
        assertEquals(poster.getStatus(), verifyProfile.getStatus());
        assertEquals(poster.getPin(), verifyProfile.getPin());
        assertEquals(poster.getDepartment(), verifyProfile.getDepartment());
        assertEquals(poster.getVoted_for(), verifyProfile.getVoted_for());
        assertEquals(poster.getRole(), verifyProfile.getRole());
        assertEquals(poster.getAvg_r1(), verifyProfile.getAvg_r1());
        assertEquals(poster.getAvg_r2(), verifyProfile.getAvg_r2());
        assertEquals(poster.getAvg_comm_r1(), verifyProfile.getAvg_comm_r1());
        assertEquals(poster.getAvg_comm_r2(), verifyProfile.getAvg_comm_r2());
        assertEquals(poster.getAvg_research_r1(), verifyProfile.getAvg_research_r1());
        assertEquals(poster.getAvg_research_r2(), verifyProfile.getAvg_research_r2());
        assertEquals(poster.getAvg_pres_r1(), verifyProfile.getAvg_pres_r1());
        assertEquals(poster.getAvg_pres_r2(), verifyProfile.getAvg_pres_r2());


    }
}
