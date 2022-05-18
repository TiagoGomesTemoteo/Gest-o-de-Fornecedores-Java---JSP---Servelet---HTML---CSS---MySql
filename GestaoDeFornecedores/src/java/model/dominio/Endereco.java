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
public class Endereco extends EntidadeDominio{
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private TipoLogradouro tpLogradouro;
    private TipoEndereco tpEndereco;
    private Cidade cidade;

    
    public Endereco(){
        this.cep = " ";
        this.logradouro = " ";
        this.numero = " ";
        this.bairro = " ";
        this.tpLogradouro = new TipoLogradouro();
        this.tpEndereco = new TipoEndereco();
        this.cidade = new Cidade();
        this.complemento = " ";
    }
    
    public Endereco(String cep, String logradouro, String numero, 
            String bairro, TipoLogradouro tpLogradouro, TipoEndereco tpEndereco, Cidade cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.tpLogradouro = tpLogradouro;
        this.tpEndereco = tpEndereco;
        this.cidade = cidade;
    }
    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public TipoLogradouro getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(TipoLogradouro tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public TipoEndereco getTpEndereco() {
        return tpEndereco;
    }

    public void setTpEndereco(TipoEndereco tpEndereco) {
        this.tpEndereco = tpEndereco;
    }
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
      
}
