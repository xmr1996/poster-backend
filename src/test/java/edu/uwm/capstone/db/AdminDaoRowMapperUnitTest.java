package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Admin.Admin;

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
import edu.uwm.capstone.util.TestDataUtility;

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
        assertEquals(admin.getLastName(),mapObject.get(AdminDaoRowMapper.AdminColumnType.LAST_NAME.getColumnName()));
        assertEquals(admin.getFirstName(),mapObject.get(AdminDaoRowMapper.AdminColumnType.FIRST_NAME.getColumnName()));
        assertEquals(admin.getEmail(),mapObject.get(AdminDaoRowMapper.AdminColumnType.EMAIL.getColumnName()));
        assertEquals(admin.getCanRead(),mapObject.get(AdminDaoRowMapper.AdminColumnType.READ_R.getColumnName()));
        assertEquals(admin.getCanWrite(),mapObject.get(AdminDaoRowMapper.AdminColumnType.WRITE_W.getColumnName()));
    }

    /**
     * Verify that {@link ScoreDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException{
        Admin admin = TestDataUtility.adminWithTestValues();
        assertNotNull(admin);

        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.EMAIL.getColumnName())).thenReturn(admin.getEmail());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.FIRST_NAME.getColumnName())).thenReturn(admin.getFirstName());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.LAST_NAME.getColumnName())).thenReturn(admin.getLastName());
        when(resultset.getString(AdminDaoRowMapper.AdminColumnType.PIN.getColumnName())).thenReturn(admin.getPin());
        when(resultset.getBoolean(AdminDaoRowMapper.AdminColumnType.WRITE_W.getColumnName())).thenReturn(admin.getCanWrite());
        when(resultset.getBoolean(AdminDaoRowMapper.AdminColumnType.READ_R.getColumnName())).thenReturn(admin.getCanRead());

        Admin verifyResult = adminDaoRowMapper.mapRow(resultset,0);
        assertNotNull(verifyResult);

        assertEquals(admin.getEmail(),verifyResult.getEmail());
        assertEquals(admin.getFirstName(),verifyResult.getFirstName());
        assertEquals(admin.getLastName(),verifyResult.getLastName());
        assertEquals(admin.getPin(),verifyResult.getPin());
        assertEquals(admin.getCanWrite(),verifyResult.getCanWrite());
        assertEquals(admin.getCanRead(),verifyResult.getCanRead());

    }
}

