package com.example.decoder.book.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.decoder.book.dao.BookDao;
import com.example.decoder.book.domain.Book;

@Service
public class BookService {
	@Autowired
	BookDao bookDao;
	
	public void add(Book book) {
		bookDao.add(book);
	}
	public Book get(int id) {
		return bookDao.get(id);
	}
	public List<Book> getBooktoDate(String date){
		return bookDao.getDateBook(date);
	}
	public List<Book> getBooktotheme(int theme_id){
		return bookDao.getThemeBook(theme_id);
	}
	public void bookCancel(String name, String booked_date, String booked_time) {
		bookDao.bookCancel(name,booked_date,booked_time);
	}
	public List<Book> searchBook(int theme_id,String booked_date){
		return bookDao.searchBook(theme_id, booked_date);
	}
}
