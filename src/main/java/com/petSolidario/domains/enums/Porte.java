package com.petSolidario.domains.enums;

public enum Porte {


    PEQUENO(0,"PEQUENO"),MEDIO(1,"MEDIO"),GRANDE(2,"GRANDE"),;

    private Integer id;
    private String porte;

    Porte() {
    }

    Porte(Integer id,String porte) {
        this.porte = porte;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public static Porte toEnum(Integer id) {
        if (id == null) return null;
        for (Porte x : Porte.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Porte inválida!");
    }
}
