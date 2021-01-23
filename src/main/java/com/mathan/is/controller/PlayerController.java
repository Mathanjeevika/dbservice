package com.mathan.is.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathan.is.dto.CarrerDTO;
import com.mathan.is.dto.FilterDto;
import com.mathan.is.dto.PlayerDTO;
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

//	@GetMapping("")
	public Object savePlayers() {

		Player player = new Player("Rohit Sharma", "Batsman", "Mumbai", 33);
		player = playersRepo.save(player);
		Carreer test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5, player);
		Carreer odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5, player);
		Carreer t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5, player);
		List<Carreer> carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carrerRepo.saveAll(carreers);
		player = playersRepo.save(new Player("Jasprit", "Bowler", "Mumbai", 27));
		test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5, player);
		odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5, player);
		t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5, player);
		carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carreers = carrerRepo.saveAll(carreers);
		player = playersRepo.save(new Player("Hardik", "All Rounder", "Mumbai", 33));
		test = new Carreer("test", "19", "1405", 100, 5, 1, 1, 5, player);
		odi = new Carreer("odi", "50", "1505", 100, 5, 1, 1, 5, player);
		t20 = new Carreer("t20", "10", "405", 100, 5, 1, 1, 5, player);
		carreers = new ArrayList<Carreer>();
		carreers.add(t20);
		carreers.add(odi);
		carreers.add(test);
		carreers = carrerRepo.saveAll(carreers);
		return "Success";
	}

	@GetMapping("/players")
	public Object getAllPlayer() {
		return playersRepo.findByIsActiveTrue();
	}

	@GetMapping("/players/{playerId}")
	public Object getAllPlayer(@PathVariable("playerId") Integer playerId) {
		Player player = playersRepo.findByIdAndIsActiveTrue(playerId);
		return player==null?"404":playerMapper.playerToDTO(player);
	}

	@GetMapping("/player/carrer/{playerId}")
	public Object getCarrerByPlayerId(@PathVariable("playerId") Integer playerId) {
		List<Carreer> carreers = carrerRepo.findByPlayerIdAndIsActiveTrue(playerId);
		return carreers.isEmpty()?"404":playerMapper.playerResponse(carreers);
	}

//	@GetMapping("/saveSingle")
	public Object createSinglePlayer() {
		Player player = new Player("Mathan", "All-Rounder", "Mumbai", 23);
		player = playersRepo.save(player);
		Carreer odi = new Carreer("ODI", "28", "500", 258, 2, 5, 1, 22, player);
		Carreer t20 = new Carreer("t20", "48", "2500", 158, 1, 8, 0, 30, player);
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
			players = playersRepo.findByTeamAndIsActiveTrue(filterDto.getValue());
		} else if (CommonConstants.AGE.equals(filterDto.getType())) {
			players = playersRepo.findByAgeGreaterThanEqualAndIsActiveTrue(Integer.parseInt(filterDto.getValue()));
		} else if (CommonConstants.NAME.equals(filterDto.getType())) {
			players = playersRepo.findByNameLikeAndIsActiveTrue("%" + filterDto.getValue() + "%");
		} else if (CommonConstants.ROLE.equals(filterDto.getType())) {
			players = playersRepo.findByRoleAndIsActiveTrue(filterDto.getValue());
		} else if (CommonConstants.AVERAGE.equals(filterDto.getType())) {
			players.addAll(carrerRepo.findByAverageGreaterThanEqualAndIsActiveTrue(Double.parseDouble(filterDto.getValue())).stream()
					.map(value -> value.getPlayer()).collect(Collectors.toSet()));
		}
		return playerMapper.playerToPlayerDTOS(players);
	}

	@PostMapping("/createPlayer")
	public Object createPlayer(@RequestBody PlayerDTO playerDTO) {
		Player player = playerMapper.playerDTOtoPlayer(playerDTO);
		return playersRepo.save(player).getId();
	}

	@PostMapping("player/{playerId}/createCarrer")
	public Object createcarrer(@RequestBody List<CarrerDTO> carrerDTOs, @PathVariable("playerId") Integer playerId) {
		Player player = playersRepo.getOne(playerId);
		if (player == null || !player.isActive()) {
			return ResponseEntity.badRequest().body("Please select the proper player");
		}
		new Thread(() -> {
			carrerRepo.saveAll(carrerDTOs.stream().map(carrerDTO -> playerMapper.carrerDTOtoCarrer(carrerDTO, player))
					.collect(Collectors.toList()));
			;
		}).start();
		return ResponseEntity.status(HttpStatus.CREATED).body("SUccess");
	}

	@PostMapping("/player/updateCarrer/{carrerId}")
	public Object updateCarrer(@RequestBody CarrerDTO carrerDTOs, @PathVariable("carrerId") Integer carrerId) {
		new Thread(() -> {
			Carreer carrer = carrerRepo.findById(carrerId).get();
			BeanUtils.copyProperties(carrerDTOs, carrer);
			carrer.setId(carrerId);
			carrerRepo.save(carrer);
		}).start();
		return ResponseEntity.status(HttpStatus.CREATED).body("SUccess");
	}

	@PostMapping("/player/deletePlayer/{playerId}")
	public Object deletePlayer(@PathVariable("playerId") Integer playerId) {
		List<Carreer> carreers = carrerRepo.findByPlayerIdAndIsActiveTrue(playerId);
		carreers.stream().forEach(carreer -> {
			carreer.setActive(false);
		});
		Player player = carreers.get(0).getPlayer();
		player.setActive(false);
		new Thread(() ->  {
			playersRepo.save(player);
			carrerRepo.saveAll(carreers);
		}).start();
		
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@GetMapping("/carrer/{carrerId}")
	public Object getCarrerById(@PathVariable("carrerId") Integer carrerId) {
		Carreer carreer = carrerRepo.findByIdAndIsActiveTrue(carrerId);
		return carreer == null?"404":playerMapper.carrerToCarrerDTO(carreer);
	}
}
