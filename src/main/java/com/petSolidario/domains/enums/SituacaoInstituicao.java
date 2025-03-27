package com.petSolidario.domains.enums;

public enum SituacaoInstituicao {

    APROVADA(0,"APROVADA"),EMAPROVACAO(1,"EMAPROVACAO"),IRREGULAR(2,"IRREGULAR");

    private Integer id;
    private String situacao;

    SituacaoInstituicao() {
    }

    SituacaoInstituicao(Integer id, String situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public static SituacaoInstituicao toEnum(Integer id) {
        if (id == null) return null;
        for (SituacaoInstituicao x : SituacaoInstituicao.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Situação inválida!");
    }
}
