package com.harman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harman.service.MarvelousGamesServiceImplementation;

@Controller
public class MarvelousGamesHomeController {

	@Autowired
	private MarvelousGamesServiceImplementation marvelousGamesService;

	@RequestMapping(value = "/home")
	public String getHome() throws Exception {
		return "home";
	}

	@RequestMapping(value = "/showpower")
	public String showPower(@RequestParam("characterName") String characterName, Model model) {
		int power = 0;
		try {
			power = marvelousGamesService.returnPower(characterName);
			model.addAttribute("name", characterName);
			if (power == 0) {
				model.addAttribute("name", "Invalid Character");
				model.addAttribute("max_power", "No Power");
			} else if (power != 0) {
				model.addAttribute("max_power", power);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "powerLevel";
	}
}