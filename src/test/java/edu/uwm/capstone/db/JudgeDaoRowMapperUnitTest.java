//package edu.uwm.capstone.db;
//
//import edu.uwm.capstone.UnitTestConfig;
//import edu.uwm.capstone.model.Judge.Judge;
//import edu.uwm.capstone.sql.dao.BaseRowMapper;
//import edu.uwm.capstone.util.TestDataUtility;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import static edu.uwm.capstone.sql.dao.BaseRowMapper.dateFromJavaTime;
//import static edu.uwm.capstone.sql.dao.BaseRowMapper.javaTimeFromDate;
//import static edu.uwm.capstone.util.TestDataUtility.randomLocalDateTime;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = UnitTestConfig.class)
//public class JudgeDaoRowMapperUnitTest {
//
//    @Autowired
//    JudgeDaoRowMapper judgeDaoRowMapper;
//
//    @Mock
//    private ResultSet resultSet;
//
//    @Before
//    public void setUp() throws IOException {
//        assertNotNull(judgeDaoRowMapper);
//    }
//
//    /**
//     * Verify that {@link JudgeDaoRowMapper.JudgeColumnType#values()} is working correctly.
//     */
//    @Test
//    public void judgeColumnType() {
//        for (JudgeDaoRowMapper.JudgeColumnType columnType : JudgeDaoRowMapper.JudgeColumnType.values()) {
//            assertNotNull(columnType.name());
//            assertNotNull(columnType.getColumnName());
//        }
//    }
//
//    /**
//     * Verify that {@link JudgeDaoRowMapper#mapObject} is working correctly.
//     */
//    @Test
//    public void mapObject() {
//        // generate a profile object with test values
//        Judge judge = TestDataUtility.judgeWithTestValues();
//        judge.setId(TestDataUtility.randomLong());
//        assertNotNull(judge);
//
//        Map<String, Object> mapObject = judgeDaoRowMapper.mapObject(judge);
//        assertNotNull(mapObject);
//
//        assertEquals(judge.getId(), mapObject.get(BaseRowMapper.BaseColumnType.ID.getColumnName()));
//        assertEquals(judge.getFirst_name(), mapObject.get(JudgeDaoRowMapper.JudgeColumnType.FIRST_NAME.getColumnName()));
//        assertEquals(judge.getLast_name(), mapObject.get(JudgeDaoRowMapper.JudgeColumnType.LAST_NAME.getColumnName()));
//        assertEquals(judge.getEmail(), mapObject.get(JudgeDaoRowMapper.JudgeColumnType.EMAIL.getColumnName()));
//        assertEquals(judge.getPin(), mapObject.get(JudgeDaoRowMapper.JudgeColumnType.PIN.getColumnName()));
//        assertEquals(judge.getStatus(), mapObject.get(JudgeDaoRowMapper.JudgeColumnType.STATUS.getColumnName()));
//    }
//
//    /**
//     * Verify that {@link ProfileDaoRowMapper#mapRow} is working correctly.
//     */
//    @Test
//    public void mapRow() throws SQLException {
//        // generate a profile object with test values
//        Judge judge = TestDataUtility.judgeWithTestValues();
//        judge.setId(TestDataUtility.randomLong());
//        assertNotNull(judge);
//
//        // define the behavior of the resultSet that is being mocked
//        when(resultSet.getLong(BaseRowMapper.BaseColumnType.ID.getColumnName())).thenReturn(judge.getId());
//        when(resultSet.getString(JudgeDaoRowMapper.JudgeColumnType.FIRST_NAME.getColumnName())).thenReturn(judge.getFirst_name());
//        when(resultSet.getString(JudgeDaoRowMapper.JudgeColumnType.LAST_NAME.getColumnName())).thenReturn(judge.getLast_name());
//        when(resultSet.getString(JudgeDaoRowMapper.JudgeColumnType.EMAIL.getColumnName())).thenReturn(judge.getEmail());
//        when(resultSet.getInt(JudgeDaoRowMapper.JudgeColumnType.PIN.getColumnName())).thenReturn(judge.getPin());
//
//        // exercise the mapRow functionality and verify the expected results
//        Judge verifyJudge = judgeDaoRowMapper.mapRow(resultSet, 0);
//        assertNotNull(verifyJudge);
//
//        assertEquals(judge.getId(), verifyJudge.getId());
//        assertEquals(judge.getFirst_name(), verifyJudge.getFirst_name());
//        assertEquals(judge.getLast_name(), verifyJudge.getLast_name());
//        assertEquals(judge.getEmail(), verifyJudge.getEmail());
//        assertEquals(judge.getPin(), verifyJudge.getPin());
//    }
//}
