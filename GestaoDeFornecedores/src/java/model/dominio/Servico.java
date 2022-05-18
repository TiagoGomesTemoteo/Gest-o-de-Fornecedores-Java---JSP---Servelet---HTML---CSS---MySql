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
public class Servico extends EntidadeDominio{
    private String descricao;
    private Fornecedor fornecedor;
    
    public Servico(){
        super(0);
        this.descricao = " ";
        this.fornecedor = new Fornecedor();
    }
    
    public Servico(String descricao) {
        super();
        this.descricao = descricao;
        this.fornecedor = new Fornecedor();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
   
}
