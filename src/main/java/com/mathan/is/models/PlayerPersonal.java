package com.mathan.is.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PlayerPersonal extends BaseModel {

	private String firstName;
	private String lastName;
	private String middleName;
	private String placeOfBirth;
	private String fatherName;
	private String motherName;
	private String dateOfBirth;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playerId", referencedColumnName = "id")
	private Player player;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Address getAddresses() {
		return address;
	}

	public void setAddresses(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PlayerPersonal [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", placeOfBirth=" + placeOfBirth + ", fatherName=" + fatherName + ", motherName=" + motherName
				+ ", dateOfBirth=" + dateOfBirth + ", player=" + player + ", address=" + address + "]";
	}

	
}
