package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petSolidario.domains.dtos.UsuarioDTO;
import com.petSolidario.domains.enums.PersonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Person {

    private String nome;
    private String sexo;

    @Column(unique = true)
    @CPF
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Animal> animais = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Instituicao> instituicoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Adocao> adocoes = new ArrayList<>();


    public Usuario() {
        super();
        addPersonType(PersonType.USER);
    }

    public Usuario(Long id, String email, String password, String nome, String sexo, String cpf, LocalDate dataNascimento) {
        super(id, email, password);
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        addPersonType(PersonType.USER);
    }


    public Usuario(UsuarioDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.sexo = dto.getSexo();
        this.cpf = dto.getCpf();
        this.dataNascimento = dto.getDataNascimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void setAdocoes(List<Adocao> adocoes) {
        this.adocoes = adocoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
