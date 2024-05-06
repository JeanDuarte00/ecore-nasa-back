package com.ecore.nasa.application.usecase;

import com.ecore.nasa.domain.exception.InvalidPayloadException;
import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetExoPlanetsQuerybleUsecase implements IUsecase<GetExoPlanetsQuerybleUsecase.Input, GetExoPlanetsQuerybleUsecase.Output>{

	private final IExoPlanetRepository repository;

	public GetExoPlanetsQuerybleUsecase (IExoPlanetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Output execute (Input input) {
		List<ExoPlanet> data;
		data = this.repository.getAllQueryble(input.planetName, input.hostName, input.discoveryMethod, input.discoveryYear);
		return new Output(data);
	}

	public record Input(Integer page, String planetName, String hostName, String discoveryMethod, String discoveryYear){

		 public Input {
			boolean isAnyEmpty = planetName.isEmpty() || hostName.isEmpty() || discoveryMethod.isEmpty() || discoveryYear.isEmpty();
			if(isAnyEmpty){
				throw new InvalidPayloadException("Have to pass all filters");
			}
		}
	}
	public record Output(List<ExoPlanet> planetList){}
}
