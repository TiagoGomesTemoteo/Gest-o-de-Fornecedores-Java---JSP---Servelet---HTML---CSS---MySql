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
public class Contato extends EntidadeDominio{
    private String nome;
    private String email;
    private Departamento departamento;
    private Telefone telefone;
    private Fornecedor fornecedor;
    
    public Contato() {
        super();
        this.nome = " ";
        this.email = " ";
        this.departamento = new Departamento();
        this.telefone = new Telefone();
        this.fornecedor = new Fornecedor();
       
    }

    public Contato(String nome, String email, Departamento departamento, Telefone telefone) {
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
        this.telefone = telefone;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
 
}
