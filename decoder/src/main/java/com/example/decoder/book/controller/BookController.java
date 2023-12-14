package com.example.decoder.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.decoder.book.domain.Book;
import com.example.decoder.book.service.BookService;
import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	ThemeService themeService;
	@PostMapping("/book/sendDate")
	@ResponseBody
	public Map<String, Object> getDateData(@RequestParam Map<String,String> data) {
		Map<String, Object> model=new HashMap<String, Object>();
		model.put("path","/book/user");
		model.put("userArea", "userFragment");
		return model;
	}
//	@GetMapping("/book")
//	public String boardMainPage(Model model) {
//		model.addAttribute("title", "book theme");
//		model.addAttribute("path", "/book/book");
//		model.addAttribute("content", "bookFragment");
//		model.addAttribute("bookArea", "dateFragment");
//		model.addAttribute("contentHead", "bookFragmentHead");
//		return "/basic/layout";
//	}
	
	//view 수정후 사용할것
	@GetMapping("/book/{theme_id}")
	public String BookPage(@PathVariable("theme_id")int theme_id,Model model) {
		Theme theme=themeService.get(theme_id);
		model.addAttribute("title",theme.getTitle());
		model.addAttribute("path","/book/book");
		model.addAttribute("content","bookFragment");
		model.addAttribute("bookArea","dateFragment");
		model.addAttribute("userArea", "userFragment");
		model.addAttribute("contentHead","bookFragmentHead");
		model.addAttribute("theme",theme);
		return "/basic/layout";
	}
	@PostMapping("/book/userdata")
	@ResponseBody
	public String bookTheme(@RequestParam Map<String,String> data) {
		Book book=new Book();
		book.setBooked_time(data.get("booked_time"));
	}
	

}
