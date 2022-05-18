/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import model.dao.FornecedorDAO;
import model.dominio.EntidadeDominio;
import model.dominio.Fornecedor;

/**
 *
 * @author Tiago
 */
public class ValidarCNPJ implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidade) {
        
        Fornecedor fornecedor = (Fornecedor) entidade;
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        
        String cnpj = fornecedor.getCnpj();
        
        cnpj = cnpj.replace(".","");
        cnpj = cnpj.replace("-","");
        cnpj = cnpj.replace("/","");
        cnpj = cnpj.replace(" ","");
        
        if(cnpj.length() != 14){
            return "CNPJ Inválido";
        }else{
            if(!(fornecedorDAO.consultar(fornecedor)).getCnpj().equals(" ")){
                return "CNPJ já existe";
            }
        }
        
        return null;
    }
    
}
