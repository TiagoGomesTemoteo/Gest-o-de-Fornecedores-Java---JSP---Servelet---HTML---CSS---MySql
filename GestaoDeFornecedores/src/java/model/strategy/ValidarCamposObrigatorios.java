/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.strategy;

import java.util.List;
import model.dominio.*;

/**
 *
 * @author Tiago
 */
public class ValidarCamposObrigatorios implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidade) {
        String camposVazios = "Preencha os campos obrigatórios abaixo:";
        
        Fornecedor fornecedor = (Fornecedor) entidade;
      
        if(fornecedor.getRzSocial().equals(" ") || fornecedor.getRzSocial().equals("")){
            camposVazios+= "\n Razão Social";
        }
        
        if(fornecedor.getNmFantasia().equals(" ") || fornecedor.getNmFantasia().equals("")){
            camposVazios+= "\n Nome Fantasia";
        }
        
        if(fornecedor.getEmail().equals(" ") || fornecedor.getEmail().equals("")){
            camposVazios+= "\n Email do Fornecedor";
        }
        
        if(fornecedor.getCnpj().equals(" ") || fornecedor.getCnpj().equals("")){
            camposVazios+= "\n CNPJ";
        }
        
        if(fornecedor.getInscricaoMunicipal().equals(" ") || fornecedor.getInscricaoMunicipal().equals("")){
            camposVazios+="\n Inscrição Municipal";
        }
        
        if(fornecedor.getInscricaoEstadual().equals(" ") || fornecedor.getInscricaoEstadual().equals("")){
            camposVazios+="\n Inscrição Estadual";
        }
        
        if(fornecedor.getTpFornecimento().equals(" ") || fornecedor.getTpFornecimento().equals("")){
            camposVazios+="\n Tipo de FSornecimento";
        }
        
        if(fornecedor.getStatus().equals(" ") || fornecedor.getStatus().equals("")
        || fornecedor.getStatus().equals("Selecione...")){
            camposVazios+="\n Status";
        }
        
        List<CNAE> listaCnae = fornecedor.getListaCNAE();
        for(CNAE cnae : listaCnae){
            if(cnae.getNumero().equals(" ") || cnae.getNumero().equals("") ){
                camposVazios+="\n CNAE ";
            }
        }
        
        List<Telefone> listaTelefones = fornecedor.getListaTelefones();
        for(Telefone telefone : listaTelefones){
            
            if(telefone.getDdi().equals(" ") || telefone.getDdi().equals("")){
                camposVazios+="\n DDI do Telefone do fornecedor";
            }
            
            if(telefone.getDdd().equals(" ") || telefone.getDdd().equals("") ){
                camposVazios+="\n DDD do Telefone do fornecedor";
            }
            
            if(telefone.getNumero().equals(" ") || telefone.getNumero().equals("")){
                camposVazios+="\n Número de Telefone do fornecedor";
            }
            
            if(telefone.getTpTelefone().getDescricao().equals(" ") ||
               telefone.getTpTelefone().getDescricao().equals("")  ||
               telefone.getTpTelefone().getDescricao().equals("Selecione...")){
                camposVazios+="\n Tipo de Telefone do fornecedor";
        
            }
        }
        
        if(fornecedor.getEndereco().getTpLogradouro().getDescricao().equals(" ") ||
           fornecedor.getEndereco().getTpLogradouro().getDescricao().equals("")  ||
           fornecedor.getEndereco().getTpLogradouro().getDescricao().equals("Selecione...")){
            camposVazios+="\n Tipo Logradouro";
        }
        
        if(fornecedor.getEndereco().getLogradouro().equals(" ") ||
           fornecedor.getEndereco().getLogradouro().equals("")){
            camposVazios+="\n Logradouro";
        }
        
        if(fornecedor.getEndereco().getNumero().equals(" ") ||
           fornecedor.getEndereco().getNumero().equals("")){
            camposVazios+="\n Número de Endereço";
        }
        
        if(fornecedor.getEndereco().getCep().equals(" ") ||
           fornecedor.getEndereco().getCep().equals("")){
            camposVazios+="\n CEP";
        }
        
        if(fornecedor.getEndereco().getBairro().equals(" ") ||
           fornecedor.getEndereco().getBairro().equals("")){
            camposVazios+="\n Bairro";
        }
        
        if(fornecedor.getEndereco().getCidade().getDescricao().equals(" ") ||
           fornecedor.getEndereco().getCidade().getDescricao().equals("")){
            camposVazios+="\n Cidade";
        }
        
        if(fornecedor.getEndereco().getCidade().getUf().getDescricao().equals(" ") ||
           fornecedor.getEndereco().getCidade().getUf().getDescricao().equals("")){
            camposVazios+="\n Estado";
        }
        
        if(fornecedor.getEndereco().getCidade().getUf().getPais().getDescricao().equals(" ") ||
           fornecedor.getEndereco().getCidade().getUf().getPais().getDescricao().equals("")){
            camposVazios+="\n País";
        }
        
        if(fornecedor.getEndereco().getTpEndereco().getDescricao().equals(" ") ||
           fornecedor.getEndereco().getTpEndereco().getDescricao().equals("")  ||
           fornecedor.getEndereco().getTpEndereco().getDescricao().equals("Selecione...")){
            camposVazios+="\n Tipo de Endereço";
        }
        
        List<Contato> listaContatos = fornecedor.getListaContatos();
        for(Contato contato : listaContatos){
            if(contato.getNome().equals(" ") || contato.getNome().equals("")){
                camposVazios+="\n Nome do contato";
            }
            
            if(contato.getEmail().equals(" ") || contato.getEmail().equals("")){
                camposVazios+="\n Email do contato";
            }
            
            if(contato.getDepartamento().getDescricao().equals(" ") ||
               contato.getDepartamento().getDescricao().equals("")){
                camposVazios+="\n Departamento do contato";
            }
            
            if(contato.getTelefone().getDdi().equals(" ") || 
               contato.getTelefone().getDdi().equals("")){
                camposVazios+="\n DDI do Telefone do contato";
            }
            
            if(contato.getTelefone().getDdd().equals(" ") ||
               contato.getTelefone().getDdd().equals("")){
                camposVazios+="\n DDD do Telefone do contato";
            }
            
            if(contato.getTelefone().getNumero().equals(" ") ||
               contato.getTelefone().getNumero().equals("")){
                camposVazios+="\n Número de Telefone do contato";
            }
            
            if(contato.getTelefone().getTpTelefone().getDescricao().equals(" ") ||
               contato.getTelefone().getTpTelefone().getDescricao().equals("")  ||
               contato.getTelefone().getTpTelefone().getDescricao().equals("Selecione...")){
                camposVazios+="\n Tipo de Telefone do contato";
            }
            
            
        }

        List<Produto> listaProdutos = fornecedor.getProdutosOfertados();
        for(Produto produto : listaProdutos){
            if(produto.getNome().equals(" ") || produto.getNome().equals("")){
                camposVazios+="\n Nome do Produto";
            }
            
            if(produto.getDescricao().equals(" ") || produto.getDescricao().equals("")){
                camposVazios+="\n Descrição do Produto";
            }
        }

        List<Servico> listaServicos = fornecedor.getServicosOfertados();
        for(Servico servico : listaServicos){
            if(servico.getDescricao().equals(" ") || servico.getDescricao().equals("")){
                camposVazios+="\n Descrição do Serviço";
            }
        }
        
        if(camposVazios.equals("Preencha os campos obrigatórios abaixo:")){
            camposVazios = null;
        }
        
        return camposVazios;
    }
    
    
    
}
