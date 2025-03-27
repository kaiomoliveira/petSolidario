package com.petSolidario.repositories;

import com.petSolidario.domains.Produto;
import com.petSolidario.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByCodigoDeBarras(String codigoDeBarras);
}
