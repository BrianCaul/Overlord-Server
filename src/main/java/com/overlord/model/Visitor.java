package com.overlord.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="visitor")
public class Visitor {

	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date entryTime;
	
	@NotEmpty
	@Size(min=1, max=45)
	private String exited;
	
	@org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name="positionid")
	private Position position;


	public Visitor(){
		
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
	 * @return the entryTime
	 */
	public Date getEntryTime() {
		return entryTime;
	}


	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}


	/**
	 * @return the exited
	 */
	public String getExited() {
		return exited;
	}


	/**
	 * @param exited the exited to set
	 */
	public void setExited(String exited) {
		this.exited = exited;
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
