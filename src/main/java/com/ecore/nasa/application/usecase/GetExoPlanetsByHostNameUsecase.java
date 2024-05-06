package com.ecore.nasa.application.usecase;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetExoPlanetsByHostNameUsecase implements IUsecase<GetExoPlanetsByHostNameUsecase.Input, GetExoPlanetsByHostNameUsecase.Output> {

	private final IExoPlanetRepository repository;

	public GetExoPlanetsByHostNameUsecase (IExoPlanetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Output execute (Input input) {
		PageRequest pageRequest = PageRequest.of(input.page, 100);
		List<ExoPlanet> response = this.repository.getAllByHostName(pageRequest, input.fieldValue);
		return new GetExoPlanetsByHostNameUsecase.Output(response);
	}

	public record Input(Integer page, String fieldValue){}
	public record Output(List<ExoPlanet> planetList){}
}
