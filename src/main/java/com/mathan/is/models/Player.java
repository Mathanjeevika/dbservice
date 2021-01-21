package com.mathan.is.models;

import javax.persistence.Entity;


@Entity
public class Player extends BaseModel{
	
	private String name;
	private String role;
	private String team;
	private Integer age;
	public Player() {
	}
	public Player(String name, String role, String team, Integer age) {
		super();
		this.name = name;
		this.role = role;
		this.team = team;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
