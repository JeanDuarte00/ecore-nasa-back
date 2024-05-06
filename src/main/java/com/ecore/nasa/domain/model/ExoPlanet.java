package com.ecore.nasa.domain.model;

public record ExoPlanet (
		Integer id,
		String planetName,
		String hostName,
		String discoveryMethod,
		String discoveryYear
) {
	public ExoPlanet(String id, String planetName, String hostName, String discoveryMethod, String discoveryYear){
		this(Integer.valueOf(id), planetName, hostName, discoveryMethod, discoveryYear);
	}
}