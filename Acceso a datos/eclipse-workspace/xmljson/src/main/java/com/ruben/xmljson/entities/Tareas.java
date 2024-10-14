package com.ruben.xmljson.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tareas {

	private long userId;
	private long id;
	private String title;
	private boolean completed;
}