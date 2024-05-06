package com.ecore.nasa.infrastructure.repository.mapper;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.infrastructure.repository.entity.ExoPlanetEntity;

public class ExoPlanetMapper {

	public static ExoPlanet map(ExoPlanetEntity planet){
		return new ExoPlanet(
				planet.getId(),
				planet.getPlanetName(),
				planet.getHostName(),
				planet.getDiscoveryMethod(),
				planet.getDiscoveryYear()
		);
	}

	public static ExoPlanetEntity map(ExoPlanet planet){
		return new ExoPlanetEntity(
				planet.id(),
				planet.planetName(),
				planet.hostName(),
				planet.discoveryMethod(),
				planet.discoveryYear()
		);
	}
}
