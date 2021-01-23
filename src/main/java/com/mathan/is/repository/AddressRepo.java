package com.mathan.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mathan.is.models.Address;

public interface AddressRepo extends JpaRepository<Address,Integer>{

}
