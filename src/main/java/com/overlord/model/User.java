package com.overlord.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=4, max=20)
	private String userName;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String phone;
	
	@NotEmpty
	private String userType;
	
	@NotEmpty
	@Size(min=4, max=8)
	private String password;
	
	@NotEmpty
	@Email
	private String email;
	
	@org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name="companyid")
	private Company company;
	
	@org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name="positionid")
	private Position position;
	
	 public User() {       

	 }
	
	 public User(String userName,String name, String userType, String password, String email, String phone) {       
	        this.userName = userName;
	        this.name = name;
	        this.userType = userType;
	        this.password = password;
	        this.email = email;
	        this.phone = phone;
	 }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
    @JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}	
}
