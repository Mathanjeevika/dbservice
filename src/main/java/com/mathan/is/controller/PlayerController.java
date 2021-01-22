package com.mathan.is.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathan.is.dto.FilterDto;
import com.mathan.is.mapper.PlayerMapper;
import com.mathan.is.models.Carreer;
import com.mathan.is.models.Player;
import com.mathan.is.repository.CarrerRepo;
import com.mathan.is.repository.PlayersRepo;
import com.mathan.is.util.CommonConstants;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayersRepo playersRepo;

	@Autowired
	private CarrerRepo carrerRepo;

	@Autowired
	private PlayerMapper playerMapper;
	
	@GetMapping("")
	public Object savePlayers() {
		
		Player player = new Player("Rohit Sharma", "Batsman", "Mumbai", 33);
		player = playersRepo.save(player);
		Carreer test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5,player);
		Carreer odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5,player);
		Carreer t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5,player);
		List<Carreer> carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carrerRepo.saveAll(carreers);
		player =playersRepo.save(new Player("Jasprit", "Bowler", "Mumbai", 27));
		test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5,player);
		odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5,player);
		t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5,player);
		carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carreers = carrerRepo.saveAll(carreers);
		player =playersRepo.save(new Player("Hardik", "All Rounder", "Mumbai", 33));
		test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5,player);
		odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5,player);
		t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5,player);
		carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carreers = carrerRepo.saveAll(carreers);
		return "Success";
	}

	@GetMapping("/players")
	public Object getAllPlayer() {
		return playersRepo.findAll();
	}

	@GetMapping("/players/{playerId}")
	public Object getAllPlayer(@PathVariable("playerId") Integer playerId) {
		Player player = playersRepo.findById(playerId).get();
		return playerMapper.playerToDTO(player);
	}
	
	@GetMapping("/player/carrer/{playerId}")
	public Object getCarrerByPlayerId(@PathVariable("playerId") Integer playerId) {
		List<Carreer> carreers = carrerRepo.findByPlayerId(playerId);
		return playerMapper.playerResponse(carreers);
	}
	
	@GetMapping("/saveSingle")
	public Object createSinglePlayer() {
		Player player = new Player("Mathan", "All-Rounder", "Mumbai", 23);
		player = playersRepo.save(player);
		Carreer odi = new Carreer("ODI","28","500",258,2,5,1,22,player);
		Carreer t20 = new Carreer("t20","48","2500",158,1,8,0,30,player);
		List<Carreer> carreers = new ArrayList<Carreer>();
		carreers.add(odi);
		carreers.add(t20);
		carreers = carrerRepo.saveAll(carreers);
		return player;
	}
	

	@PostMapping("/player")
	public Object filteredPlayers(@RequestBody FilterDto filterDto) {
		List<Player> players = new ArrayList<Player>();
		if (CommonConstants.TEAM.equals(filterDto.getType())) {
			players = playersRepo.findByTeam(filterDto.getValue());
		} else if (CommonConstants.AGE.equals(filterDto.getType())) {
			players = playersRepo.findByAgeGreaterThanEqual(Integer.parseInt(filterDto.getValue()));
		} else if (CommonConstants.NAME.equals(filterDto.getType())) {
			players = playersRepo.findByNameLike("%" + filterDto.getValue() + "%");
		} else if (CommonConstants.ROLE.equals(filterDto.getType())) {
			players = playersRepo.findByRole(filterDto.getValue());
		} else if (CommonConstants.AVERAGE.equals(filterDto.getType())) {
			players.addAll(carrerRepo.findByAverageGreaterThanEqual(Double.parseDouble(filterDto.getValue())).stream().map(value -> value.getPlayer()).collect(Collectors.toSet()));
		}
		return playerMapper.playerToPlayerDTOS(players);
	}
}
