/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import java.util.List;
import model.dao.CnaeDAO;
import model.dominio.*;


/**
 *
 * @author Tiago
 */
public class ValidarExistenciaCNAE implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidade) {
        String CnaeExistem = null;
        CnaeDAO cnaeDao = new CnaeDAO();
        
        Fornecedor fornecedor = (Fornecedor) entidade;
        
        List<CNAE> listaCnae = fornecedor.getListaCNAE();
            
            for(CNAE cnae : listaCnae){
                if(cnae.getNumero().length() == 7){
                    if(cnaeDao.existeCNAE(cnae)){
                        CnaeExistem += "\n O CNAE : " + cnae.getNumero() + " já existe";
                    }
                }else{
                    CnaeExistem += "\n CNAE Inválido";
                }
            }
        
        
        return CnaeExistem;
    }
    
}
