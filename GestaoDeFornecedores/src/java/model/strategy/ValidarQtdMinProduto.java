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
public class ValidarQtdMinProduto implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade){
        
        Fornecedor fornecedor = (Fornecedor) entidade;
        
        if(fornecedor.getProdutosOfertados().isEmpty()){
            return "O fornecedor precisa oferecer no mínimo um produto";
        }
        
        return null;
    }
}
