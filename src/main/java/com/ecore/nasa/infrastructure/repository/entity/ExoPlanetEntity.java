package com.ecore.nasa.infrastructure.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="planets")
@Entity
public class ExoPlanetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String planetName;
	private String hostName;
	private String discoveryMethod;
	private String discoveryYear;

	public ExoPlanetEntity(){}

	public ExoPlanetEntity (Integer id, String planetName, String hostName, String discoveryMethod, String discoveryYear) {
		this.id = id;
		this.planetName = planetName;
		this.hostName = hostName;
		this.discoveryMethod = discoveryMethod;
		this.discoveryYear = discoveryYear;
	}

	public Integer getId () {
		return id;
	}

	public String getPlanetName () {
		return planetName;
	}

	public String getHostName () {
		return hostName;
	}

	public String getDiscoveryMethod () {
		return discoveryMethod;
	}

	public String getDiscoveryYear () {
		return discoveryYear;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public void setPlanetName (String planetName) {
		this.planetName = planetName;
	}

	public void setHostName (String hostName) {
		this.hostName = hostName;
	}

	public void setDiscoveryMethod (String discoveryMethod) {
		this.discoveryMethod = discoveryMethod;
	}

	public void setDiscoveryYear (String discoveryYear) {
		this.discoveryYear = discoveryYear;
	}
}

