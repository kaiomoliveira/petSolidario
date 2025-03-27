package com.petSolidario.services;


import com.petSolidario.domains.Produto;
import com.petSolidario.domains.dtos.ProdutoDTO;
import com.petSolidario.repositories.InstituicaoRepository;
import com.petSolidario.repositories.ProdutoRepository;
import com.petSolidario.repositories.UsuarioRepository;
import com.petSolidario.services.exceptions.DataIntegrityViolationException;
import com.petSolidario.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Retorna uma lista de UsuarioDTO
    public List<ProdutoDTO> findAll() {
        return produtoRepo.findAll().stream()
                .map(obj -> new ProdutoDTO(obj))
                .collect(Collectors.toList());
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = produtoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto Não Encontrado! ID: " + id));
    }

    public Produto findByCodigoDeBarras(String codigoDeBarras) {
        Optional<Produto> obj = produtoRepo.findByCodigoDeBarras(codigoDeBarras);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto Não Encontrado! CODIGO DE BARRAS: " + codigoDeBarras));
    }

    public Produto create(ProdutoDTO dto) {
        dto.setId(null);
        validaProduto(dto);
        Produto obj = new Produto(dto);

        return produtoRepo.save(obj);
    }

    public Produto update(Long id, ProdutoDTO dto) {

        dto.setId(id);
        Produto oldObj = findById(id);
        validaProduto(dto);
        oldObj = new Produto(dto);
        return produtoRepo.save(oldObj);
    }

    public void delete(Long id) {
        Produto obj = findById(id);
        produtoRepo.deleteById(id);
    }


    // Valida se o Codigo de Barras já está cadastrado
    private void validaProduto(ProdutoDTO dto) {

        Optional<Produto> obj = produtoRepo.findByCodigoDeBarras(dto.getCodigoDeBarras());
        if (obj.isPresent() && !obj.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Codigo de Barras já Cadastrado!");
        }

        // Valida se a instituição existe
        if (!instituicaoRepo.existsById(dto.getInstituicao())) {
            throw new ObjectNotFoundException("Instituição Não Encontrada! ID: " + dto.getInstituicao());
        }

        // Valida se o usuário existe
        if (!usuarioRepo.existsById(dto.getUsuario())) {
            throw new ObjectNotFoundException("Usuário Não Encontrado! ID: " + dto.getUsuario());
        }
    }

}
