package com.petSolidario.services;

import com.petSolidario.domains.Animal;
import com.petSolidario.domains.Usuario;
import com.petSolidario.domains.dtos.AnimalDTO;
import com.petSolidario.repositories.AnimalRepository;
import com.petSolidario.repositories.InstituicaoRepository;
import com.petSolidario.repositories.UsuarioRepository;
import com.petSolidario.services.exceptions.DataIntegrityViolationException;
import com.petSolidario.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {


    @Autowired
    private AnimalRepository animalRepo;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Retorna uma lista de AnimalDTO
    public List<AnimalDTO> findAll() {
        return animalRepo.findAll().stream()
                .map(obj -> new AnimalDTO(obj))
                .collect(Collectors.toList());
    }

    public Animal findById(Long id) {
        Optional<Animal> obj = animalRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Animal Não Encontrado! ID: " + id));
    }


    public Animal findByRegistro(String registro) {
        Optional<Animal> obj = animalRepo.findByRegistro(registro);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Animal Não Encontrado! REGISTRO: " + registro));
    }

    public Animal create(AnimalDTO dto) {
        dto.setId(null);
        validaAnimal(dto);
        Animal obj = new Animal(dto);

        return animalRepo.save(obj);
    }

    public Animal update(Long id, AnimalDTO dto) {
        dto.setId(id);
        Animal oldObj = findById(id);
        validaAnimal(dto);
        oldObj = new Animal(dto);
        return animalRepo.save(oldObj);
    }

    public void delete(Long id) {
        Animal obj = findById(id);
        animalRepo.deleteById(id);
    }

    // Validações específicas para o animal
    private void validaAnimal(AnimalDTO dto) {

        Optional<Animal> obj = animalRepo.findByRegistro(dto.getRegistro());
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
