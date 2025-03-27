package com.petSolidario.resources;


import com.petSolidario.domains.Produto;
import com.petSolidario.domains.dtos.ProdutoDTO;
import com.petSolidario.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        Produto obj = this.produtoService.findById(id);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }


    @GetMapping(value = "/codigoDeBarras/{codigoDeBarras}")
    public ResponseEntity<ProdutoDTO> findByCpf(@PathVariable String codigoBarras){
        Produto obj = this.produtoService.findByCodigoDeBarras(codigoBarras);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO dto){
        Produto produto = produtoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO objDto){
        Produto Obj = produtoService.update(id, objDto);
        return ResponseEntity.ok().body(new ProdutoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
        produtoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
