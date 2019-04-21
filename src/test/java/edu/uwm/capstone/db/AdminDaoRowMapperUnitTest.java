package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.model.Score.Score;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.util.TestDataUtility;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.dateFromJavaTime;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.javaTimeFromDate;
import static edu.uwm.capstone.util.TestDataUtility.randomLocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)

public class AdminDaoRowMapperUnitTest {
    @Autowired
    AdminDaoRowMapper adminDaoRowMapper;

    @Mock
    private ResultSet resultset;

    @Before
    public void setUp() throws IOException {
        assertNotNull(adminDaoRowMapper);
    }
    /**
     * Verify that {@link AdminDaoRowMapper.AdminColumnType} is working correctly.
     */
    @Test
    public void AdminColumnType() {
        for(AdminDaoRowMapper.AdminColumnType columnType : AdminDaoRowMapper.AdminColumnType.values()){
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link AdminDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject(){
        Admin admin = TestDataUtility.adminWithTestValues();
        assertNotNull(admin);

        Map<String, Object> mapObject = adminDaoRowMapper.mapObject(admin);

        assertEquals(admin.getRole(),mapObject.get(AdminDaoRowMapper.AdminColumnType.ROLE.getColumnName()));
        assertEquals(admin.getPin(),mapObject.get(AdminDaoRowMapper.AdminColumnType.PIN.getColumnName()));
        assertEquals(admin.getLast_name(),mapObject.get(AdminDaoRowMapper.AdminColumnType.LAST_NAME.getColumnName()));
        assertEquals(admin.getFirst_name(),mapObject.get(AdminDaoRowMapper.AdminColumnType.FIRST_NAME.getColumnName()));
        assertEquals(admin.getEmail(),mapObject.get(AdminDaoRowMapper.AdminColumnType.EMAIL.getColumnName()));
        assertEquals(admin.isRead_r(),mapObject.get(AdminDaoRowMapper.AdminColumnType.READ_R.getColumnName()));
        assertEquals(admin.isWrite_w(),mapObject.get(AdminDaoRowMapper.AdminColumnType.WRITE_W.getColumnName()));
    }

    /**
     * Verify that {@link ScoreDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException{
        Admin admin = TestDataUtility.adminWithTestValues();
        assertNotNull(admin);

        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.EMAIL.getColumnName())).thenReturn(admin.getEmail());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.FIRST_NAME.getColumnName())).thenReturn(admin.getFirst_name());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.LAST_NAME.getColumnName())).thenReturn(admin.getLast_name());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.PIN.getColumnName())).thenReturn(admin.getPin());
        when(resultset.getBoolean(AdminDaoRowMapper.AdminColumnType.WRITE_W.getColumnName())).thenReturn(admin.isWrite_w());
        when(resultset.getBoolean(AdminDaoRowMapper.AdminColumnType.READ_R.getColumnName())).thenReturn(admin.isRead_r());

        Admin verifyResult = adminDaoRowMapper.mapRow(resultset,0);
        assertNotNull(verifyResult);

        assertEquals(admin.getEmail(),verifyResult.getEmail());
        assertEquals(admin.getFirst_name(),verifyResult.getFirst_name());
        assertEquals(admin.getLast_name(),verifyResult.getLast_name());
        assertEquals(admin.getPin(),verifyResult.getPin());
        assertEquals(admin.isWrite_w(),verifyResult.isWrite_w());
        assertEquals(admin.isRead_r(),verifyResult.isRead_r());

    }
}

