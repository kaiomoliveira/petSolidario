package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.dtos.ProdutoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    private String nome;

    @Digits(integer = 15,fraction = 3)
    private BigDecimal peso;

    @NotNull @NotBlank
    @Column(unique = true)
    private String codigoDeBarras;

    @ManyToOne
    @JoinColumn(name="idinstituicao")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    public Produto() {
    }

    public Produto( Long id, LocalDate dataCadastro, String nome, BigDecimal peso, String codigoDeBarras, Instituicao instituicao, Usuario usuario) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.peso = peso;
        this.codigoDeBarras = codigoDeBarras;
        this.instituicao = instituicao;
        this.usuario = usuario;
    }

    public Produto(ProdutoDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.peso = dto.getPeso();
        this.codigoDeBarras = dto.getCodigoDeBarras();
        this.instituicao = new Instituicao();
        this.instituicao.setId(dto.getInstituicao());
        this.usuario = new Usuario();
        this.usuario.setId(dto.getUsuario());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal peso) {
        this.peso = peso;
    }

    public @NotNull @NotBlank String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(@NotNull @NotBlank String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
