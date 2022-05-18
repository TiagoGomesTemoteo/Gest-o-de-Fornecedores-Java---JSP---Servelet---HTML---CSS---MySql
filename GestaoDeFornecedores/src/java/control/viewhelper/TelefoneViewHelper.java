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
import model.dominio.*;
import util.Contem;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class TelefoneViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        
        Telefone telefone = new Telefone();
        TipoTelefone tpTelefone =   new TipoTelefone();
        
        String operacao = request.getParameter("operacao");
        
        if(operacao.equals("Alterar") || operacao.equals("Excluir")){
            String hiddenIdTelefone = request.getParameter("hiddenIdTelefone");
            
            if(Contem.contemDado(hiddenIdTelefone)){
                telefone.setId(Integer.parseInt(hiddenIdTelefone));
            }
            
            if(operacao.equals("Excluir")){
                return telefone;
            }
        }
        
        String tipoTelefone = request.getParameter("tipoTelefone");
        String numeroTelefone = request.getParameter("numeroTelefone");
        String ddi = request.getParameter("ddi");
        
        if(Contem.contemDado(tipoTelefone)){
            tpTelefone.setDescricao(tipoTelefone);
        }
        if(Contem.contemDado(numeroTelefone)){
            if(numeroTelefone.length() > 3){
                telefone.setDdd(numeroTelefone.replaceAll("[-./() ]","").substring(0, 2));
            }
        }
        if(Contem.contemDado(ddi)){
            telefone.setDdi(request.getParameter("ddi").replaceAll("[-./()+ ]",""));
        }

        telefone.setTpTelefone(tpTelefone);
        telefone.setFromFornecedor(true);
        
        if(Contem.contemDado(numeroTelefone)){
            numeroTelefone = numeroTelefone.replaceAll("[-./() ]","");
            if(numeroTelefone.length() == 11){
                numeroTelefone = numeroTelefone.substring(2, 11);
            }else if (numeroTelefone.length() == 10){
                numeroTelefone = numeroTelefone.substring(2, 10);
            }
            telefone.setNumero(numeroTelefone);
        }
        
        return telefone;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
