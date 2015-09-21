package com.overlord.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="event")
public class Event {

	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=1, max=45)
	private String eventName;
	
	@Size(max=255)
	private String description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reset;
	
	@NotNull
	private int capacity;
	
	@org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name="companyid")
	private Company company;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="event")
    private Set<Venue> venues;	
	

	public Event(){
		
	}
	
	public Event(String eventName){
		this.eventName = eventName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + "]";
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
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	 * @return the venues
	 */
	public Set<Venue> getVenues() {
		return venues;
	}

	/**
	 * @param venues the venues to set
	 */
	public void setVenues(Set<Venue> venues) {
		this.venues = venues;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @return the reset
	 */
	public Date getReset() {
		return reset;
	}

	/**
	 * @param reset the reset to set
	 */
	public void setReset(Date reset) {
		this.reset = reset;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
