package com.mathan.is.dto;

import java.util.List;

public class PlayerResponse {

	private PlayerDTO playerDTO;
	private List<CarrerDTO> carrerDTOs;

	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}

	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}

	public List<CarrerDTO> getCarrerDTOs() {
		return carrerDTOs;
	}

	public void setCarrerDTOs(List<CarrerDTO> carrerDTOs) {
		this.carrerDTOs = carrerDTOs;
	}

}
