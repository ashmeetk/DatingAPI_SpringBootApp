package com.ashmeet.dating.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashmeet.dating.entities.Interest;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

}
