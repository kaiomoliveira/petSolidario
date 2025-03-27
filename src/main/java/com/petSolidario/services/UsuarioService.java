package com.petSolidario.services;

import com.petSolidario.domains.*;
import com.petSolidario.domains.dtos.UsuarioDTO;
import com.petSolidario.repositories.*;
import com.petSolidario.services.exceptions.DataIntegrityViolationException;
import com.petSolidario.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Retorna uma lista de UsuarioDTO
    public List<UsuarioDTO> findAll() {
        return usuarioRepo.findAll().stream()
                .map(obj -> new UsuarioDTO(obj))
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = usuarioRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário Não Encontrado! ID: " + id));
    }

    public Usuario findByCpf(String cpf) {
        Optional<Usuario> obj = usuarioRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário Não Encontrado! CPF: " + cpf));
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = usuarioRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário Não Encontrado! EMAIL: " + email));
    }

    public Usuario create(UsuarioDTO dto) {
        dto.setId(null);
        validaUsuario(dto);
        Usuario obj = new Usuario(dto);

        return usuarioRepo.save(obj);
    }

    public Usuario update(Long id, UsuarioDTO dto) {

        dto.setId(id);
        Usuario oldObj = findById(id);
        validaUsuario(dto);
        oldObj = new Usuario(dto);
        return usuarioRepo.save(oldObj);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);

        // Verifica se o usuário está associado a instituições
        if (!obj.getInstituicoes().isEmpty()) {
            throw new DataIntegrityViolationException("Usuário não pode ser excluído! Ele está associado às seguintes instituições: " +
                    obj.getInstituicoes().stream()
                            .map(Instituicao::getNome)
                            .collect(Collectors.joining(", ")));
        }

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
        usuarioRepo.deleteById(id);
    }


    // Valida se o CPF já está cadastrado
    private void validaUsuario(UsuarioDTO dto) {

        Optional<Usuario> objEmail = usuarioRepo.findByEmail(dto.getEmail());
        if (objEmail.isPresent() && !objEmail.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Email já Cadastrado!");
        }

        Optional<Usuario> objCpf = usuarioRepo.findByCpf(dto.getCpf());
        if (objCpf.isPresent() && !objCpf.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("CPF já Cadastrado!");
        }

    }

}
