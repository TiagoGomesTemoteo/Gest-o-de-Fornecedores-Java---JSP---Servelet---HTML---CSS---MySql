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
import javax.servlet.http.HttpSession;
import model.dominio.*;
import util.Contem;
import util.Resultado;


/**
 *
 * @author Tiago
 */
public class FornecedorViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {   
        IViewHelper viewHelper;
        
        String operacao = request.getParameter("operacao");
        
        Fornecedor fornecedor = new Fornecedor();
        CNAE cnae = new CNAE();

        if(operacao.equals("Salvar") || operacao.equals("Alterar")){

            if(operacao.equals("Alterar")){
                String hiddenIdCnae = request.getParameter("hiddenIdCnae");
                String idFornecedor = request.getParameter("idFornecedor");
                
                if(Contem.contemDado(hiddenIdCnae)){
                    cnae.setId(Integer.parseInt(hiddenIdCnae));
                }
                if(Contem.contemDado(idFornecedor)){
                    fornecedor.setId(Integer.parseInt(idFornecedor));
                }
            }
            
            String campoCnae = request.getParameter("cnae");
            String rzSocial = request.getParameter("razaoSocial");
            String nomeFantasia = request.getParameter("nomeFantasia");
            String email = request.getParameter("email");
            String cnpj = request.getParameter("cnpj");
            String tipoFornecimento = request.getParameter("tipoFornecimento");
            String inscricaoEstadual = request.getParameter("inscricaoEstadual");
            String inscricaoMunicipal = request.getParameter("inscricaoMunicipal");
            String status = request.getParameter("status");
                    
            if(Contem.contemDado(campoCnae)){
                cnae.setNumero(campoCnae.replaceAll("[-./() ]","")); 
            }
            if(Contem.contemDado(rzSocial)){
                fornecedor.setRzSocial(rzSocial);
            }
            if(Contem.contemDado(nomeFantasia)){
               fornecedor.setNmFantasia(nomeFantasia);
            }
            if(Contem.contemDado(email)){
               fornecedor.setEmail(email);
            }
            if(Contem.contemDado(cnpj)){
               fornecedor.setCnpj(cnpj.replaceAll("[-./() ]",""));
            }
            if(Contem.contemDado(tipoFornecimento)){
               fornecedor.setTpFornecimento(tipoFornecimento);
            }
            if(Contem.contemDado(inscricaoEstadual)){
               fornecedor.setInscricaoEstadual(inscricaoEstadual.replaceAll("[-./() ]",""));
            }
            if(Contem.contemDado(inscricaoMunicipal)){
               fornecedor.setInscricaoMunicipal(inscricaoMunicipal.replaceAll("[-./() ]",""));
            }
            if(Contem.contemDado(status)){
               fornecedor.setStatus(status);
            }

            fornecedor.addCNAE(cnae); 

            viewHelper = new TelefoneViewHelper();
            fornecedor.addTelefone((Telefone)viewHelper.getEntidade(request));

            viewHelper = new ContatoViewHelper();
            fornecedor.addContato((Contato)viewHelper.getEntidade(request));

            viewHelper = new ProdutoViewHelper();
            fornecedor.addProdutos((Produto)viewHelper.getEntidade(request)); 

            viewHelper = new ServicoViewHelper();
            fornecedor.addServico((Servico)viewHelper.getEntidade(request));

            viewHelper = new EnderecoViewHelper();
            fornecedor.setEndereco((Endereco)viewHelper.getEntidade(request));
            
        }else if (operacao.equals("Visualizar") || operacao.equals("VisualizarProdutos") || operacao.equals("VisualizarServicos")){
            HttpSession session = request.getSession();
            Resultado resultado = (Resultado)session.getAttribute("resultado");
            String idFornecedor = request.getParameter("idFornecedor");
            int id = 0;
            
            if(Contem.contemDado(idFornecedor)){
                id = Integer.parseInt(idFornecedor);
            }
            
            for(EntidadeDominio ed : resultado.getEntidades()){
                if(ed.getId() == id){
                    fornecedor = (Fornecedor)ed;
                }
            }
            
        }else if (operacao.equals("Excluir")){
  
            String hiddenIdCnae = request.getParameter("hiddenIdCnae");
            String idFornecedor = request.getParameter("idFornecedor");
            
            
            if(Contem.contemDado(hiddenIdCnae)){
                cnae.setId(Integer.parseInt(hiddenIdCnae));
            }
            if(Contem.contemDado(idFornecedor)){
                fornecedor.setId(Integer.parseInt(idFornecedor));
            }
            
            fornecedor.addCNAE(cnae);
            
            viewHelper = new TelefoneViewHelper();
            fornecedor.addTelefone((Telefone)viewHelper.getEntidade(request));

            viewHelper = new ContatoViewHelper();
            fornecedor.addContato((Contato)viewHelper.getEntidade(request));

            viewHelper = new ProdutoViewHelper();
            fornecedor.addProdutos((Produto)viewHelper.getEntidade(request)); 

            viewHelper = new ServicoViewHelper();
            fornecedor.addServico((Servico)viewHelper.getEntidade(request));

            viewHelper = new EnderecoViewHelper();
            fornecedor.setEndereco((Endereco)viewHelper.getEntidade(request));
        }
        
        
        return fornecedor;
        
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse reponse) throws IOException, ServletException {
        
        RequestDispatcher rD = null;
        
        String operacao = request.getParameter("operacao");
        
        if(resultado.getMsg() == null && operacao.equals("Salvar")){
            request.getSession().setAttribute("resultado",resultado);
            rD = request.getRequestDispatcher("consultarFornecedor.jsp");
            
        }else if(resultado.getMsg() == null && (operacao.equals("Visualizar") || operacao.equals("VisualizarProdutos") || operacao.equals("VisualizarServicos"))) {
            request.setAttribute("fornecedor", resultado.getEntidades().get(0));
            
            if(operacao.equals("VisualizarProdutos")){
                rD = request.getRequestDispatcher("cadastrarProduto.jsp");
                
            }else if (operacao.equals("VisualizarServicos")){
                rD = request.getRequestDispatcher("cadastrarServico.jsp"); 
                
            }else{
                rD = request.getRequestDispatcher("alterarFornecedor.jsp");
            }
            
        }else if(resultado.getMsg() == null && operacao.equals("Consultar")) {
            request.getSession().setAttribute("resultado",resultado);
            rD = request.getRequestDispatcher("consultarFornecedor.jsp");
            
        }else if(resultado.getMsg() == null && operacao.equals("Alterar")) {
            request.getSession().setAttribute("resultado",resultado);
            rD = request.getRequestDispatcher("consultarFornecedor.jsp");
            
        }else if(resultado.getMsg() == null && operacao.equals("Excluir")) {
            request.getSession().setAttribute("resultado", null);
            rD = request.getRequestDispatcher("consultarFornecedor.jsp");
        }
        
        rD.forward(request, reponse);
    }
    
}
