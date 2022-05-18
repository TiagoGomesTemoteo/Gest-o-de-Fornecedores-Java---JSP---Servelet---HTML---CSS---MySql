/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.command;

import model.dominio.EntidadeDominio;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class VisualizarCommand extends AbstractCommand{

    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return fachada.visualizar(entidade);
    }
    
}
