package com.mathan.is.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathan.is.models.Player;
import com.mathan.is.repository.PlayersRepo;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayersRepo playersRepo;

	@GetMapping("")
	public Object savePlayers() {
		List<Player> players = new ArrayList();
		players.add(new Player("Rohit Sharma", "Batsman", "Mumbai", 33));
		players.add(new Player("Jasprit", "Bowler", "Mumbai", 27));
		players.add(new Player("Hardik", "All Rounder", "Mumbai", 33));
		players.add(new Player("Pollard", "All Rounder", "Mumbai", 33));
		players.add(new Player("Dhoni", "Wk-Batsman", "Chennai", 33));
		players.add(new Player("Raina", "Batsman", "Chennai", 33));
		players.add(new Player("Jadeja", "All Rounder", "Chennai", 33));
		players.add(new Player("Virat", "Batsman", "Bangalor", 31));
		players.add(new Player("ABD", "Wk-Batsman", "Bangalor", 36));
		players.add(new Player("Chahal", "Bowler", "Bangalor", 28));
		players.add(new Player("Iyer", "Batsman", "Delhi", 27));
		players.add(new Player("Pant", "WK-Batsman", "Delhi", 27));
		players.add(new Player("Rabada", "Bowler", "Delhi", 27));
		return playersRepo.saveAll(players);
	}

	@GetMapping("/players")
	public Object getAllPlayer() {
		return playersRepo.findAll();
	}

	@GetMapping("/players/{playerId}")
	public Object getAllPlayer(Integer playerId) {
		return playersRepo.findById(playerId);
	}

}
