package com.petSolidario.repositories;

import com.petSolidario.domains.Instituicao;
import com.petSolidario.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Optional<Instituicao> findByCnpj(String cnpj);

}
