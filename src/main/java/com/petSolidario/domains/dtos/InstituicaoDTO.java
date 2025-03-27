package com.petSolidario.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.Instituicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InstituicaoDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    @NotNull(message = "O campo nome não pode ser nulo!")
    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String nome;

    @NotNull(message = "O campo cnpj não pode ser nulo!")
    @NotBlank(message = "O campo cnpj não pode ser vazio!")
    private String cnpj;

    @NotNull(message = "O campo razão Social não pode ser nulo!")
    @NotBlank(message = "O campo razão Social não pode ser vazio!")
    private String razaoSocial;

    @NotNull(message = "O campo Usuario é requerido!")
    private Long usuario; // IDs dos usuários
    private String nomeUsuario; // nome do usuário

    private int situacao;

    public InstituicaoDTO() {
    }

    public InstituicaoDTO(Instituicao instituicao) {
        this.id = instituicao.getId();
        this.nome = instituicao.getNome();
        this.cnpj = instituicao.getCnpj();
        this.razaoSocial = instituicao.getRazaoSocial();
        this.usuario = instituicao.getUsuario().getId();
        this.nomeUsuario = instituicao.getUsuario().getNome();
        this.situacao = instituicao.getSituacaoInstituicao().getId();
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

    public @NotNull(message = "O campo cnpj não pode ser nulo!") @NotBlank(message = "O campo cnpj não pode ser vazio!") String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull(message = "O campo cnpj não pode ser nulo!") @NotBlank(message = "O campo cnpj não pode ser vazio!") String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotNull(message = "O campo razão Social não pode ser nulo!") @NotBlank(message = "O campo razão Social não pode ser vazio!") String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull(message = "O campo razão Social não pode ser nulo!") @NotBlank(message = "O campo razão Social não pode ser vazio!") String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @NotNull(message = "O campo Usuario é requerido!")
    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull(message = "O campo Usuario é requerido!") Long usuario) {
        this.usuario = usuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
