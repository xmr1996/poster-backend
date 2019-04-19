package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static edu.uwm.capstone.db.AdminDaoRowMapper.AdminColumnType.*;

public class AdminDaoRowMapper extends BaseRowMapper<Admin> {

    public enum AdminColumnType {
        EMAIL(),
        FIRST_NAME(),
        LAST_NAME(),
        READ_R(),
        WRITE_W(),
        PIN(),
        ROLE(),
        ;

        private String columnName;

        AdminColumnType() {
            columnName = name().toLowerCase();
        }

        AdminColumnType(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName() {
            return columnName;
        }
    }

    public Map<String, Object> mapObject(Admin object) {
        Map<String, Object> map = new HashMap<>();
        map.put(EMAIL.getColumnName(), object.getEmail());
        map.put(FIRST_NAME.getColumnName(), object.getFirstName());
        map.put(LAST_NAME.getColumnName(), object.getLastName());
        map.put(READ_R.getColumnName(), object.getCanRead());
        map.put(WRITE_W.getColumnName(), object.getCanWrite());
        map.put(PIN.getColumnName(), object.getPin());
        map.put(ROLE.getColumnName(), object.getRole());
        return map;
    }

    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin folder = new Admin();
        folder.setEmail(rs.getString(EMAIL.getColumnName()));
        folder.setFirstName(rs.getString(FIRST_NAME.getColumnName()));
        folder.setLastName(rs.getString(LAST_NAME.getColumnName()));
        folder.setCanRead(rs.getBoolean(READ_R.getColumnName()));
        folder.setCanWrite(rs.getBoolean(WRITE_W.getColumnName()));
        folder.setPin(rs.getString(PIN.getColumnName()));
        folder.setRole(rs.getString(ROLE.getColumnName()));
        return folder;
    }


}
