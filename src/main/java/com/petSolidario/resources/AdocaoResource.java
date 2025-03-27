package com.petSolidario.resources;


import com.petSolidario.domains.Adocao;
import com.petSolidario.domains.dtos.AdocaoDTO;
import com.petSolidario.services.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/adocao")
public class AdocaoResource {

    @Autowired
    private AdocaoService adocaoService;

    @GetMapping
    public ResponseEntity<List<AdocaoDTO>> findAll() {
        return ResponseEntity.ok().body(adocaoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdocaoDTO> findById(@PathVariable UUID id) {
        Adocao obj = this.adocaoService.findById(id);
        return ResponseEntity.ok().body(new AdocaoDTO(obj));
    }

//    @GetMapping(value = "/registro/{registro}")
//    public ResponseEntity<AdocaoDTO> findByRegistro(@PathVariable String registro) {
//        Adocao obj = this.adocaoService.findByRegistro(registro);
//        return ResponseEntity.ok().body(new AdocaoDTO(obj));
//    }

    @PostMapping
    public ResponseEntity<AdocaoDTO> create(@Valid @RequestBody AdocaoDTO dto) {
        Adocao adocao = adocaoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(adocao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdocaoDTO> update(@PathVariable UUID id, @Valid @RequestBody AdocaoDTO objDto) {
        Adocao obj = adocaoService.update(id, objDto);
        return ResponseEntity.ok().body(new AdocaoDTO(obj));
    }

}
