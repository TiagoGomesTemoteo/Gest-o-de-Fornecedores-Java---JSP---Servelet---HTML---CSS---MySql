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
public class Produto extends EntidadeDominio{
    private String nome;
    private String descricao;
    private Fornecedor fornecedor;
    
    public Produto(){
        super(0);
        this.nome = " ";
        this.descricao = " ";
        this.fornecedor = new Fornecedor();
    }
    
    
    public Produto(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.fornecedor = new Fornecedor();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
