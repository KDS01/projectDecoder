package com.example.decoder.admin.service;

import java.security.MessageDigest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.decoder.admin.dao.AdminDao;
import com.example.decoder.admin.domain.Admin;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	public void add(Admin admin) {
		admin.setUser_password(cryptoPassword(admin.getUser_password()));
		adminDao.add(admin);
	}
	public Admin get(String user_id) {
		return adminDao.get(user_id);
	}
	public List<Admin> getAll(){
		return adminDao.getAll();
	}
	private String cryptoPassword(String password) {
		try {
			MessageDigest md =MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] sha256Hash=md.digest();
			StringBuilder sb= new StringBuilder();
			for(byte b: sha256Hash) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
	}catch(Exception e) {
		e.printStackTrace();
		return null;
		}

	}
	public Admin login(Admin admin) {
		Admin tempAdmin = adminDao.get(admin.getUser_id());
		if(tempAdmin!=null&&tempAdmin.getUser_password().equals(cryptoPassword(admin.getUser_password()))){
			return tempAdmin;	
		}
		else return null;
	}
	public void logOut(HttpSession session) {
		session.invalidate();
	}
	
	
	
}
