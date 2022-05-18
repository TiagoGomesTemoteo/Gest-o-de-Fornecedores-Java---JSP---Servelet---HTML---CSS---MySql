/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.fachada;

import java.util.*;
import model.dominio.*;
import model.dao.*;
import model.strategy.*;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public class Fachada implements IFachada{
    
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Map<String, IDAO> daos;
    private Resultado resultado;
    
    public Fachada(){
        
        rns = new HashMap<String, Map<String, List<IStrategy>>> ();
        daos = new HashMap<String, IDAO>();
        
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO =       new ProdutoDAO();
        ServicoDAO servicoDAO =       new ServicoDAO(); 
        
        daos.put(Fornecedor.class.getName(), fornecedorDAO);
        daos.put(Produto.class.getName(), produtoDAO);
        daos.put(Servico.class.getName(), servicoDAO);        
        
        //Regras de negócio
        GerarLog gerarLog = new GerarLog();
        ValidarCNPJ vCNPJ = new ValidarCNPJ();
        ValidarCamposObrigatorios vCamposObrigatorios = new ValidarCamposObrigatorios();
        ValidarExistenciaCNAE vExistenciaCNAE = new ValidarExistenciaCNAE();
        ValidarQtdMinContatoAssociado vContatoAssociado = new ValidarQtdMinContatoAssociado();
        ValidarQtdMinProduto vQtdMinProduto = new ValidarQtdMinProduto();
        
        //Regras de negócio quando for salvar um fornecedor
        ArrayList<IStrategy> rnsSalvarFornecedor = new ArrayList<IStrategy>();
        rnsSalvarFornecedor.add(gerarLog);
        rnsSalvarFornecedor.add(vCNPJ);
        rnsSalvarFornecedor.add(vCamposObrigatorios);
        rnsSalvarFornecedor.add(vExistenciaCNAE);
        rnsSalvarFornecedor.add(vContatoAssociado);
        rnsSalvarFornecedor.add(vQtdMinProduto);
        
        ArrayList<IStrategy> rnsAlterarFornecedor = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsExcluirFornecedor = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsConsultarFornecedor = new ArrayList<IStrategy>();
   
        //Mapa de operações e regras de negócio do fornecedor
        Map<String, List<IStrategy>> rnsFornecedor = new HashMap<String, List<IStrategy>>();     
        rnsFornecedor.put("SALVAR", rnsSalvarFornecedor);
        rnsFornecedor.put("ALTERAR", rnsAlterarFornecedor);
        rnsFornecedor.put("EXCLUIR", rnsExcluirFornecedor);
        rnsFornecedor.put("CONSULTAR", rnsConsultarFornecedor);
   
        //Produto
        ArrayList<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsExcluirProduto = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsConsultarProduto = new ArrayList<IStrategy>();
        
        Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
        
        rnsProduto.put("SALVAR", rnsSalvarProduto);
        rnsProduto.put("ALTERAR", rnsAlterarProduto);
        rnsProduto.put("EXCLUIR", rnsExcluirProduto);
        rnsProduto.put("CONSULTAR", rnsConsultarProduto);
        
        //Servico
        ArrayList<IStrategy> rnsSalvarServico = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsAlterarServico = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsExcluirServico = new ArrayList<IStrategy>();
        ArrayList<IStrategy> rnsConsultarServico = new ArrayList<IStrategy>();
        
        Map<String, List<IStrategy>> rnsServico = new HashMap<String, List<IStrategy>>();
        
        rnsServico.put("SALVAR", rnsSalvarServico);
        rnsServico.put("ALTERAR", rnsAlterarServico);
        rnsServico.put("EXCLUIR", rnsExcluirServico);
        rnsServico.put("CONSULTAR", rnsConsultarServico);
        
        
        rns.put(Servico.class.getName(), rnsServico);
        rns.put(Produto.class.getName(), rnsProduto);
        rns.put(Fornecedor.class.getName(),rnsFornecedor);
        
        
    }
    
    @Override
    public Resultado salvar(EntidadeDominio entidade) { 
        resultado = new Resultado();
        String nmClass = entidade.getClass().getName();
       
        String msg = aplicarRegras(entidade, "SALVAR");
        
        if(msg == null){
            IDAO dao = daos.get(nmClass);
            try{
                dao.salvar(entidade); 
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            }catch(Exception ex){
                ex.printStackTrace();
                resultado.setMsg("Não foi possível salvar o(a)" + nmClass);
            }   
        }     
        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClass = entidade.getClass().getName();
           
        String msg = aplicarRegras(entidade, "ALTERAR");

        if(msg == null){
            IDAO dao = daos.get(nmClass);
            try{
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                
            }catch(Exception ex){
                ex.printStackTrace();
                resultado.setMsg("Não foi possível alterar o(a)" + nmClass);
            }   
        }     
        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClass = entidade.getClass().getName();
           
        String msg = aplicarRegras(entidade, "EXCLUIR");

        if(msg == null){
            IDAO dao = daos.get(nmClass);
            try{
                dao.excluir(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                
            }catch(Exception ex){
                ex.printStackTrace();
                resultado.setMsg("Não foi possível excluir o(a)" + nmClass);
            }   
        }     
        return resultado;
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClass = entidade.getClass().getName();
           
        String msg = aplicarRegras(entidade, "CONSULTAR");

        if(msg == null){
            IDAO dao = daos.get(nmClass);
            try{
                resultado.setEntidades(dao.consultar(entidade));
                
            }catch(Exception ex){
                ex.printStackTrace();
                resultado.setMsg("Não foi possível consultar o(a)" + nmClass);
            }   
        }     
        return resultado;
    }
    
    @Override
    public Resultado visualizar(EntidadeDominio entidade){
        resultado = new Resultado();
        resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
        resultado.getEntidades().add(entidade);
        return resultado;
    }
    
    @Override
    public Resultado acessar(EntidadeDominio entidade){
        ValidarUsuario vUsuario = new ValidarUsuario();
        resultado = new Resultado();
        resultado.setMsg(vUsuario.processar(entidade));
        return resultado;
    }
    
    private String aplicarRegras(EntidadeDominio entidade, String operacao){
        StringBuilder msg = new StringBuilder();
        
        Map <String, List<IStrategy>> regrasNegocio = rns.get(entidade.getClass().getName());
        
        if(regrasNegocio != null){
            List<IStrategy> regrasOperacao = regrasNegocio.get(operacao);
            if(regrasOperacao != null){
                for(IStrategy regras : regrasOperacao){
                    String mensagem = regras.processar(entidade);
                    if(mensagem != null){
                        msg.append(mensagem);
                        msg.append("\n");
                    }
                }
            }
        }
        if(msg.length() > 0){
            return msg.toString();
        }else{
            return null;
        }
    }
    
}
