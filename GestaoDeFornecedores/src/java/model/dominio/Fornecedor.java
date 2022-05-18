/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Tiago
 */
public class Fornecedor extends EntidadeDominio{
    private String email;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Date dtCadastro;
    private String rzSocial;
    private String nmFantasia;
    private Endereco endereco;
    private String tpFornecimento;
    private String status;
    
    private List<CNAE> listaCNAE;
    private List<Empresa> listaEmpresas;
    private List<Contato> listaContatos ;
    private List<Telefone> listaTelefones;
    private List<Produto> produtosOfertados;
    private List<Servico> servicosOfertados;
    
    
    public Fornecedor(){
        super(0);
        this.email = " ";
        this.cnpj = " ";
        this.inscricaoEstadual = " ";
        this.inscricaoMunicipal = " ";
        this.dtCadastro = new Date();
        this.rzSocial = " ";
        this.nmFantasia = " ";
        this.endereco = new Endereco();
        this.tpFornecimento = " ";
        this.status = " ";
        this.listaCNAE = new ArrayList <CNAE>();
        this.listaEmpresas = new ArrayList <Empresa>();
        this.listaContatos = new ArrayList <Contato>();
        this.listaTelefones = new ArrayList <Telefone>();
        this.produtosOfertados = new ArrayList <Produto>();
        this.servicosOfertados = new ArrayList <Servico>();
        
    }
    
    public Fornecedor(int id, String email, String cnpj, String inscricaoEstadual, 
            String inscricaoMunicipal, Date dtCadastro, String rzSocial, String nmFantasia, 
            Endereco endereco, String tpFornecimento, String status, List<CNAE> listaCNAE, 
            List<Empresa> listaEmpresas, List<Contato> listaContatos, List<Telefone> listaTelefones) {
        
        super(id);
        this.email = email;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.dtCadastro = dtCadastro;
        this.rzSocial = rzSocial;
        this.nmFantasia = nmFantasia;
        this.endereco = endereco;
        this.tpFornecimento = tpFornecimento;
        this.status = status;
        this.listaCNAE = listaCNAE;
        this.listaEmpresas = listaEmpresas;
        this.listaContatos = listaContatos;
        this.listaTelefones = listaTelefones;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {    
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getRzSocial() {
        return rzSocial;
    }

    public void setRzSocial(String rzSocial) {
        this.rzSocial = rzSocial;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTpFornecimento() {
        return tpFornecimento;
    }

    public void setTpFornecimento(String tpFornecimento) {
        this.tpFornecimento = tpFornecimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CNAE> getListaCNAE() {
        return listaCNAE;
    }

    public void setListaCNAE(List<CNAE> listaCNAE) {
        this.listaCNAE = listaCNAE;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public List<Telefone> getListaTelefones() {
        return listaTelefones;
    }

    public void setListaTelefones(List<Telefone> listaTelefones) {
        this.listaTelefones = listaTelefones;
    }

    public List<Produto> getProdutosOfertados() {
        return produtosOfertados;
    }

    public void setProdutosOfertados(List<Produto> produtosOfertados) {
        this.produtosOfertados = produtosOfertados;
    }

    public List<Servico> getServicosOfertados() {
        return servicosOfertados;
    }

    public void setServicosOfertados(List<Servico> servicosOfertados) {
        this.servicosOfertados = servicosOfertados;
    }
    
    public void addCNAE (CNAE cnae){
        if(this.listaCNAE.size() < 1){
            this.listaCNAE = new ArrayList <CNAE>();
        }
        this.listaCNAE.add(cnae);
    }
    
    public void addEmpresa (Empresa empresa){
        if(this.listaEmpresas.size() < 1){
            this.listaEmpresas = new ArrayList <Empresa>();
        }
        this.listaEmpresas.add(empresa);
    }
    
    public void addContato (Contato contato){
        if(this.listaContatos.size() < 1){
            this.listaContatos = new ArrayList <Contato>();
        }
        this.listaContatos.add(contato);
    }
    
    public void addTelefone (Telefone telefone){
        if(this.listaTelefones.size() < 1){
            this.listaTelefones = new ArrayList <Telefone>();
        }
        this.listaTelefones.add(telefone);
    }   

    public void addProdutos (Produto produto){
        if(this.produtosOfertados.size() < 1){
            this.produtosOfertados = new ArrayList <Produto>();
        }
        this.produtosOfertados.add(produto);
    }       
    
    public void addServico (Servico servico){
        if(this.servicosOfertados.size() < 1){
            this.servicosOfertados = new ArrayList <Servico>();
        }
        this.servicosOfertados.add(servico);
    }      
}
