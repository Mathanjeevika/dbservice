package com.mathan.is.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mathan-Jeevika
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO{

	private String doorNo;
	private String laneName1;
	private String laneName2;
	private String post;
	private String district;
	private String state;
	private String country;

	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getLaneName1() {
		return laneName1;
	}
	public void setLaneName1(String laneName1) {
		this.laneName1 = laneName1;
	}
	public String getLaneName2() {
		return laneName2;
	}
	public void setLaneName2(String laneName2) {
		this.laneName2 = laneName2;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "AddressDTO [doorNo=" + doorNo + ", laneName1=" + laneName1 + ", laneName2=" + laneName2 + ", post="
				+ post + ", district=" + district + ", state=" + state + ", country=" + country + "]";
	}
	
	
}
