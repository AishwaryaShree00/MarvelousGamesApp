package com.harman.marvelousgamesapp;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.harman.controllers.MarvelousGamesHomeController;

@RunWith(SpringRunner.class)
@WebMvcTest(MarvelousGamesHomeController.class)
public class MarvelousGamesAppHomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new MarvelousGamesHomeController()).build();
	}

	@Test
	public void testHomePage() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void testMessagePage() throws Exception {
		this.mockMvc.perform(get("/showpower")).andExpect(status().isOk());
	}
}