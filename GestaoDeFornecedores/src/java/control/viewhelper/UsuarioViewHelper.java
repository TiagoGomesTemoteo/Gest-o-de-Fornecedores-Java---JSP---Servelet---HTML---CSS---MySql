/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.viewhelper;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dominio.EntidadeDominio;
import model.dominio.Usuario;
import util.Contem;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class UsuarioViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        String operacao = request.getParameter("operacao");
        
        Usuario usuario = new Usuario();
        
        if(operacao.equals("Acessar")){
            String login = request.getParameter("campoLogin");
            String senha = request.getParameter("campoSenha"); 

            if(Contem.contemDado(login)){
                usuario.setLogin(login);
            }
            if(Contem.contemDado(senha)){
                usuario.setSenha(senha);
            }
        }
        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        
        String operacao = request.getParameter("operacao");
        
        RequestDispatcher rD = null;
        
        if(operacao.equals("Acessar")){
            if(resultado.getMsg().trim().equals("Liberado")){
                rD = request.getRequestDispatcher("index.html");
            }else{
                request.setAttribute("acesso", resultado);
                rD = request.getRequestDispatcher("login.jsp");
            }
        }
        
        rD.forward(request, reponse);
    }
    
}
