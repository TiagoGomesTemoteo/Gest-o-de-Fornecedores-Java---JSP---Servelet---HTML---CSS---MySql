/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dominio;

/**
 *
 * @author Tiago
 */
public class UF {
    private String descricao;
    private String sigla;
    private Pais pais;

    public UF() {
       this.descricao = " ";
       this.sigla = " ";
       this.pais = new Pais();
    }
    
    
    public UF(String descricao, String sigla, Pais pais) {
        this.descricao = descricao;
        this.sigla = sigla;
        this.pais = pais;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
    
}
