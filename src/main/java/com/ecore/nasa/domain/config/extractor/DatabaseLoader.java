package com.ecore.nasa.domain.config.extractor;

import com.ecore.nasa.domain.repository.IExoPlanetRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader implements Runnable {

	private final FileExtractor fileExtractor;
	private final IExoPlanetRepository repository;

	@Autowired
	public DatabaseLoader (FileExtractor fileExtractor, IExoPlanetRepository repository) {
		this.fileExtractor = fileExtractor;
		this.repository = repository;
	}

	@PostConstruct
	public void load() {
		DatabaseLoader loader = new DatabaseLoader(this.fileExtractor, repository);
		Thread thread = new Thread(loader);
		thread.start();
	}

	@Override
	public void run () {
		this.repository.addAll(this.fileExtractor.getExoPlanets());
	}
}
