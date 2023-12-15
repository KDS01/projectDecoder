package com.example.decoder.book.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
	private int id;
	@NonNull
	private String booked_time;
	@NonNull
	private String booked_date;
	@NonNull
	private String booked_name;
	@NonNull
	private String phone;
	@NonNull
	private String email;
	private int people_count;
	private int booked_theme;
	private int timeLine_id=1;
}
