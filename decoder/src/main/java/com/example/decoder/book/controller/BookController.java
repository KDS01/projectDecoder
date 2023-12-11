package com.example.decoder.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.decoder.book.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@PostMapping("/book/sendDate")
	@ResponseBody
	public Model getDateData(@RequestParam Map<String,String> data, Model model) {
		model.addAttribute("title", "book theme");
		model.addAttribute("path", "/book/book");
		model.addAttribute("content", "bookFragment");
		model.addAttribute("userArea", "userFragment");
		model.addAttribute("contentHead", "bookFragmentHead");
		return model;

	}
	@GetMapping("/book")
	public String boardMainPage(Model model) {
		model.addAttribute("title", "book theme");
		model.addAttribute("path", "/book/book");
		model.addAttribute("content", "bookFragment");
		model.addAttribute("bookArea", "dateFragment");
		model.addAttribute("contentHead", "bookFragmentHead");
		return "/basic/layout";
	}

}
