package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.dtos.AdocaoDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "adocao")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private String tituloSolicitacao;
    private String descricao;

    @ManyToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="idinstituicao")
    private Instituicao instituicao;

    @OneToOne
    @JoinColumn(name="idanimal")
    private Animal animal;

    public Adocao() {
    }

    public Adocao(UUID id, String tituloSolicitacao, String descricao, Usuario usuario, Instituicao instituicao, Animal animal) {
        this.id = id;
        this.tituloSolicitacao = tituloSolicitacao;
        this.descricao = descricao;
        this.usuario = usuario;
        this.instituicao = instituicao;
        this.animal = animal;
    }

    public Adocao(AdocaoDTO dto){

        this.id = dto.getId();
        this.tituloSolicitacao = dto.getTituloSolicitacao();
        this.descricao = dto.getDescricao();
        this.instituicao = new Instituicao();
        this.instituicao.setId(dto.getInstituicao().getId());
        this.usuario = new Usuario();
        this.usuario.setId(dto.getUsuario().getId());
        this.animal = new Animal();
        this.animal.setId(dto.getAnimal().getId());
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
