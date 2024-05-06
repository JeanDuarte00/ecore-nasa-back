package com.ecore.nasa.infrastructure.repository;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import com.ecore.nasa.infrastructure.repository.entity.ExoPlanetEntity;
import com.ecore.nasa.infrastructure.repository.mapper.ExoPlanetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("ExoPlanetSQLRepository")
public class ExoPlanetSQLRepository implements IExoPlanetRepository {

	private final IExoPlanetSQLRepository repository;

	@Autowired
	public ExoPlanetSQLRepository (IExoPlanetSQLRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ExoPlanet> getAll (PageRequest pageRequest) {
		List<ExoPlanetEntity> entityList = this.repository.findAll(pageRequest).stream().toList();
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}

	@Override
	public List<ExoPlanet> getAllQueryble (String planetName, String hostName, String discoveryMethod, String discoveryYear) {
		List<ExoPlanetEntity> entityList = this.repository.getAllQueryble(planetName, hostName, discoveryMethod, discoveryYear).stream().toList();
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}

	@Override
	public void add (ExoPlanet object) {
		ExoPlanetEntity entity = ExoPlanetMapper.map(object);
		this.repository.save(entity);
	}

	@Override
	public void addAll (Set<ExoPlanet> objects) {
		var mapped = objects.stream().map(ExoPlanetMapper::map).toList();
		this.repository.saveAll(mapped);
	}

	@Override
	public List<ExoPlanet> getAllByPlanetName (PageRequest pageRequest, String name) {
		List<ExoPlanetEntity> entityList = this.repository.getAllByPlanetName(name, pageRequest);
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}

	@Override
	public List<ExoPlanet> getAllByHostName (PageRequest pageRequest, String host) {
		List<ExoPlanetEntity> entityList = this.repository.getAllByHostName(host, pageRequest);
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}

	@Override
	public List<ExoPlanet> getAllByDiscoveryMethod (PageRequest pageRequest, String method) {
		List<ExoPlanetEntity> entityList = this.repository.getAllByDiscoveryMethod(method, pageRequest);
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}

	@Override
	public List<ExoPlanet> getAllByDiscoveryYear (PageRequest pageRequest, String year) {
		List<ExoPlanetEntity> entityList = this.repository.getAllByDiscoveryYear(year, pageRequest);
		return entityList.stream().map(ExoPlanetMapper::map).toList();
	}
}
