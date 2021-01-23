package com.mathan.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathan.is.models.PlayerPersonal;

@Repository
public interface PlayerProfileRepo extends JpaRepository<PlayerPersonal, Integer>{
	public PlayerPersonal findByPlayerIdAndIsActiveTrue(Integer id);
}
