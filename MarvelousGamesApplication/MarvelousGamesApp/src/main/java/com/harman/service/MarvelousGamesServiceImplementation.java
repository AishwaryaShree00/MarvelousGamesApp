package com.harman.service;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarvelousGamesServiceImplementation implements MarvelousGamesService {

	RestTemplate restTemplate = new RestTemplate();
	TreeMap<Integer, String> character = new TreeMap<Integer, String>();
	TreeMap<Integer, String> characterCount = new TreeMap<Integer, String>();
	String url1 = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b";
	String url2 = "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e";
	String url3 = "http://www.mocky.io/v2/5ecfd630320000f1aee3d64d";

	public JSONArray getCharacters(String url) throws Exception {
		JSONArray jsonArray = null;
		ResponseEntity<String> results = restTemplate.getForEntity(url, String.class);
		String jsonString = results.getBody();
		JSONObject jsonObject = new JSONObject(jsonString);
		jsonArray = jsonObject.getJSONArray("character");
		return jsonArray;
	}

	public JSONArray getCharacter(String url) throws Exception {
		ResponseEntity<String> results = restTemplate.getForEntity(url, String.class);
		String jsonString = results.getBody();
		jsonString = jsonString.substring(0, jsonString.length() - 4);
		jsonString = jsonString.concat("}");
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray jsonArray1 = jsonObject.getJSONArray("character");
		return jsonArray1;
	}

	public int storeCharacters(JSONArray jsonArray, String charName) throws Exception {
		int max_power = 0;
		String name;
		int charCount = 0;
		if (character.size() < 15) {
			for (int count = 0; count < jsonArray.length(); count++) {
				name = jsonArray.getJSONObject(count).getString("name");
				if (name.equalsIgnoreCase(charName)) {
					if (characterCount.containsKey(charCount)) {
						charCount++;
						characterCount.put(charCount, name);
					} else {
						characterCount.put(charCount, name);
					}
					max_power = jsonArray.getJSONObject(count).getInt("max_power");
					character.put(max_power, name);
				}
			}
		} else if (character.size() == 15 && !character.containsValue(charName)) {
			character.pollFirstEntry();

			for (int count = 0; count < jsonArray.length(); count++) {
				name = jsonArray.getJSONObject(count).getString("name");
				if (name.equalsIgnoreCase(charName)) {
					if (characterCount.containsValue(name)) {
						charCount++;
						characterCount.put(charCount, name);
					} else {
						characterCount.put(charCount, name);
					}
					max_power = jsonArray.getJSONObject(count).getInt("max_power");
					character.put(max_power, name);
				}
			}
		}

		else if (character.size() == 15 && !character.containsValue(charName)) {
			for (Entry<Integer, String> entry : characterCount.entrySet()) {
				String value = entry.getValue();
				if (value.equalsIgnoreCase(charName)) {
					Integer key = entry.getKey();
					{
						if (key == 0) {
							character.pollFirstEntry();

							for (int count = 0; count < jsonArray.length(); count++) {
								name = jsonArray.getJSONObject(count).getString("name");
								if (name.equalsIgnoreCase(charName)) {
									if (characterCount.containsValue(name)) {
										charCount++;
										characterCount.put(charCount, name);
									} else {
										characterCount.put(charCount, name);
									}
									max_power = jsonArray.getJSONObject(count).getInt("max_power");
									character.put(max_power, name);
								}
							}
						} else if (key > 0) {
							for (int count = 0; count < jsonArray.length(); count++) {
								name = jsonArray.getJSONObject(count).getString("name");
								if (name.equalsIgnoreCase(charName)) {
									if (characterCount.containsValue(name)) {
										charCount++;
										characterCount.put(charCount, name);
									} else {
										characterCount.put(charCount, name);
									}
									max_power = jsonArray.getJSONObject(count).getInt("max_power");
									character.put(max_power, name);
								}
							}
						}
					}
				}
			}
		}
		return max_power;
	}

	public int returnPower(String characterName) throws Exception {
		int power = 0;
		JSONArray jsonArray;

		jsonArray = getCharacters(url1);
		power = storeCharacters(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		jsonArray = getCharacters(url2);
		power = storeCharacters(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		jsonArray = getCharacter(url3);
		power = storeCharacters(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		if (power == 0) {
			return power;
		}
		return power;
	}
}