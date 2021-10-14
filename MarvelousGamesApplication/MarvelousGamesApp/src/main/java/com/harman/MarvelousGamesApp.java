package com.harman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.harman.controllers.MarvelousGamesHomeController;

@SpringBootApplication
public class MarvelousGamesApp {
	static MarvelousGamesHomeController marvelousGamesHomeController;
	public static void main(String[] args) {
		SpringApplication.run(MarvelousGamesApp.class, args);
	}
}