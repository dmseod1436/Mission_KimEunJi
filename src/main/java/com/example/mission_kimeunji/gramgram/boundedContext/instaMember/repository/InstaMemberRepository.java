package com.example.mission_kimeunji.gramgram.boundedContext.instaMember.repository;

import com.example.mission_kimeunji.gramgram.boundedContext.instaMember.entity.InstaMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstaMemberRepository extends JpaRepository<InstaMember, Long> {
    Optional<InstaMember> findByUsername(String username);
}