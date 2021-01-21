package com.mathan.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathan.is.models.Player;

@Repository
public interface PlayersRepo extends JpaRepository<Player, Integer>{

}
