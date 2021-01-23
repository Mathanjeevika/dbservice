package com.mathan.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mathan.is.models.Player;

@Repository
public interface PlayersRepo extends JpaRepository<Player, Integer>{

	public List<Player> findByIsActiveTrue();

	public Player findByIdAndIsActiveTrue(Integer id);
	
	public List<Player> findByTeamAndIsActiveTrue(String team);
	
	public List<Player> findByAgeGreaterThanEqualAndIsActiveTrue(Integer value);
	
	public List<Player> findByNameLikeAndIsActiveTrue(String value);
	
	public List<Player> findByRoleAndIsActiveTrue(String role);
	
	@Query("select player from Player player where player.id in :playerIds")
	public List<Player> findByCarrerIds(@Param("playerIds") List<Integer> carrers);
		
}
