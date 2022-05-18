/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dominio.*;
import util.Contem;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class ServicoViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
            
        Servico servico = new Servico();
                
        if(operacao.equals("Alterar") || operacao.equals("Excluir")){
            String hiddenIdServico = request.getParameter("hiddenIdServico");
            
            if(Contem.contemDado(hiddenIdServico)){
                servico.setId(Integer.parseInt(hiddenIdServico));
            }
            
            if(operacao.equals("Excluir")){
                return servico;
            }
        }else if (operacao.equals("Salvar_Servico")){
            String idFornecedor = request.getParameter("idFornecedor");
            
            if(Contem.contemDado(idFornecedor)){
                servico.getFornecedor().setId(Integer.parseInt(idFornecedor));
            }
            
        }else if (operacao.equals("Visualizar")){
            Fornecedor fornecedor = new Fornecedor();
            HttpSession session = request.getSession();
            Resultado resultado1 = (Resultado)session.getAttribute("resultado");
            String idFornecedor = request.getParameter("idFornecedor");
            String idServico = request.getParameter("idServico");
            
            int id = 0;
            
            if(Contem.contemDado(idFornecedor)){
                id = Integer.parseInt(idFornecedor);
            }
            
            for(EntidadeDominio ed : resultado1.getEntidades()){
                if(ed.getId() == id){
                    fornecedor = (Fornecedor)ed;
                }
            }
            
            if(Contem.contemDado(idServico)){
                id = Integer.parseInt(idServico);
            }
                        
            for(EntidadeDominio ed : fornecedor.getServicosOfertados()){
                if(ed.getId() == id){
                    servico = (Servico)ed;
                }
            }
            
            return servico;
            
        }else if (operacao.equals("Alterar_Servico") || operacao.equals("Excluir_Servico")){
            String idServico = request.getParameter("idServico");
            
            if(Contem.contemDado(idServico)){
                servico.setId(Integer.parseInt(idServico));
            }

            if (operacao.equals("Excluir_Servico")){
                return servico; 
            }
        }

        String descricaoServico = request.getParameter("descricaoServico");
        
        if(Contem.contemDado(descricaoServico)){
            servico.setDescricao(descricaoServico);
        }

        return servico;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        
        String operacao = request.getParameter("operacao");
        
        Fornecedor fornecedor = new Fornecedor();
        
        RequestDispatcher rD = null;
        
        if (operacao != null){
            
            HttpSession session = request.getSession();
            Resultado resultado1 = (Resultado)session.getAttribute("resultado");
            String idFornecedor = request.getParameter("idFornecedor");
            
            int id = 0;
            
            if(Contem.contemDado(idFornecedor)){
                id = Integer.parseInt(idFornecedor);
            }
            
            for(EntidadeDominio ed : resultado1.getEntidades()){
                if(ed.getId() == id){
                    fornecedor = (Fornecedor)ed;
                    
                }
            }
  
            if(operacao.equals("Salvar_Servico") || operacao.equals("Alterar_Servico") || operacao.equals("Excluir_Servico")){
                Servico servico = (Servico)resultado.getEntidades().get(0);
                List <Servico> listaServico = fornecedor.getServicosOfertados();
                
                if(operacao.equals("Alterar_Servico") || operacao.equals("Excluir_Servico")){
                    EntidadeDominio ed;
                    for(int i = 0; i < listaServico.size(); i++){
                        ed = listaServico.get(i);
                        if(ed.getId() == servico.getId()){
                            fornecedor.getServicosOfertados().remove(i);
                            
                            if(operacao.equals("Alterar_Servico")){
                                fornecedor.getServicosOfertados().add(i, servico);
                            }
                            break;
                        }
                    }
                }
                
                if(operacao.equals("Salvar_Servico")){
                    fornecedor.addServico(servico);
                }
                resultado = new Resultado();
                resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
                resultado.getEntidades().add(fornecedor);

                request.getSession().setAttribute("resultado", resultado);
                request.setAttribute("fornecedor",fornecedor);
                rD = request.getRequestDispatcher("cadastrarServico.jsp");
            }
            
            if(operacao.equals("Visualizar")){
                request.setAttribute("servico",resultado.getEntidades().get(0));
                request.setAttribute("fornecedor",fornecedor);
                rD = request.getRequestDispatcher("cadastrarServico.jsp");
            }
        } 
        
        
        
        rD.forward(request, reponse);
    }
    
}
