package com.petSolidario.resources;


import com.petSolidario.domains.Usuario;
import com.petSolidario.domains.dtos.UsuarioDTO;
import com.petSolidario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }


    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> findByCpf(@PathVariable String cpf){
        Usuario obj = this.usuarioService.findByCpf(cpf);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email){
        Usuario obj = this.usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = usuarioService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO objDto){
        Usuario Obj = usuarioService.update(id, objDto);
        return ResponseEntity.ok().body(new UsuarioDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id){
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
