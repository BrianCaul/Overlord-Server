package com.overlord.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="position")
public class Position {

	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=1, max=45)
	private String positionName;
	
	@NotEmpty
	@Size(min=1, max=45)
	private String positionFunction;
	
	@NotEmpty
	@Size(min=1, max=45)
	private String positionType;
	
	@org.codehaus.jackson.annotate.JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name="areaid")
	private Area area;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="position")
    private Set<User> attendants;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="position")
    private Set<Visitor> visitors;	
	
	@Transient
    private int numVisitors;
	

	public Position(){
		
	}
	
	public Position(String positionName){
		this.positionName = positionName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [id=" + id + ", positionName=" + positionName +"]";
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
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}


	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the positionFunction
	 */
	public String getPositionFunction() {
		return positionFunction;
	}

	/**
	 * @param positionFunction the positionFunction to set
	 */
	public void setPositionFunction(String positionFunction) {
		this.positionFunction = positionFunction;
	}

	/**
	 * @return the positionType
	 */
	public String getPositionType() {
		return positionType;
	}

	/**
	 * @param positionType the positionType to set
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	/**
	 * @return the numVisitors
	 */
	public int getNumVisitors() {
		return this.area.getVisitors().size();
	}
	
	/**
	 * @return the numVisitors
	 */
	public void setNumVisitors() {
		this.numVisitors = this.area.getVisitors().size();
	}

	/**
	 * @return the attendants
	 */
	public Set<User> getAttendants() {
		return attendants;
	}

	/**
	 * @param attendants the attendants to set
	 */
	public void setAttendants(Set<User> attendants) {
		this.attendants = attendants;
	}

	/**
	 * @return the visitors
	 */
	public Set<Visitor> getVisitors() {
		return visitors;
	}

	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(Set<Visitor> visitors) {
		this.visitors = visitors;
	}
	
	
}
