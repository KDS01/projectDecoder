package com.example.decoder.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.decoder.book.service.BookService;

@Controller
//@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
//	@PostMapping("/book/sendDate")
//	@ResponseBody
//	public Map<String,String> getDateData(@RequestParam Map<String,String> data) {
//		Map<String,String> mapp = new HashMap<String, String>();
//		mapp.put("date", null);
//		
//		return mapp;
//	}

}
