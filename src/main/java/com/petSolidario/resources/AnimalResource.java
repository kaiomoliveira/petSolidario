package com.petSolidario.resources;


import com.petSolidario.domains.Animal;
import com.petSolidario.domains.dtos.AnimalDTO;
import com.petSolidario.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/animal")
public class AnimalResource {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> findAll() {
        return ResponseEntity.ok().body(animalService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long id) {
        Animal obj = this.animalService.findById(id);
        return ResponseEntity.ok().body(new AnimalDTO(obj));
    }

    @GetMapping(value = "/registro/{registro}")
    public ResponseEntity<AnimalDTO> findByRegistro(@PathVariable String registro) {
        Animal obj = this.animalService.findByRegistro(registro);
        return ResponseEntity.ok().body(new AnimalDTO(obj));
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> create(@Valid @RequestBody AnimalDTO dto) {
        Animal animal = animalService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animal.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable Long id, @Valid @RequestBody AnimalDTO objDto) {
        Animal obj = animalService.update(id, objDto);
        return ResponseEntity.ok().body(new AnimalDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> delete(@PathVariable Long id) {
        animalService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
