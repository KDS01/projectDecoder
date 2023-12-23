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
					rs.getString("booked_date"),
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
		jdbcTemplate.update("insert into projectdecoder.booked_time"
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
		String query="SELECT * FROM projectdecoder.booked_time where id=?";
		return jdbcTemplate.queryForObject(query,mapper,id);
	}
	public List<Book> getDateBook(String date){
		String query="select * from projectdecoder.booked_time where booked_date =?";
		return jdbcTemplate.query(query,mapper,date);
	}
	public List<Book> getThemeBook(int theme_Id){
		String query="select * from projectdecoder.booked_time where booked_theme =?";
		return jdbcTemplate.query(query,mapper,theme_Id);
	}
	public void bookCancel(String name,String booked_date,String booked_time) {
		jdbcTemplate.update("delete from projectdecoder.booked_time where booked_name=? and booked_date=? and booked_time=?",name,booked_date,booked_time);
	}
	public List<Book> searchBook(int theme_id,String Booked_date){
		return jdbcTemplate.query("select * from projectdecoder.booked_time where booked_theme=? AND booked_date=?",new Object[] {theme_id,Booked_date},mapper);
	}
}
