package com.dripclothe.repository;

import com.dripclothe.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddrressRepo extends JpaRepository<Address, Integer> {
}
