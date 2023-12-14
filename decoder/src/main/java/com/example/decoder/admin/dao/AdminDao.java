package com.example.decoder.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.decoder.admin.domain.Admin;

@Repository
public class AdminDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper<Admin> mapper = new RowMapper<Admin>() {
		
		@Override
		public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Admin(
					rs.getInt("id"),
					rs.getString("user_id"),
					rs.getString("user_password"),
					rs.getString("name")
					);
		}
	};
	public void add(Admin admin) {
		jdbcTemplate.update("insert into projectdecoder.managers (id, user_id, user_password, name) values (?,?,?,?)",
				admin.getId(),
				admin.getUser_id(),
				admin.getUser_password(),
				admin.getName()
				);
	}
	public Admin get(String user_id) {
		return jdbcTemplate.queryForObject("select * from projectdecoder.managers where user_id=?",mapper,user_id);
	}
	public List<Admin> getAll(){
		return jdbcTemplate.query("select * from projectdecoder.managers", mapper);
	}
	public void delete(int id) {
		jdbcTemplate.update("delete from projectdecoder.managers where id=?",id);
	}

}
