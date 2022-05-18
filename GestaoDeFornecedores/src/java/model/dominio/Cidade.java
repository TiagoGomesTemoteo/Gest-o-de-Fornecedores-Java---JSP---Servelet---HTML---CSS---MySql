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
public class Cidade {
    private String descricao;
    private UF uf;

    public Cidade() {
        this.descricao = " ";
        this.uf = new UF();
    }
    
    public Cidade(String descricao, UF uf) {
        this.descricao = descricao;
        this.uf = uf;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }


    
}
