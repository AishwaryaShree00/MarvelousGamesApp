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

	private RestTemplate restTemplate = new RestTemplate();
	private TreeMap<String, Integer> character = new TreeMap<String, Integer>(); //for storing characters
	private TreeMap<String, Integer> characterCount = new TreeMap<String, Integer>(); //for storing character counts

	private String url1 = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b";
	private String url2 = "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e";
	private String url3 = "http://www.mocky.io/v2/5ecfd630320000f1aee3d64d";
	
	//for getting names and max powers of characters from apis
	public JSONArray getCharacters(String url) throws Exception {
		JSONArray jsonArray = null;
		ResponseEntity<String> results = restTemplate.getForEntity(url, String.class);
		String jsonString = results.getBody();
		JSONObject jsonObject = new JSONObject(jsonString);
		jsonArray = jsonObject.getJSONArray("character");
		return jsonArray;
	}
	
	//for getting names and max powers of characters from api
	public JSONArray getCharacter(String url) throws Exception {
		ResponseEntity<String> results = restTemplate.getForEntity(url, String.class);
		String jsonString = results.getBody();
		jsonString = jsonString.substring(0, jsonString.length() - 4);
		jsonString = jsonString.concat("}");
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray jsonArray1 = jsonObject.getJSONArray("character");
		return jsonArray1;
	}
	
	//for getting maximum power of given character , implementing business rules
	public int getPower(JSONArray jsonArray, String charName) throws Exception {
		int max_power = 0;
		if (character.size() < 15) {
			max_power = addChars(jsonArray, charName);
		} else if (character.size() == 15) {
			if (!character.containsKey(charName) && !characterCount.containsKey(charName)) {
				character.remove(getMinKey(character, character.keySet()));
				max_power = addChars(jsonArray, charName);
			} else if (!character.containsKey(charName) && characterCount.containsKey(charName)) {
				if (charName.equalsIgnoreCase(getMinKey(characterCount, characterCount.keySet()))) {
					characterCount.keySet().remove(charName);
					character.remove(getMinKey(characterCount, characterCount.keySet()));
				} else {
					character.remove(getMinKey(characterCount, characterCount.keySet()));
				}
				max_power = addChars(jsonArray, charName);
			} else if (character.containsKey(charName)) {
				max_power = addChars(jsonArray, charName);
			}
		}
		return max_power;
	}
	
	//returning maximum power to controller
	public int returnPower(String characterName) throws Exception {
		int power = 0;
		JSONArray jsonArray;

		jsonArray = getCharacters(url1);
		power = getPower(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		jsonArray = getCharacters(url2);
		power = getPower(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		jsonArray = getCharacter(url3);
		power = getPower(jsonArray, characterName);
		if (power != 0) {
			return power;
		}
		if (power == 0) {
			return power;
		}
		return power;
	}
	
	//getting the character with minimum power
	//getting the character with least count
	public String getMinKey(TreeMap<String, Integer> map, Set<String> keys) {
		String minKey = null;
		int minValue = Integer.MAX_VALUE;
		for (String key : keys) {
			int value = map.get(key);
			if (value < minValue) {
				minValue = value;
				minKey = key;
			}
		}
		System.out.println("D" + minKey + " " + minValue);
		return minKey;
	}
	
	//adding characters to the treemaps
	public int addChars(JSONArray jsonArray, String charName) throws Exception {
		int max_power = 0;
		String name;
		int charCount = 0;
		for (int count = 0; count < jsonArray.length(); count++) {
			name = jsonArray.getJSONObject(count).getString("name");
			if (name.equalsIgnoreCase(charName)) {
				if (characterCount.containsKey(charName)) {
					charCount = characterCount.get(charName);
					charCount++;
					characterCount.put(charName, charCount);
				} else {
					characterCount.put(charName, charCount);
				}
				max_power = jsonArray.getJSONObject(count).getInt("max_power");
				character.put(charName, max_power);
			}
		}
		return max_power;
	}
}
