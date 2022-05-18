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
public class Log {
    private long dataHora;

    public long getDataHora() {
        return dataHora;
    }

    public void setDataHora(long dataHora) {
        this.dataHora = dataHora;
    }

    public Log(long dataHora) {
        this.dataHora = dataHora;
    }
    
    public void registrarTransacao(Usuario usuario, Fornecedor fornecedor){}
}
