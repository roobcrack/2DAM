package com.ruben.xmljson.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {
	private String nombre;
	private String height;
	private List<String> films;
}
