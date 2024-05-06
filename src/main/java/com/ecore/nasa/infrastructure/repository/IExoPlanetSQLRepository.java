package com.ecore.nasa.infrastructure.repository;

import com.ecore.nasa.infrastructure.repository.entity.ExoPlanetEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExoPlanetSQLRepository extends JpaRepository<ExoPlanetEntity, Integer> {

	List<ExoPlanetEntity> getAllByPlanetName (String name, PageRequest pageRequest);
	List<ExoPlanetEntity> getAllByHostName (String host, PageRequest pageRequest);
	List<ExoPlanetEntity> getAllByDiscoveryMethod (String method, PageRequest pageRequest);
	List<ExoPlanetEntity> getAllByDiscoveryYear (String year, PageRequest pageRequest);

	@Query(value = "SELECT * FROM planets p WHERE p.planet_name = :planet AND p.host_name = :host AND p.discovery_method = :method AND p.discovery_year = :year", nativeQuery = true)
	List<ExoPlanetEntity> getAllQueryble (@Param("planet") String planetName,
	                                      @Param("host") String hostName,
	                                      @Param("method") String discoveryMethod,
	                                      @Param("year") String discoveryYear);

}
