/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import model.dominio.EntidadeDominio;
import model.dominio.Fornecedor;

/**
 *
 * @author Tiago
 */
public class ValidarQtdMinContatoAssociado implements IStrategy{
    
    @Override
    public String processar(EntidadeDominio entidade) {
        
        Fornecedor fornecedor = (Fornecedor) entidade;
        
        if(fornecedor.getListaContatos().isEmpty()){
            return "O fornecedor precisa ter no mínimo um contato associado";
        }
        
        return null;
    }
}
