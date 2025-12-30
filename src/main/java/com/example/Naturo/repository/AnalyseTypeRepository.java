package com.example.Naturo.repository;

import com.example.Naturo.entity.AnalyseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyseTypeRepository extends JpaRepository<AnalyseType, Long> {
    Optional<AnalyseType> findByNom(String nom);
}