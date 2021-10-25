package com.harman.service;

import java.util.Set;
import java.util.TreeMap;

import org.springframework.boot.configurationprocessor.json.JSONArray;

public interface MarvelousGamesService {
	public JSONArray getCharacters(String url) throws Exception;
	public JSONArray getCharacter(String url) throws Exception;
	public int getPower(JSONArray jsonArray, String charName) throws Exception;
	public String getMinKey(TreeMap<String, Integer> map, Set<String> keys);
	public int returnPower(String characterName) throws Exception;
	public int addChars(JSONArray jsonArray, String charName) throws Exception;
}
