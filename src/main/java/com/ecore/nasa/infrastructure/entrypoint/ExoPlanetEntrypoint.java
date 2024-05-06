package com.ecore.nasa.infrastructure.entrypoint;

import com.ecore.nasa.application.usecase.GetExePlanetsbyPlanetNameUsecase;
import com.ecore.nasa.application.usecase.GetExoPlanetsByDiscoveryMethodUsecase;
import com.ecore.nasa.application.usecase.GetExoPlanetsByDiscoveryYearUsecase;
import com.ecore.nasa.application.usecase.GetExoPlanetsByHostNameUsecase;
import com.ecore.nasa.application.usecase.GetExoPlanetsQuerybleUsecase;
import com.ecore.nasa.application.usecase.GetExoPlanetsUsecase;
import com.ecore.nasa.application.usecase.IUsecase;
import com.ecore.nasa.domain.model.ExoPlanet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nasa")
public class ExoPlanetEntrypoint {

	private final IUsecase<GetExoPlanetsUsecase.Input, GetExoPlanetsUsecase.Output> planetsUsecase;
	private final IUsecase<GetExoPlanetsQuerybleUsecase.Input, GetExoPlanetsQuerybleUsecase.Output> planetsQuerybleUsecase;
	private final IUsecase<GetExoPlanetsByDiscoveryYearUsecase.Input, GetExoPlanetsByDiscoveryYearUsecase.Output> planetsByYearUsecase;
	private final IUsecase<GetExoPlanetsByDiscoveryMethodUsecase.Input, GetExoPlanetsByDiscoveryMethodUsecase.Output> planetsByMethodUsecase;
	private final IUsecase<GetExoPlanetsByHostNameUsecase.Input, GetExoPlanetsByHostNameUsecase.Output> planetsByHostUsecase;
	private final IUsecase<GetExePlanetsbyPlanetNameUsecase.Input, GetExePlanetsbyPlanetNameUsecase.Output> planetsByPlanetNameUsecase;

	@Autowired
	public ExoPlanetEntrypoint (GetExoPlanetsUsecase getExoPlanetsUsecase,
	                            GetExoPlanetsQuerybleUsecase planetsQuerybleUsecase,
	                            GetExoPlanetsByDiscoveryYearUsecase planetsByYearUsecase,
	                            GetExoPlanetsByDiscoveryMethodUsecase planetsByMethodUsecase,
	                            GetExoPlanetsByHostNameUsecase planetsByHostUsecase,
	                            GetExePlanetsbyPlanetNameUsecase planetsByPlanetNameUsecase){
		this.planetsUsecase = getExoPlanetsUsecase;
		this.planetsQuerybleUsecase = planetsQuerybleUsecase;
		this.planetsByYearUsecase = planetsByYearUsecase;
		this.planetsByMethodUsecase = planetsByMethodUsecase;
		this.planetsByHostUsecase = planetsByHostUsecase;
		this.planetsByPlanetNameUsecase = planetsByPlanetNameUsecase;
	}

	@GetMapping(value = "/ping")
	public String pingPong(){
		return "pong";
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAll(@RequestParam Integer page) {
		GetExoPlanetsUsecase.Input payload = new GetExoPlanetsUsecase.Input(page);
		var response = planetsUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

	@GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAllQuery(@RequestParam Integer page,
	                                                   @RequestParam String planetName,
	                                                   @RequestParam String hostName,
	                                                   @RequestParam String discoveryMethod,
	                                                   @RequestParam String discoveryYear) {
		GetExoPlanetsQuerybleUsecase.Input payload = new GetExoPlanetsQuerybleUsecase.Input(page, planetName, hostName, discoveryMethod, discoveryYear);
		var response = planetsQuerybleUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

	@GetMapping(value = "/planet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAllByField(@RequestParam Integer page, @RequestParam String planetName) {
		GetExePlanetsbyPlanetNameUsecase.Input payload = new GetExePlanetsbyPlanetNameUsecase.Input(page, planetName);
		var response = planetsByPlanetNameUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

	@GetMapping(value = "/host", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAllByHost(@RequestParam Integer page, @RequestParam String hostName) {
		GetExoPlanetsByHostNameUsecase.Input payload = new GetExoPlanetsByHostNameUsecase.Input(page, hostName);
		var response = planetsByHostUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

	@GetMapping(value = "/method", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAllByMethod(@RequestParam Integer page, @RequestParam String discoveryMethod) {
		GetExoPlanetsByDiscoveryMethodUsecase.Input payload = new GetExoPlanetsByDiscoveryMethodUsecase.Input(page, discoveryMethod);
		var response = planetsByMethodUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

	@GetMapping(value = "/year", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExoPlanet>> getAllByYear(@RequestParam Integer page, @RequestParam String discoveryYear) {
		GetExoPlanetsByDiscoveryYearUsecase.Input payload = new GetExoPlanetsByDiscoveryYearUsecase.Input(page, discoveryYear);
		var response = planetsByYearUsecase.execute(payload);
		return ResponseEntity.status(HttpStatus.OK).body(response.planetList());
	}

}
