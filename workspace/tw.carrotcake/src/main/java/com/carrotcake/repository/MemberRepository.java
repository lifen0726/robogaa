package com.carrotcake.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrotcake.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	public Optional<Member> findByName(String name);
	public Member findByNameAndPassword(String name, String password);
	Boolean existsByName(String username);
}
