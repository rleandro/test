package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import jsonview.Views;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Person {

	@JsonView(Views.Public.class)
	private int id;
	@JsonView(Views.Public.class)
	private String name;
	@JsonView(Views.Public.class)
	private String email;
	@JsonView(Views.Public.class)
	private Address address;
	@JsonView(Views.Public.class)
	private Gender gender;
	@JsonView(Views.Public.class)
	MaritalStatus maritalStatus;
	
	public Person(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Enumerated(EnumType.STRING)
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", gender=" + gender
				+ ", maritalStatus=" + maritalStatus + "]";
	}

	
}
