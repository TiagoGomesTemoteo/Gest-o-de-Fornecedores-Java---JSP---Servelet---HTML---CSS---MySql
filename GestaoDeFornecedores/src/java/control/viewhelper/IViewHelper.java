/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.viewhelper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dominio.EntidadeDominio;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public interface IViewHelper {
    public EntidadeDominio getEntidade(HttpServletRequest request);
    
    public void setView (Resultado resultado, HttpServletRequest request, HttpServletResponse reponse
    )throws IOException, ServletException; 
}
