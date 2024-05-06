package com.ecore.nasa.domain.repository;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.infrastructure.repository.entity.ExoPlanetEntity;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface IExoPlanetRepository {
	void add (ExoPlanet object);
	void addAll (Set<ExoPlanet> objects);
	List<ExoPlanet> getAll (PageRequest pageRequest);
	List<ExoPlanet> getAllQueryble (String planetName, String hostName, String discoveryMethod, String discoveryYear);
	List<ExoPlanet> getAllByPlanetName(PageRequest pageRequest, String name);
	List<ExoPlanet> getAllByHostName(PageRequest pageRequest, String host);
	List<ExoPlanet> getAllByDiscoveryMethod(PageRequest pageRequest, String method);
	List<ExoPlanet> getAllByDiscoveryYear(PageRequest pageRequest, String year);
}
