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
public class TipoFornecimento {
    
   public static final String VENDA = "VENDA"; 
   public static final String SERVICO = "SERVICO"; 

    public static String getVENDA() {
        return VENDA;
    }

    public static String getSERVICO() {
        return SERVICO;
    }

}
