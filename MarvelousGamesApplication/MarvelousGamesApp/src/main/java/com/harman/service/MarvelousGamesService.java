package com.harman.service;

import org.springframework.boot.configurationprocessor.json.JSONArray;

public interface MarvelousGamesService {
	public JSONArray getCharacters(String url) throws Exception;
	public JSONArray getCharacter(String url) throws Exception;
	public int storeCharacters(JSONArray jsonArray, String charName) throws Exception;
	public int returnPower(String characterName) throws Exception;
}