package com.example.decoder.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.decoder.book.domain.Book;

@Repository
public class BookDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper<Book> mapper = new RowMapper<Book>() {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Book(
					rs.getInt("id"),
					rs.getString("booked_time"),
					rs.getDate("booked_date"),
					rs.getString("booked_name"),
					rs.getString("phone"),
					rs.getString("email"),
					rs.getInt("people_count"),
					rs.getInt("booked_theme"),
					rs.getInt("timeLines_id")	
					);
		}
	};
	public void add(Book book) {
		jdbcTemplate.update("insert into projectDecoder.booked_time"
				+ "(id,"
				+ "booked_time,"
				+ "booked_date,"
				+ "booked_name,"
				+ "phone,"
				+ "email,"
				+ "people_count,"
				+ "booked_theme,"
				+ "timeLines_id)"
				+ "values(?,?,?,?,?,?,?,?,?)",
				book.getId(),
				book.getBooked_time(),
				book.getBooked_date(),
				book.getBooked_name(),
				book.getPhone(),
				book.getEmail(),
				book.getPeople_count(),
				book.getBooked_theme(),
				book.getTimeLine_id()
				);
	}
	public Book get(int id) {
		String query="select a.*,b.* from projectdecoder.booked_time a"
				+ "join projectdecoder.theme_timelines b on a.booked_theme=b.theme_id where a.id=?";
		return jdbcTemplate.queryForObject(query,mapper,id);
	}
	public List<Book> getDateBook(Date date){
		String query="select * from projectdecoder.booked_time where booked_date =?";
		return jdbcTemplate.query(query,mapper,date);
	}
	public List<Book> getThemeBook(int theme_Id){
		String query="select * from projectdecoder.booked_time where booked_theme =?";
		return jdbcTemplate.query(query,mapper,theme_Id);
	}
	public void bookCancel(int id) {
		jdbcTemplate.update("delete from projectdecoder.booked_time where id=?",id);
	}

}
