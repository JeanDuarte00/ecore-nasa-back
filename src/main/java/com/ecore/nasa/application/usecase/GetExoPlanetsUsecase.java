package com.ecore.nasa.application.usecase;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetExoPlanetsUsecase implements IUsecase<GetExoPlanetsUsecase.Input, GetExoPlanetsUsecase.Output>{

	private final IExoPlanetRepository repository;

	public GetExoPlanetsUsecase (IExoPlanetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Output execute (Input input) {
		PageRequest pageRequest = PageRequest.of(input.page, 100);
		List<ExoPlanet> response = this.repository.getAll(pageRequest);
		return new Output(response);
	}

	public record Input(Integer page){}
	public record Output(List<ExoPlanet> planetList){}
}
