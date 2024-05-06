package com.ecore.nasa.domain.config.extractor;

import com.ecore.nasa.domain.exception.SetupException;
import com.ecore.nasa.domain.model.ExoPlanet;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

@Configuration
public class FileExtractor {

	private final Map<String, String> columns;
	private final Set<ExoPlanet> exoPlanetList;

	public FileExtractor(Map<String, String> map, Set<ExoPlanet> exoPlanetList){
		this.columns = map;
		this.exoPlanetList = exoPlanetList;
	}

	private void extractColumnsFromFile(String file){
		String path = this.getClass().getClassLoader().getResource(file).toExternalForm();
		try (Scanner scanner = new Scanner(path)) {
			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				if(currentLine.contains("COLUMN")){
					getColumns(currentLine);
				}
			}
		}

	}

	private void extractData(String file){
		String path = this.getClass().getClassLoader().getResource(file).toExternalForm();
		try (Scanner scanner = new Scanner(path)) {
			scanner.nextLine(); // read header
			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				String[] rawData = currentLine.split(",");

				String id = rawData[0];
				String planetName = rawData[1];
				String hostName = rawData[2];
				String discoveryMethod = rawData[3];
				String discoveryYear = rawData[4];

				ExoPlanet planet = new ExoPlanet(id, planetName, hostName, discoveryMethod, discoveryYear);
				this.exoPlanetList.add(planet);

			}
		}
	}

	private void getColumns(String line){
		String[] rawLine = line.split(":");
		String keyLine = rawLine[0];
		String dataLine = rawLine[1];
		String columnName = dataLine.trim();
		String columnKey = keyLine.split(" ")[keyLine.split(" ").length-1];
		this.columns.put(columnKey, columnName);
	}

	private File getFileFromResource(String fileName) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();

			URL resource = classLoader.getResource(fileName);
			if (resource == null) {
				throw new SetupException("File not found! " + fileName);
			}
			return new File(resource.toURI());
		} catch (URISyntaxException e) {
			throw new SetupException("Error when reading file.");
		}
	}

	public Map<String, String> getColumns(){
		return this.columns;
	}

	public Set<ExoPlanet> getExoPlanets(){
		return this.exoPlanetList;
	}

	@PostConstruct
	private void load () {
		this.extractColumnsFromFile("./static/nasa-columns.csv");
		this.extractData("./static/nasa-data.csv");
	}
}
