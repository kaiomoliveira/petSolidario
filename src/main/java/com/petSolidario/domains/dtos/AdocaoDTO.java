package com.petSolidario.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.Adocao;
import com.petSolidario.domains.Animal;
import com.petSolidario.domains.Instituicao;
import com.petSolidario.domains.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AdocaoDTO {
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @NotNull(message = "O campo título da solicitação não pode ser nulo!")
    @NotBlank(message = "O campo título da solicitação não pode ser vazio!")
    private String tituloSolicitacao;

    @NotNull(message = "O campo descrição não pode ser nulo!")
    @NotBlank(message = "O campo descrição não pode ser vazio!")
    private String descricao;

    @NotNull(message = "O campo usuário é requerido!")
    private Usuario usuario;

    @NotNull(message = "O campo instituição é requerido!")
    private Instituicao instituicao;

    @NotNull(message = "O campo animal é requerido!")
    private Animal animal;

    public AdocaoDTO() {

    }

    public AdocaoDTO(UUID id, LocalDate startDate, LocalDate endDate, String tituloSolicitacao, String descricao, Usuario usuario, Instituicao instituicao, Animal animal) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tituloSolicitacao = tituloSolicitacao;
        this.descricao = descricao;
        this.usuario = usuario;
        this.instituicao = instituicao;
        this.animal = animal;
    }

    public AdocaoDTO(Adocao obj) {
        this.id = obj.getId();
        this.startDate = obj.getStartDate();
        this.endDate = obj.getEndDate();
        this.tituloSolicitacao = obj.getTituloSolicitacao();
        this.descricao = obj.getDescricao();
        this.usuario = obj.getUsuario();
        this.instituicao = obj.getInstituicao();
        this.animal = obj.getAnimal();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTituloSolicitacao() {
        return tituloSolicitacao;
    }

    public void setTituloSolicitacao(String tituloSolicitacao) {
        this.tituloSolicitacao = tituloSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
