package com.example.decoder.theme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.decoder.theme.dao.ThemeDao;
import com.example.decoder.theme.domain.Theme;

@Service
public class ThemeService {
	@Autowired
	ThemeDao themeDao;
	public void add(Theme theme) {
		themeDao.add(theme);
	}
	public Theme get(int id) {
		return themeDao.get(id);
	}
	public List<Theme> getAll(){
		return themeDao.getAll();
	}
	public void delete(int id) {
		themeDao.delete(id);
	}

}
