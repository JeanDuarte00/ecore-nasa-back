package com.ecore.nasa.application.usecase;

import com.ecore.nasa.domain.model.ExoPlanet;
import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetExoPlanetsByDiscoveryMethodUsecase implements IUsecase<GetExoPlanetsByDiscoveryMethodUsecase.Input, GetExoPlanetsByDiscoveryMethodUsecase.Output> {

	private final IExoPlanetRepository repository;

	public GetExoPlanetsByDiscoveryMethodUsecase (IExoPlanetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Output execute (Input input) {
		PageRequest pageRequest = PageRequest.of(input.page, 100);
		List<ExoPlanet> response = this.repository.getAllByDiscoveryMethod(pageRequest, input.fieldValue);
		return new GetExoPlanetsByDiscoveryMethodUsecase.Output(response);
	}

	public record Input(Integer page, String fieldValue){}
	public record Output(List<ExoPlanet> planetList){}
}
