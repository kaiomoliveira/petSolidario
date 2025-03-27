package com.petSolidario.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.Produto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProdutoDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    @NotNull(message = "O campo nome não pode ser nulo!")
    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String nome;

    @NotNull(message = "O campo peso não pode ser nulo!")
    @Digits(integer = 15,fraction = 3)
    private BigDecimal peso;

    @NotNull(message = "O campo Código de Barras não pode ser nulo!")
    @NotBlank(message = "O campo Código de Barras não pode ser vazio!")
    private String codigoDeBarras;

    @NotNull(message = "O campo instituicao é requerido!")
    private Long instituicao;
    private String nomeInstituicao;

    private Long usuario;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.dataCadastro = produto.getDataCadastro();
        this.nome = produto.getNome();
        this.peso = produto.getPeso();
        this.codigoDeBarras = produto.getCodigoDeBarras();
        this.instituicao = produto.getInstituicao().getId();
        this.usuario = produto.getUsuario().getId();
        this.nomeInstituicao = produto.getInstituicao().getNome();
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

    public @NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo peso não pode ser nulo!") @Digits(integer = 15, fraction = 3) BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(@NotNull(message = "O campo peso não pode ser nulo!") @Digits(integer = 15, fraction = 3) BigDecimal peso) {
        this.peso = peso;
    }

    public @NotNull(message = "O campo Código de Barras não pode ser nulo!") @NotBlank(message = "O campo Código de Barras não pode ser vazio!") String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(@NotNull(message = "O campo Código de Barras não pode ser nulo!") @NotBlank(message = "O campo Código de Barras não pode ser vazio!") String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    @NotNull(message = "O campo instituicao é requerido!")
    public Long getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(@NotNull(message = "O campo instituicao é requerido!") Long instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
