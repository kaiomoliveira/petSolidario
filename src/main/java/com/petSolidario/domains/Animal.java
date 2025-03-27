package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.dtos.AnimalDTO;
import com.petSolidario.domains.enums.Porte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_animal")
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    @NotNull @NotBlank
    @Column(unique = true)
    private String registro;

    @NotNull @NotBlank
    private String nome;

    @NotNull @NotBlank
    private String especie;

    @NotNull @NotBlank
    private String raca;

    @NotNull @NotBlank
    private String cor;

    @NotNull @NotBlank
    private String sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name="idinstituicao")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "porte")
    private Porte porte;

    @OneToOne
    @JoinColumn(name="idadocao")
    private Adocao adocao;

    public Animal() {

    }

    public Animal(Long id, String registro, String nome, String especie, String raca, String cor, String sexo, Date dataNascimento, Instituicao instituicao, Usuario usuario, Porte porte) {
        this.id = id;
        this.registro = registro;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.instituicao = instituicao;
        this.usuario = usuario;
        this.porte = porte;
    }

    public Animal(AnimalDTO dto) {
        this.id = dto.getId();
        this.registro = dto.getRegistro();
        this.nome = dto.getNome();
        this.especie = dto.getEspecie();
        this.raca = dto.getRaca();
        this.cor = dto.getCor();
        this.sexo = dto.getSexo();
        this.dataNascimento = dto.getDataNascimento();
        this.instituicao = new Instituicao();
        this.instituicao.setId(dto.getInstituicao());
        this.usuario = new Usuario();
        this.usuario.setId(dto.getUsuario());
        this.porte = Porte.toEnum(dto.getPorte());
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

    public @NotNull @NotBlank String getRegistro() {
        return registro;
    }

    public void setRegistro(@NotNull @NotBlank String registro) {
        this.registro = registro;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank String getEspecie() {
        return especie;
    }

    public void setEspecie(@NotNull @NotBlank String especie) {
        this.especie = especie;
    }

    public @NotNull @NotBlank String getRaca() {
        return raca;
    }

    public void setRaca(@NotNull @NotBlank String raca) {
        this.raca = raca;
    }

    public @NotNull @NotBlank String getCor() {
        return cor;
    }

    public void setCor(@NotNull @NotBlank String cor) {
        this.cor = cor;
    }

    public @NotNull @NotBlank String getSexo() {
        return sexo;
    }

    public void setSexo(@NotNull @NotBlank String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
