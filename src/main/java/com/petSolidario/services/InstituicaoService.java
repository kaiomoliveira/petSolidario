package com.petSolidario.services;


import com.petSolidario.domains.Animal;
import com.petSolidario.domains.Instituicao;
import com.petSolidario.domains.Produto;
import com.petSolidario.domains.dtos.InstituicaoDTO;
import com.petSolidario.repositories.AnimalRepository;
import com.petSolidario.repositories.InstituicaoRepository;
import com.petSolidario.repositories.ProdutoRepository;
import com.petSolidario.services.exceptions.DataIntegrityViolationException;
import com.petSolidario.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private AnimalRepository animalRepo;

    @Autowired
    private ProdutoRepository produtoRepo;
    // Retorna uma lista de InstituicaoDTO
    public List<InstituicaoDTO> findAll() {
        return instituicaoRepo.findAll().stream()
                .map(obj -> new InstituicaoDTO(obj))
                .collect(Collectors.toList());
    }

    public Instituicao findById(Long id) {
        Optional<Instituicao> obj = instituicaoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Instituição Não Encontrada! ID: " + id));
    }

    public Instituicao findByCnpj(String cnpj) {
        Optional<Instituicao> obj = instituicaoRepo.findByCnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Instituição Não Encontrada! CNPJ: " + cnpj));
    }

    public Instituicao create(InstituicaoDTO dto) {
        dto.setId(null);
        validaInstituicao(dto);
        Instituicao obj = new Instituicao(dto);

        return instituicaoRepo.save(obj);
    }

    public Instituicao update(Long id, InstituicaoDTO dto) {
        dto.setId(id);
        Instituicao oldObj = findById(id);
        validaInstituicao(dto);
        oldObj = new Instituicao(dto);
        return instituicaoRepo.save(oldObj);
    }

    public void delete(Long id) {
        Instituicao obj = findById(id);

        // Verifica se o usuário está associado a animais
        if (!obj.getAnimais().isEmpty()) {
            throw new DataIntegrityViolationException("Usuário não pode ser excluído! Ele está associado aos seguintes animais: " +
                    obj.getAnimais().stream()
                            .map(Animal::getNome)
                            .collect(Collectors.joining(", ")));
        }

        // Verifica se o usuário está associado a produtos
        if (!obj.getProdutos().isEmpty()) {
            throw new DataIntegrityViolationException("Usuário não pode ser excluído! Ele está associado aos seguintes produtos: " +
                    obj.getProdutos().stream()
                            .map(Produto::getNome)
                            .collect(Collectors.joining(", ")));
        }

        // Se não houver relacionamentos, realiza a exclusão
        instituicaoRepo.deleteById(id);
    }


    // Valida se o CPF já está cadastrado
    private void validaInstituicao(InstituicaoDTO dto) {
        Optional<Instituicao> objCnpj = instituicaoRepo.findByCnpj(dto.getCnpj());
        if (objCnpj.isPresent() && !objCnpj.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("CNPJ já Cadastrado!");
        }

    }

}
