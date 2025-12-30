package com.example.Naturo.repository;

import com.example.Naturo.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {
}