package com.mathan.is.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathan.is.dto.PlayerPersonalDTO;
import com.mathan.is.models.Address;
import com.mathan.is.models.Player;
import com.mathan.is.models.PlayerPersonal;
import com.mathan.is.repository.AddressRepo;
import com.mathan.is.repository.PlayerProfileRepo;
import com.mathan.is.repository.PlayersRepo;

@RestController
@RequestMapping("/personal")
public class PersonalProfileController {

	@Autowired
	private PlayerProfileRepo playerProfileRepo;

	@Autowired
	private PlayersRepo playersRepo;

	@Autowired
	private AddressRepo addressRepo;

	@PostMapping("/profile/{playerId}")
	public Object findPlayerProfile(@PathVariable("playerId") Integer id) {
		return playerProfileRepo.findByPlayerIdAndIsActiveTrue(id);
	}

	@PostMapping("/createprofile/{playerId}")
	public Object createProfileForPlayer(@RequestBody PlayerPersonalDTO playerPersonalDTO,
			@PathVariable("playerId") Integer id) {
		PlayerPersonal playerPersonal = new PlayerPersonal();
		BeanUtils.copyProperties(playerPersonalDTO, playerPersonal);
		Player player = playersRepo.findByIdAndIsActiveTrue(id);
		if (player == null) {
			return "Player Not Available";
		}
		new Thread(() -> {
			Address address = new Address();
			BeanUtils.copyProperties(playerPersonalDTO.getAddress(), address);
			playerPersonal.setPlayer(player);
			playerPersonal.setAddresses(address);
			playerProfileRepo.save(playerPersonal);
		}).start();
		return "Profile Created Successfully";
	}

	@PutMapping("/updateProfile/{profileId}")
	public Object updateProfile(@RequestBody PlayerPersonalDTO playerPersonalDTO,
			@PathVariable("profileId") Integer id) {
		PlayerPersonal playerPersonal = playerProfileRepo.findById(id).get();
		BeanUtils.copyProperties(playerPersonalDTO, playerPersonal);
		if (playerPersonalDTO.getAddress() != null) {
			Address address = new Address();
			BeanUtils.copyProperties(playerPersonalDTO.getAddress(), address);
			playerPersonal.setAddresses(address);
		}
		playerPersonal.setId(id);
		System.out.println(playerPersonal);
		new Thread(() -> {
			playerProfileRepo.save(playerPersonal);
		}).start();
		return "Profile Updated Success fully";
	}

}
