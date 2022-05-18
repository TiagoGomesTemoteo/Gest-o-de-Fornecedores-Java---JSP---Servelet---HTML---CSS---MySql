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
public class ProdutoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {     
        
        String operacao = request.getParameter("operacao");
        
        Produto produto = new Produto();
        
        if(operacao.equals("Alterar") || operacao.equals("Excluir")){
            String hiddenIdProduto = request.getParameter("hiddenIdProduto");
            
            if(Contem.contemDado(hiddenIdProduto)){
                produto.setId(Integer.parseInt(hiddenIdProduto));
            }
  
            if(operacao.equals("Excluir")){
                return produto;
            }
            
        }else if (operacao.equals("Salvar_Produto")){
            String idFornecedor = request.getParameter("idFornecedor");
            
            if(Contem.contemDado(idFornecedor)){
                produto.getFornecedor().setId(Integer.parseInt(idFornecedor));
            }
            
        }else if (operacao.equals("Visualizar")){
            Fornecedor fornecedor = new Fornecedor();
            HttpSession session = request.getSession();
            Resultado resultado1 = (Resultado)session.getAttribute("resultado");
            String idFornecedor = request.getParameter("idFornecedor");
            String idProduto = request.getParameter("idProduto");
           
            int id = 0;
            
            if(Contem.contemDado(idFornecedor)){
                id = Integer.parseInt(idFornecedor);
            }

            for(EntidadeDominio ed : resultado1.getEntidades()){
                if(ed.getId() == id){
                    fornecedor = (Fornecedor)ed;
                }
            }
            
            if(Contem.contemDado(idProduto)){
                id = Integer.parseInt(idProduto);
            }
                        
            for(EntidadeDominio ed : fornecedor.getProdutosOfertados()){
                if(ed.getId() == id){
                    produto = (Produto)ed;
                }
            }
            
            return produto;
            
        }else if (operacao.equals("Alterar_Produto") || operacao.equals("Excluir_Produto")){
            String idProduto = request.getParameter("idProduto");
            
            if(Contem.contemDado(idProduto)){
                produto.setId(Integer.parseInt(idProduto));
            }

            if (operacao.equals("Excluir_Produto")){
                return produto; 
            }
        }
        
        String nomeProduto = request.getParameter("nomeProduto");
        String descricaoProduto = request.getParameter("descricaoProduto");
        
        if(Contem.contemDado(nomeProduto)){
            produto.setNome(nomeProduto);
        }
        if(Contem.contemDado(descricaoProduto)){
            produto.setDescricao(descricaoProduto);
        }
        
        return produto;
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
  
            if(operacao.equals("Salvar_Produto") || operacao.equals("Alterar_Produto") || operacao.equals("Excluir_Produto")){
                Produto produto = (Produto)resultado.getEntidades().get(0);
                List <Produto> listaProdutos = fornecedor.getProdutosOfertados();
                
                if(operacao.equals("Alterar_Produto") || operacao.equals("Excluir_Produto")){
                    EntidadeDominio ed;
                    for(int i = 0; i < listaProdutos.size(); i++){
                        ed = listaProdutos.get(i);
                        if(ed.getId() == produto.getId()){
                            fornecedor.getProdutosOfertados().remove(i);
                            
                            if(operacao.equals("Alterar_Produto")){
                                fornecedor.getProdutosOfertados().add(i, produto);
                            }
                            break;
                        }
                    }
                }
                
                if(operacao.equals("Salvar_Produto")){
                    fornecedor.addProdutos(produto);
                }
                resultado = new Resultado();
                resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
                resultado.getEntidades().add(fornecedor);

                request.getSession().setAttribute("resultado", resultado);
                request.setAttribute("fornecedor",fornecedor);
                rD = request.getRequestDispatcher("cadastrarProduto.jsp");
            }
            
            if(operacao.equals("Visualizar")){
                request.setAttribute("produto",resultado.getEntidades().get(0));
                request.setAttribute("fornecedor",fornecedor);
                rD = request.getRequestDispatcher("cadastrarProduto.jsp");
            }
        } 
        
        rD.forward(request, reponse);
    }
    
}
