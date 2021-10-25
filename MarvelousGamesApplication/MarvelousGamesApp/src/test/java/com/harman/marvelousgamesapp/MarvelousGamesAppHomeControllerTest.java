package com.harman.marvelousgamesapp;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harman.controllers.MarvelousGamesHomeController;
import com.harman.service.MarvelousGamesServiceImplementation;

@RunWith(SpringRunner.class)
@WebMvcTest(MarvelousGamesHomeController.class)
public class MarvelousGamesAppHomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private MarvelousGamesServiceImplementation service= new MarvelousGamesServiceImplementation();
	int p = 0;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new MarvelousGamesHomeController()).build();
	}

	@Test
	public void testHomePage() throws Exception {
		System.out.println("Test Home Page");
		this.mockMvc.perform(get("/home")).andExpect(status().isOk());
	}

	@Test
	public void testIronMan() throws Exception {
		System.out.println("Test Iron man");
		p = service.returnPower("Iron man");
		assertEquals(p, 60);
	}

	@Test
	public void testCaptainAmerica() throws Exception {
		System.out.println("Test Captain America");
		p = service.returnPower("Captain America");
		assertEquals(p, 68);
	}

	@Test
	public void testSpiderMan() throws Exception {
		System.out.println("Test Spider man");
		p = service.returnPower("Spider man");
		assertEquals(p, 58);
	}

	@Test
	public void testBlackPanther() throws Exception {
		System.out.println("Test Black Panther");
		p = service.returnPower("Black Panther");
		assertEquals(p, 68);
	}

	@Test
	public void testVision() throws Exception {
		System.out.println("Test Vision");
		p = service.returnPower("Vision");
		assertEquals(p, 50);
	}

	@Test
	public void testHawkEye() throws Exception {
		System.out.println("Test Iron man");
		p = service.returnPower("Hawk eye");
		assertEquals(p, 30);
	}

	@Test
	public void testMandrin() throws Exception {
		System.out.println("Test Mandrin");
		p = service.returnPower("Mandrin");
		assertEquals(p, 70);
	}

	@Test
	public void testThanos() throws Exception {
		System.out.println("Test Thanos");
		p = service.returnPower("Thanos");
		assertEquals(p, 80);
	}

	@Test
	public void testGalactus() throws Exception {
		System.out.println("Test Galactus");
		p = service.returnPower("Galactus");
		assertEquals(p, 95);
	}

	@Test
	public void testHela() throws Exception {
		System.out.println("Test Hela");
		p = service.returnPower("Hela");
		assertEquals(p, 75);
	}

	@Test
	public void testEgo() throws Exception {
		System.out.println("Test Ego");
		p = service.returnPower("Ego");
		assertEquals(p, 50);
	}

	@Test
	public void testDrDoom() throws Exception {
		System.out.println("Test Dr. Doom");
		p = service.returnPower("Dr. Doom");
		assertEquals(p, 78);
	}

	@Test
	public void testApocalypse() throws Exception {
		System.out.println("Test Apocalypse");
		p = service.returnPower("Apocalypse");
		assertEquals(p, 80);
	}

	@Test
	public void testProfessorX() throws Exception {
		System.out.println("Test Professor X");
		p = service.returnPower("Professor X");
		assertEquals(p, 75);
	}

	@Test
	public void testDarkPheonix() throws Exception {
		System.out.println("Test Dark Pheonix");
		p = service.returnPower("Dark Pheonix");
		assertEquals(p, 90);
	}

	@Test
	public void testMagneto() throws Exception {
		System.out.println("Test Magneto");
		p = service.returnPower("Magneto");
		assertEquals(p, 78);
	}

	@Test
	public void testWolverin() throws Exception {
		System.out.println("Test Wolverin");
		p = service.returnPower("Wolverin");
		assertEquals(p, 64);
	}

	@Test
	public void testMystique() throws Exception {
		System.out.println("Test Mystique");
		p = service.returnPower("Mystique");
		assertEquals(p, 66);
	}
}
