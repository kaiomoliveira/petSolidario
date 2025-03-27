package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petSolidario.domains.dtos.InstituicaoDTO;
import com.petSolidario.domains.enums.PersonType;
import com.petSolidario.domains.enums.SituacaoInstituicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "instituicao")
public class Instituicao extends Person {

    @Column(unique = true)
    private String cnpj;
    private String nome;
    private String razaoSocial;

    @ManyToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "instituicao")
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "instituicao")
    private List<Animal> animais = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "instituicao")
    private List<Adocao> adocoes = new ArrayList<>();


    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "situacao")
    private SituacaoInstituicao situacaoInstituicao;

    public Instituicao() {
        super();
    }


    public Instituicao(Long id, String email, String password, String cnpj, String nome, String razaoSocial, Usuario usuario, SituacaoInstituicao situacaoInstituicao) {
        super(id, email, password);
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.usuario = usuario;
        this.situacaoInstituicao = situacaoInstituicao;
        addPersonType(PersonType.INSTITUICAO);
    }

    public Instituicao(InstituicaoDTO instituicaoDTO) {
        this.id = instituicaoDTO.getId();
//        this.email = instituicaoDTO.getEmail();
//        this.password = instituicaoDTO.getPassword();
//        this.createAt = instituicaoDTO.getCreateAt();
        this.cnpj = instituicaoDTO.getCnpj();
        this.nome = instituicaoDTO.getNome();
        this.razaoSocial = instituicaoDTO.getRazaoSocial();
        this.usuario = new Usuario();
        this.usuario.setId(instituicaoDTO.getUsuario());
        this.situacaoInstituicao = SituacaoInstituicao.toEnum(instituicaoDTO.getSituacao());
        addPersonType(PersonType.INSTITUICAO);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void setAdocoes(List<Adocao> adocoes) {
        this.adocoes = adocoes;
    }

    public SituacaoInstituicao getSituacaoInstituicao() {
        return situacaoInstituicao;
    }

    public void setSituacaoInstituicao(SituacaoInstituicao situacaoInstituicao) {
        this.situacaoInstituicao = situacaoInstituicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instituicao that = (Instituicao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
