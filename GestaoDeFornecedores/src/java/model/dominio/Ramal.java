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
public class Ramal extends Telefone{
    private String codigo;
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Ramal(String ddd, String numero, String ddi, TipoTelefone tpTelefone, boolean fromFornecedor, String codigo, String descricao) {
        super(ddd, numero, ddi, tpTelefone, fromFornecedor);
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    
}
