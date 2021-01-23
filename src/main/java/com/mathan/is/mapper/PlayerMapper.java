package com.mathan.is.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.mathan.is.dto.CarrerDTO;
import com.mathan.is.dto.PlayerDTO;
import com.mathan.is.dto.PlayerResponse;
import com.mathan.is.models.Carreer;
import com.mathan.is.models.Player;

@Component
public class PlayerMapper {

	public PlayerDTO playerToDTO(Player player) {
		PlayerDTO playerDTO = new PlayerDTO();
		BeanUtils.copyProperties(player, playerDTO);
		return playerDTO;
	}
	
	public CarrerDTO carrerToCarrerDTO(Carreer carreer) {
		CarrerDTO carrerDTO = new CarrerDTO();
		BeanUtils.copyProperties(carreer, carrerDTO);
		return carrerDTO;
	}
	
	public List<CarrerDTO> carrerToDTOS(List<Carreer> carreers){
		return carreers.stream().map(carreer -> carrerToCarrerDTO(carreer)).collect(Collectors.toList());
	}
	
	public PlayerResponse playerResponse(List<Carreer> carreers) {
		if(carreers.isEmpty()) {
			return null;
		}
		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse.setPlayerDTO(playerToDTO(carreers.get(0).getPlayer()));
		playerResponse.setCarrerDTOs(carrerToDTOS(carreers));
		return playerResponse;
	}
	
	public List<PlayerDTO> playerToPlayerDTOS(List<Player> players) {
		return players.stream().map(player -> playerToDTO(player)).collect(Collectors.toList());
	}

	public Player playerDTOtoPlayer(PlayerDTO playerDTO) {
		Player player = new Player();
		BeanUtils.copyProperties(playerDTO, player);
		return player;
	}
	
	public Carreer carrerDTOtoCarrer(CarrerDTO carrerDTO,Player player) {
		Carreer carreer = new Carreer();
		BeanUtils.copyProperties(carrerDTO, carreer);
		carreer.setPlayer(player);
		return carreer;
	}
	
}
