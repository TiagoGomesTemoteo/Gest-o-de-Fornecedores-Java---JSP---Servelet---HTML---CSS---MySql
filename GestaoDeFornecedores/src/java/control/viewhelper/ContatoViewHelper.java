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
public class ContatoViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        String operacao = request.getParameter("operacao");
        
        Contato contato =   new Contato();
        Telefone telefone = new Telefone();
        TipoTelefone tpTelefone =  new TipoTelefone();
        Departamento departamento = new Departamento();
        
        String tpTelContato = request.getParameter("tipoTelefoneContato"); 
        String departContato = request.getParameter("departamentoContato"); 
        String numTelContato = request.getParameter("numeroTelefoneContato");
        String ddiContato = request.getParameter("ddiContato");
        String nomeContato = request.getParameter("nomeContato"); 
        String emailContato = request.getParameter("emailContato");
        
        if(operacao.equals("Alterar") || operacao.equals("Excluir")){
            String hiddenIdContato = request.getParameter("hiddenIdContato");
            String hiddenIdTelContato = request.getParameter("hiddenIdTelContato");
            
            if(Contem.contemDado(hiddenIdContato)){
                contato.setId(Integer.parseInt(hiddenIdContato));
            }   
            if(Contem.contemDado(hiddenIdTelContato)){
                telefone.setId(Integer.parseInt(hiddenIdTelContato));
            }    
            if(operacao.equals("Excluir")){
                contato.setTelefone(telefone);
                return contato;
            }
        } 
        
        if(Contem.contemDado(tpTelContato)){
            tpTelefone.setDescricao(tpTelContato);
        }
        if(Contem.contemDado(departContato)){
            departamento.setDescricao(departContato.replaceAll("[-./()]",""));
        }
        
        if(Contem.contemDado(numTelContato)){
            if(numTelContato.length() > 3){
                telefone.setDdd(numTelContato.replaceAll("[-./() ]","").substring(0, 2));
            }
        }

        if(Contem.contemDado(ddiContato)){
            telefone.setDdi(request.getParameter("ddiContato").replaceAll("[-./()+ ]",""));
        }
        
        telefone.setTpTelefone(tpTelefone);
        telefone.setFromFornecedor(false);
        
        if(Contem.contemDado(numTelContato)){
            numTelContato = numTelContato.replaceAll("[-./() ]","");
            if(Contem.contemDado(numTelContato)){
                if(numTelContato.length() == 11){
                    numTelContato = numTelContato.substring(2, 11);
                }else if (numTelContato.length() == 10){
                    numTelContato = numTelContato.substring(2, 10);
                }

                telefone.setNumero(numTelContato);
            }
        }
        
        if(Contem.contemDado(nomeContato)){
            contato.setNome(nomeContato);
        }
        if(Contem.contemDado(emailContato)){
            contato.setEmail(emailContato.replaceAll(" ",""));
        }

        contato.setDepartamento(departamento);
        contato.setTelefone(telefone);   
        
        return contato;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
