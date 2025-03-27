package com.petSolidario.repositories;

import com.petSolidario.domains.Adocao;
import com.petSolidario.domains.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {
}
