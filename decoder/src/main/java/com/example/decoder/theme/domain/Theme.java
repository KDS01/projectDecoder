package com.example.decoder.theme.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Theme {
	private int id;
	@NonNull
	private String title;
	@NonNull
	private String intro;
	@NonNull
	private String info;
	@NonNull
	private String main_img;
	@NonNull
	private String icon_img;
}
