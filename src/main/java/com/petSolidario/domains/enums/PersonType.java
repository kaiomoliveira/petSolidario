package com.petSolidario.domains.enums;

public enum PersonType {
    ADMIN(0,"ROLE_ADMINS)"),USER(1,"ROLE_USER"),INSTITUICAO(2,"ROLE_INSTITUICAO");

    private Integer id;
    private String personType;

    PersonType(Integer id, String personType) {
        this.id = id;
        this.personType = personType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public static PersonType toEnum(Integer id) {
        if (id == null) return null;
        for (PersonType x : PersonType.values()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido!");
    }

}
