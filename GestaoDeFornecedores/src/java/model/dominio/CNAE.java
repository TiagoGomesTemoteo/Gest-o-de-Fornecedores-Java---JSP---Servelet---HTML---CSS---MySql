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
public class CNAE extends EntidadeDominio{
    private String numero;
    private Fornecedor fornecedor;

    public CNAE() {
        super();
        this.numero = " ";
        this.fornecedor = new Fornecedor();
    }
    
    public CNAE(String numero) {
        this.numero = numero;
        this.fornecedor = new Fornecedor();
    }
    
    public CNAE(int id, String numero) {
        super(id);
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    


    
    
}
