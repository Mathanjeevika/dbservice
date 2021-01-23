package com.mathan.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathan.is.models.Carreer;

@Repository
public interface CarrerRepo extends JpaRepository<Carreer, Integer> {

	public List<Carreer> findByAverageGreaterThanEqualAndIsActiveTrue(Double value);
	
	public List<Carreer> findByPlayerIdAndIsActiveTrue(Integer player);
	
	public Carreer findByIdAndIsActiveTrue(Integer id);
	
}
