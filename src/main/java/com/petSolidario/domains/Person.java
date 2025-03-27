package com.petSolidario.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petSolidario.domains.enums.PersonType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    protected Long id;

    @Column(unique = true)
    protected String email;
    protected String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createAt = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    protected Set<Integer> personType = new HashSet<>();

    public Person() {
        addPersonType(PersonType.USER);
    }

    public Person(Long id,String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        addPersonType(PersonType.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Set<PersonType> getPersonType() {
        return personType.stream().map(PersonType::toEnum).collect(Collectors.toSet());
    }

    public void addPersonType(PersonType personType) {
        this.personType.add(personType.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
