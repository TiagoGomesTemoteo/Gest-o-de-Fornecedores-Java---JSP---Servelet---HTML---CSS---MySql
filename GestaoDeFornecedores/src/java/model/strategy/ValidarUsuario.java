/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import model.dao.UsuarioDAO;
import model.dominio.EntidadeDominio;

/**
 *
 * @author Tiago
 */
public class ValidarUsuario implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidade) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if(usuarioDAO.validarLogin(entidade)){
            return "Liberado";
        }else{
            return "Negado";
        }
        
    }
    
}
