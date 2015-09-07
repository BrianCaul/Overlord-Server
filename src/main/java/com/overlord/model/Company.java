package com.overlord.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;  
import javax.persistence.OneToMany;

import javax.persistence.Table;

import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;  
	 
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=4, max=45)
	private String name;
	
	@NotEmpty
	@Size(min=4, max=255)
	private String address;
	
	@NotEmpty
	@Size(min=4, max=45)
	private String registrationNumber;
	
	@NotEmpty
	@Size(min=4, max=255)
	private String contactDetail;
	
	@JsonUnwrapped
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="company")
    private Set<Event> events;	
	
	@JsonUnwrapped
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="company")
    private Set<User> users;	
	

	public Company(){
		
	}
	
	public Company(String name){
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the events
	 */
	public Set<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the contactDetail
	 */
	public String getContactDetail() {
		return contactDetail;
	}

	/**
	 * @param contactDetail the contactDetail to set
	 */
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
