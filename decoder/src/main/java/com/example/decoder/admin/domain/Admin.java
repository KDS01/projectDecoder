package com.example.decoder.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	private int id;
	@NonNull
	private String user_id;
	@NonNull
	private String user_password;
	@NonNull
	private String name;
	

}
