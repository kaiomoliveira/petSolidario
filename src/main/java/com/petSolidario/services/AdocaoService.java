package com.petSolidario.services;

import com.petSolidario.domains.Adocao;
import com.petSolidario.domains.dtos.AdocaoDTO;
import com.petSolidario.repositories.AdocaoRepository;
import com.petSolidario.repositories.InstituicaoRepository;
import com.petSolidario.repositories.UsuarioRepository;
import com.petSolidario.services.exceptions.DataIntegrityViolationException;
import com.petSolidario.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdocaoService {


    @Autowired
    private AdocaoRepository adocaoRepo;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Retorna uma lista de adocaoDTO
    public List<AdocaoDTO> findAll() {
        return adocaoRepo.findAll().stream()
                .map(obj -> new AdocaoDTO(obj))
                .collect(Collectors.toList());
    }

    public Adocao findById(UUID id) {
        Optional<Adocao> obj = adocaoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("adocao Não Encontrado! ID: " + id));
    }

//    public Adocao findByRegistro(String registro) {
//        Optional<Adocao> obj = adocaoRepo.findByRegistro(registro);
//        return obj.orElseThrow(() -> new ObjectNotFoundException("adocao Não Encontrado! REGISTRO: " + registro));
//    }

    public Adocao create(AdocaoDTO dto) {
        dto.setId(null);
        validaadocao(dto);
        Adocao obj = new Adocao(dto);

        return adocaoRepo.save(obj);
    }

    public Adocao update(UUID id, AdocaoDTO dto) {
        dto.setId(id);
        Adocao oldObj = findById(id);
        validaadocao(dto);
        oldObj = new Adocao(dto);
        return adocaoRepo.save(oldObj);
    }

    // Validações específicas para o adocao
    private void validaadocao(AdocaoDTO dto) {

        // Valida se a instituição existe
        if (!instituicaoRepo.existsById(dto.getInstituicao().getId())) {
            throw new ObjectNotFoundException("Instituição Não Encontrada! ID: " + dto.getInstituicao());
        }

        // Valida se o usuário existe
        if (!usuarioRepo.existsById(dto.getUsuario().getId())) {
            throw new ObjectNotFoundException("Usuário Não Encontrado! ID: " + dto.getUsuario());
        }
    }

}
