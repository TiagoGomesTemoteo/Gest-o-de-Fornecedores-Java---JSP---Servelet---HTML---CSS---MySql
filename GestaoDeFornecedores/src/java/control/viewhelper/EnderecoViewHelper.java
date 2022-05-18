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
import model.dominio.UF;
import util.Contem;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class EnderecoViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        Endereco endereco = new Endereco();
        
        String operacao = request.getParameter("operacao");
        
        TipoLogradouro tpLogradouro = new TipoLogradouro();
        Pais pais =                   new Pais();
        UF uf =                       new UF(); 
        Cidade cidade =               new Cidade();
        TipoEndereco tpEndereco =     new TipoEndereco();
        
        String estado = request.getParameter("uf");
        String tipoLogradouro = request.getParameter("tipoLogradouro");
        String nomePais = request.getParameter("pais");
        String nomeCidade = request.getParameter("cidade");
        String tipoEndereco = request.getParameter("tipoEndereco");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String complemento = request.getParameter("complemento");
        
        
        if(operacao.equals("Alterar") || operacao.equals("Excluir")){
            String hiddenIdEndereco = request.getParameter("hiddenIdEndereco");
            
            if(Contem.contemDado(hiddenIdEndereco)){
                endereco.setId(Integer.parseInt(hiddenIdEndereco));
            }
            if(operacao.equals("Excluir")){
                return endereco;
            }
        }
        
        if(Contem.contemDado(tipoLogradouro)){
            tpLogradouro.setDescricao(request.getParameter("tipoLogradouro"));
        }
        if(Contem.contemDado(nomePais)){
            pais.setDescricao(nomePais);
        }
        if(Contem.contemDado(estado)){
            uf = new UF(estado,estado.substring(estado.length() - 3), pais); 
            
            if(Contem.contemDado(nomeCidade)){
                cidade = new Cidade(nomeCidade, uf);
            }
        }
        if(Contem.contemDado(tipoEndereco)){
            tpEndereco.setDescricao(tipoEndereco);
        }
        
        endereco.setTpLogradouro(tpLogradouro);
        
        if(Contem.contemDado(logradouro)){
            endereco.setLogradouro(logradouro);
        }
        if(Contem.contemDado(numero)){
            endereco.setNumero(numero);
        }
        if(Contem.contemDado(cep)){
            endereco.setCep(cep.replaceAll("[-./() ]",""));
        }
        if(Contem.contemDado(bairro)){
            endereco.setBairro(bairro);
        }        
        if(Contem.contemDado(complemento)){
            endereco.setComplemento(complemento);
        }
                
        endereco.setCidade(cidade);
        endereco.setTpEndereco(tpEndereco);
        
        return endereco;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
