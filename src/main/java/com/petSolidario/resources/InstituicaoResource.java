package com.petSolidario.resources;

import com.petSolidario.domains.Instituicao;
import com.petSolidario.domains.dtos.InstituicaoDTO;
import com.petSolidario.services.InstituicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/instituicao")
public class InstituicaoResource {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping
    public ResponseEntity<List<InstituicaoDTO>> findAll() {
        return ResponseEntity.ok().body(instituicaoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InstituicaoDTO> findById(@PathVariable Long id) {
        Instituicao obj = this.instituicaoService.findById(id);
        return ResponseEntity.ok().body(new InstituicaoDTO(obj));
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<InstituicaoDTO> findByCnpj(@PathVariable String cnpj) {
        Instituicao obj = this.instituicaoService.findByCnpj(cnpj);
        return ResponseEntity.ok().body(new InstituicaoDTO(obj));
    }

    @PostMapping
    public ResponseEntity<InstituicaoDTO> create(@Valid @RequestBody InstituicaoDTO dto) {
        Instituicao instituicao = instituicaoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(instituicao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InstituicaoDTO> update(@PathVariable Long id, @Valid @RequestBody InstituicaoDTO objDto) {
        Instituicao obj = instituicaoService.update(id, objDto);
        return ResponseEntity.ok().body(new InstituicaoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instituicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
