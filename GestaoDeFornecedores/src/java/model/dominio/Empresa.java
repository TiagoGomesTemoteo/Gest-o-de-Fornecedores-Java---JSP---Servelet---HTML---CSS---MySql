/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Empresa {
    private String tpEmpresa;
    private List <Fornecedor> fornecedores;

    public String getTpEmpresa() {
        return tpEmpresa;
    }

    public void setTpEmpresa(String tpEmpresa) {
        this.tpEmpresa = tpEmpresa;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void addFornecedor (Fornecedor forne){
        if(this.fornecedores.size() < 1){
            this.fornecedores = new ArrayList <Fornecedor>();
        }
        this.fornecedores.add(forne);
    }
   
}
