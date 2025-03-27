package com.petSolidario.repositories;

import com.petSolidario.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByCpf(String cpf);
    Optional<Usuario>findByEmail(String email);

}
