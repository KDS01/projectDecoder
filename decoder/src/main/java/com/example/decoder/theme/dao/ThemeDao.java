package com.example.decoder.theme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.decoder.theme.domain.Theme;

@Repository
public class ThemeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper<Theme> mapper = new RowMapper<Theme>() {
		@Override
	public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Theme(
				rs.getInt("id"),
				rs.getString("title"),
				rs.getString("intro"),
				rs.getString("info"),
				rs.getString("main_img"),
				rs.getString("icon_img"));
		}
	};
	public void add(Theme theme) {
		jdbcTemplate.update(
				"insert into projectdecoder.theme ( title, intro, info, main_img, icon_img) values (?,?,?,?,?)",
				theme.getTitle(),
				theme.getIntro(),
				theme.getInfo(),
				theme.getMain_img(),
				theme.getIcon_img()
				);
	}
	public Theme get(int id) {
		String query="select * from projectdecoder.theme where id=?";
		return jdbcTemplate.queryForObject(query,mapper,id);
	}
//	public List<Theme> getAll(){
//		String query="select"
//		return jdbcTemplate.queryForObject("", null)
//	}

}
