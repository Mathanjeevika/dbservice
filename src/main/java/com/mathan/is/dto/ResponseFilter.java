package com.mathan.is.dto;

public class ResponseFilter {

	private Integer id;
	private String name;

	public ResponseFilter(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ResponseFilter() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
