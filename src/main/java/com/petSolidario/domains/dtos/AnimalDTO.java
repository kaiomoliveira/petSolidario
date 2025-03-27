package com.petSolidario.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AnimalDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    @NotNull(message = "O campo registro não pode ser nulo!")
    @NotBlank(message = "O campo registro não pode ser vazio!")
    private String registro;

    @NotNull(message = "O campo nome não pode ser nulo!")
    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String nome;

    @NotNull(message = "O campo especie não pode ser nulo!")
    @NotBlank(message = "O campo especie não pode ser vazio!")
    private String especie;

    @NotNull(message = "O campo raça não pode ser nulo!")
    @NotBlank(message = "O campo raça não pode ser vazio!")
    private String raca;

    @NotNull(message = "O campo cor não pode ser nulo!")
    @NotBlank(message = "O campo cor não pode ser vazio!")
    private String cor;

    @NotNull(message = "O campo sexo não pode ser nulo!")
    @NotBlank(message = "O campo sexo não pode ser vazio!")
    private String sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotNull(message = "O campo instituicao é requerido!")
    private Long instituicao;
    private String nomeInstituicao;

    private Long usuario;

    private int porte;

    public AnimalDTO() {

    }

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.dataCadastro = animal.getDataCadastro();
        this.registro = animal.getRegistro();
        this.nome = animal.getNome();
        this.especie = animal.getEspecie();
        this.raca = animal.getRaca();
        this.cor = animal.getCor();
        this.sexo = animal.getSexo();
        this.dataNascimento = animal.getDataNascimento();
        this.instituicao = animal.getInstituicao().getId();
        this.nomeInstituicao = animal.getInstituicao().getNome();
        this.usuario = animal.getUsuario().getId();
        this.porte = animal.getPorte().getId();

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

    public @NotNull(message = "O campo registro não pode ser nulo!") @NotBlank(message = "O campo registro não pode ser vazio!") String getRegistro() {
        return registro;
    }

    public void setRegistro(@NotNull(message = "O campo registro não pode ser nulo!") @NotBlank(message = "O campo registro não pode ser vazio!") String registro) {
        this.registro = registro;
    }

    public @NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo especie não pode ser nulo!") @NotBlank(message = "O campo especie não pode ser vazio!") String getEspecie() {
        return especie;
    }

    public void setEspecie(@NotNull(message = "O campo especie não pode ser nulo!") @NotBlank(message = "O campo especie não pode ser vazio!") String especie) {
        this.especie = especie;
    }

    public @NotNull(message = "O campo raça não pode ser nulo!") @NotBlank(message = "O campo raça não pode ser vazio!") String getRaca() {
        return raca;
    }

    public void setRaca(@NotNull(message = "O campo raça não pode ser nulo!") @NotBlank(message = "O campo raça não pode ser vazio!") String raca) {
        this.raca = raca;
    }

    public @NotNull(message = "O campo cor não pode ser nulo!") @NotBlank(message = "O campo cor não pode ser vazio!") String getCor() {
        return cor;
    }

    public void setCor(@NotNull(message = "O campo cor não pode ser nulo!") @NotBlank(message = "O campo cor não pode ser vazio!") String cor) {
        this.cor = cor;
    }

    public @NotNull(message = "O campo sexo não pode ser nulo!") @NotBlank(message = "O campo sexo não pode ser vazio!") String getSexo() {
        return sexo;
    }

    public void setSexo(@NotNull(message = "O campo sexo não pode ser nulo!") @NotBlank(message = "O campo sexo não pode ser vazio!") String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }
}
