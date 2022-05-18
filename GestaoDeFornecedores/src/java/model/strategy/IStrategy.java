/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import model.dominio.EntidadeDominio;

/**
 *
 * @author Tiago
 */
public interface IStrategy {
    public String processar(EntidadeDominio entidade);
}
