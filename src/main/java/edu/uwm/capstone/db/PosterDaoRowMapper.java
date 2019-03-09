package edu.uwm.capstone.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.POSTERTITLE;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHOREMAIL;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHORFIRSTNAME;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHORLASTNAME;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHORSTATUS;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHORPIN;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.AUTHORDEPARTMENT;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class PosterDaoRowMapper extends BaseRowMapper<Poster> {

    public enum PosterColumnType{
        POSTERTITLE(),
        AUTHOREMAIL(),
        AUTHORFIRSTNAME(),
        AUTHORLASTNAME(),
        AUTHORSTATUS(),
        AUTHORPIN(),
        AUTHORDEPARTMENT(),
        ;

        private String columnName;
        PosterColumnType(){
            columnName = name().toLowerCase();
        }

        PosterColumnType(String columnName){
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Poster mapRow(ResultSet rs, int rowNum) throws SQLException {
        Poster post = new Poster();
        post.setPosterID(rs.getString(ID.getColumnName()));
        post.setPosterTitle(rs.getString(POSTERTITLE.getColumnName()));
        post.setAuthorEmail(rs.getString(AUTHOREMAIL.getColumnName()));
        post.setAuthorFirstName(rs.getString(AUTHORFIRSTNAME.getColumnName()));
        post.setAuthorLastName(rs.getString(AUTHORLASTNAME.getColumnName()));
        post.setAuthorStatus(rs.getString(AUTHORSTATUS.getColumnName()));
        post.setAuthorPin(rs.getString(AUTHORPIN.getColumnName()));
        post.setAuthorDepartment(AUTHORDEPARTMENT.getColumnName());
        return post;
    }

    @Override
    public Map<String, Object> mapObject(Poster object) {
        Map<String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(),object.getPosterID());
        map.put(POSTERTITLE.getColumnName(),object.getPosterTitle());
        map.put(AUTHOREMAIL.getColumnName(),object.getAuthorEmail());
        map.put(AUTHORFIRSTNAME.getColumnName(),object.getAuthorFirstName());
        map.put(AUTHORLASTNAME.getColumnName(),object.getAuthorLastName());
        map.put(AUTHORSTATUS.getColumnName(),object.getAuthorStatus());
        map.put(AUTHORPIN.getColumnName(),object.getAuthorPin());
        map.put(AUTHORDEPARTMENT.getColumnName(),object.getAuthorDepartment());
        return map;
    }

}