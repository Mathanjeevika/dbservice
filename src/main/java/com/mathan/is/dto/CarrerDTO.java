package com.mathan.is.dto;

public class CarrerDTO {
	
	private Integer id;
	private String type;
	private String matches;
	private String runs;
	private Integer highScore;
	private Integer noOf100s;
	private Integer noOf50s;
	private Integer noOf200s;
	private Double average;
	private Integer wicketsTaken;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(Integer wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatches() {
		return matches;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public String getRuns() {
		return runs;
	}

	public void setRuns(String runs) {
		this.runs = runs;
	}

	public Integer getHighScore() {
		return highScore;
	}

	public void setHighScore(Integer highScore) {
		this.highScore = highScore;
	}

	public Integer getNoOf100s() {
		return noOf100s;
	}

	public void setNoOf100s(Integer noOf100s) {
		this.noOf100s = noOf100s;
	}

	public Integer getNoOf50s() {
		return noOf50s;
	}

	public void setNoOf50s(Integer noOf50s) {
		this.noOf50s = noOf50s;
	}

	public Integer getNoOf200s() {
		return noOf200s;
	}

	public void setNoOf200s(Integer noOf200s) {
		this.noOf200s = noOf200s;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

}
