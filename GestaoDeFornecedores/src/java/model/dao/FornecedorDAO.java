/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import util.Conectar;
import model.dominio.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class FornecedorDAO extends AbstractDAO{

    EnderecoDAO enderecoDAO;
    ProdutoDAO produtoDAO;
    ServicoDAO servicoDAO;
    CnaeDAO cnaeDAO;
    ContatoDAO contatoDAO; 
    TelefoneDAO telefoneDAO;
    
    @Override
    public void salvar(EntidadeDominio entidade) {  
        Fornecedor fornecedor = (Fornecedor) entidade;
        int id = 0;
        
        String sql = "INSERT INTO FORNECEDORES(fnc_id, fnc_email, fnc_cnpj, fnc_inscEstadual, fnc_inscMunicipal, "
                +"\n fnc_dataCad, fnc_rzSocial, fnc_nmFantasia, fnc_end_id, fnc_tipoFornecimento, fnc_estatus)"
                + "\n VALUES(fnc_id, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?);";
        
        PreparedStatement stmt = null;  
        ResultSet rs = null;
        try {
            this.conn = Conectar.getConnection();
            conn.setAutoCommit(false);
            
            enderecoDAO = new EnderecoDAO(conn);
            enderecoDAO.salvar(fornecedor.getEndereco());

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fornecedor.getEmail());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getInscricaoEstadual());
            stmt.setString(4, fornecedor.getInscricaoMunicipal());
            stmt.setString(5, fornecedor.getRzSocial());
            stmt.setString(6, fornecedor.getNmFantasia());
            stmt.setInt   (7, fornecedor.getEndereco().getId());
            stmt.setString(8, fornecedor.getTpFornecimento());
            stmt.setString(9, fornecedor.getStatus());

            stmt.executeUpdate();      

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            fornecedor.setId(id);
            
            //Salvando CNAE     
            cnaeDAO = new CnaeDAO(conn);
            for (CNAE cnae : fornecedor.getListaCNAE()){
                cnae.getFornecedor().setId(id);
                cnaeDAO.salvar(cnae);
            }

            //Salvando Contatos
            contatoDAO = new ContatoDAO(conn);
            for (Contato contato : fornecedor.getListaContatos()){
                contato.getFornecedor().setId(id);
                contatoDAO.salvar(contato);
            }

            //Salvando Telefones
            telefoneDAO = new TelefoneDAO(conn);
            for (Telefone telefone : fornecedor.getListaTelefones()){
                telefone.getFornecedor().setId(id);
                telefoneDAO.salvar(telefone);
            }

            //Salvando Produtos
            produtoDAO = new ProdutoDAO(conn);
            for (Produto produto : fornecedor.getProdutosOfertados()){
                produto.getFornecedor().setId(id);
                produtoDAO.salvar(produto);
            }
            
            //Salvando Serviços
            servicoDAO = new ServicoDAO(conn);
            for (Servico servico : fornecedor.getServicosOfertados()){
                servico.getFornecedor().setId(id);
                servicoDAO.salvar(servico);
            }
            
            conn.commit();

        } catch (Exception ex) {
            try {
                conn.rollback();
                } catch (SQLException e1) {
                        System.out.println("Error: " + e1.getMessage());
                }
            System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
            
        } finally {
            Conectar.closeConnection(conn, stmt, rs);
        }   
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Fornecedor fornecedor = (Fornecedor) entidade;    

        String sql = "UPDATE FORNECEDORES SET fnc_email=?, fnc_cnpj=?, fnc_inscEstadual=?, fnc_inscMunicipal=?, "
                +"\n fnc_rzSocial=?, fnc_nmFantasia=?, fnc_end_id=?, fnc_tipoFornecimento=?, fnc_estatus=?"
                + "WHERE fnc_id=?;";
        
        PreparedStatement stmt = null;  

        try {
            
            this.conn = Conectar.getConnection();
            conn.setAutoCommit(false);
            
            enderecoDAO = new EnderecoDAO(conn);
            enderecoDAO.alterar(fornecedor.getEndereco());
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, fornecedor.getEmail());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getInscricaoEstadual());
            stmt.setString(4, fornecedor.getInscricaoMunicipal());
            stmt.setString(5, fornecedor.getRzSocial());
            stmt.setString(6, fornecedor.getNmFantasia());
            stmt.setInt   (7, fornecedor.getEndereco().getId());
            stmt.setString(8, fornecedor.getTpFornecimento());
            stmt.setString(9, fornecedor.getStatus());
            stmt.setInt(10, fornecedor.getId());

            stmt.executeUpdate();
            
            int id = fornecedor.getId();
            
            //Alterando CNAE 
            cnaeDAO = new CnaeDAO(conn);
            for (CNAE cnae : fornecedor.getListaCNAE()){
                cnae.getFornecedor().setId(id);
                cnaeDAO.alterar(cnae);
            }
            
            //Alterando Contatos
            contatoDAO = new ContatoDAO(conn);
            for (Contato contato : fornecedor.getListaContatos()){
                contato.getFornecedor().setId(id);
                contatoDAO.alterar(contato);
            }
            
            //Alterando Telefones
            telefoneDAO = new TelefoneDAO(conn);
            for (Telefone telefone : fornecedor.getListaTelefones()){
                telefone.getFornecedor().setId(id);
                telefoneDAO.alterar(telefone);
            }
            
            //Alterando Produtos
            produtoDAO = new ProdutoDAO(conn);
            for (Produto produto : fornecedor.getProdutosOfertados()){
                produto.getFornecedor().setId(id);
                produtoDAO.alterar(produto);
            }
            
            //Alterando Serviços
            servicoDAO = new ServicoDAO(conn);
            for (Servico servico : fornecedor.getServicosOfertados()){
                servico.getFornecedor().setId(id);
                servicoDAO.alterar(servico);
            }

            conn.commit();
             
        }catch (Exception ex) {
            try {
                conn.rollback();
                } catch (SQLException e1) {
                        System.out.println("Error: " + e1.getMessage());
                }
            
            System.out.println("Não foi possível alterar os dados no banco de dados.\nErro: " + ex.getMessage());
        
        } finally {
            Conectar.closeConnection(conn, stmt);
        }
    }    

    @Override
    public void excluir(EntidadeDominio entidade) {
        Fornecedor fornecedor = (Fornecedor)entidade;
        
        String sql = "DELETE FROM FORNECEDORES WHERE fnc_id = ?;";
        String sqlFornecedorProduto = "DELETE FROM FORNECEDORES_PRODUTOS WHERE fnp_fnc_id = ?;";
        String sqlFornecedorServico = "DELETE FROM FORNECEDORES_SERVICOS WHERE fns_fnc_id = ?;";
        
        PreparedStatement stmt = null;
            
        try{
            this.conn = Conectar.getConnection();
            conn.setAutoCommit(false);

            //Excluindo CNAEs
            cnaeDAO = new CnaeDAO(conn);
            for(CNAE cnae : fornecedor.getListaCNAE()){
                cnaeDAO.excluir(cnae);   
            }
            
            //Excluindo Contatos   
            contatoDAO = new ContatoDAO(conn);
            for(Contato contato : fornecedor.getListaContatos()){
                contatoDAO.excluir(contato);
            }
            
            //Excluindo Telefones
            telefoneDAO = new TelefoneDAO(conn);
            for(Telefone telefone : fornecedor.getListaTelefones()){
                telefoneDAO.excluir(telefone);
            }

            stmt = conn.prepareStatement(sqlFornecedorProduto);
            stmt.setInt(1, fornecedor.getId());
            stmt.executeUpdate();

            stmt = conn.prepareStatement(sqlFornecedorServico);
            stmt.setInt(1,fornecedor.getId());
            stmt.executeUpdate();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,fornecedor.getId());
            stmt.executeUpdate();
            
            enderecoDAO = new EnderecoDAO(conn);
            enderecoDAO.excluir(fornecedor.getEndereco());

            conn.commit();
            
        }catch (Exception ex) {
            try {
                conn.rollback();
                } catch (SQLException e1) {
                        System.out.println("Error: " + e1.getMessage());
                }
            
            System.out.println("Não foi possível excluir o fornecedor do banco de dados" + ex.getMessage());
        
        }finally{
            Conectar.closeConnection(conn, stmt);
            
        }
    }
        
    

    @Override
    public List consultar(EntidadeDominio entidadeDominio) {
        Fornecedor fornecedor = new Fornecedor();
        this.conn = Conectar.getConnection();
        String sql = "SELECT * FROM FORNECEDORES;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Fornecedor> fornecedores = new ArrayList();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            cnaeDAO = new CnaeDAO(conn);
            contatoDAO = new ContatoDAO(conn);
            telefoneDAO = new TelefoneDAO(conn);
            produtoDAO = new ProdutoDAO(conn);
            servicoDAO = new ServicoDAO(conn);
            enderecoDAO = new EnderecoDAO(conn);
            
            while(rs.next()){
                fornecedor = new Fornecedor();        
                fornecedor.setId(rs.getInt("fnc_id"));
                fornecedor.setEmail(rs.getString("fnc_email"));
                fornecedor.setCnpj(rs.getString("fnc_cnpj"));
                fornecedor.setInscricaoMunicipal(rs.getString("fnc_inscMunicipal"));
                fornecedor.setInscricaoEstadual(rs.getString("fnc_inscEstadual"));
                fornecedor.setDtCadastro(rs.getDate("fnc_dataCad"));
                fornecedor.setRzSocial(rs.getString("fnc_rzSocial"));
                fornecedor.setNmFantasia(rs.getString("fnc_nmFantasia"));
                fornecedor.setTpFornecimento(rs.getString("fnc_tipoFornecimento"));
                fornecedor.setStatus(rs.getString("fnc_estatus"));
                fornecedor.setListaCNAE(cnaeDAO.consultar(fornecedor));
                fornecedor.setListaContatos(contatoDAO.consultar(fornecedor));
                fornecedor.setListaTelefones(telefoneDAO.consultar(fornecedor));
                fornecedor.setProdutosOfertados(produtoDAO.consultar(fornecedor));
                fornecedor.setServicosOfertados(servicoDAO.consultar(fornecedor));
                fornecedor.setEndereco((Endereco)enderecoDAO.consultar(rs.getInt("fnc_end_id")));
                
                fornecedores.add(fornecedor);
            }
            
            return fornecedores;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar fornecedor no banco de dados \nErro:" + ex.getMessage());
        }finally{
            Conectar.closeConnection(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public EntidadeDominio consultar(int id) {
        Fornecedor fornecedor = new Fornecedor();

        this.conn = Conectar.getConnection();
        String sql = "SELECT * FROM FORNECEDORES WHERE fnc_id = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            cnaeDAO = new CnaeDAO(conn);
            contatoDAO = new ContatoDAO(conn);
            telefoneDAO = new TelefoneDAO(conn);
            produtoDAO = new ProdutoDAO(conn);
            servicoDAO = new ServicoDAO(conn);
            enderecoDAO = new EnderecoDAO(conn);
            
            while(rs.next()){
             
                fornecedor.setId(rs.getInt("fnc_id"));
                fornecedor.setEmail(rs.getString("fnc_email"));
                fornecedor.setCnpj(rs.getString("fnc_cnpj"));
                fornecedor.setInscricaoMunicipal(rs.getString("fnc_inscMunicipal"));
                fornecedor.setInscricaoEstadual(rs.getString("fnc_inscEstadual"));
                fornecedor.setDtCadastro(rs.getDate("fnc_dataCad"));
                fornecedor.setRzSocial(rs.getString("fnc_rzSocial"));
                fornecedor.setNmFantasia(rs.getString("fnc_nmFantasia"));
                fornecedor.setTpFornecimento(rs.getString("fnc_tipoFornecimento"));
                fornecedor.setStatus(rs.getString("fnc_estatus"));
                fornecedor.setListaCNAE(cnaeDAO.consultar(fornecedor));
                fornecedor.setListaContatos(contatoDAO.consultar(fornecedor));
                fornecedor.setListaTelefones(telefoneDAO.consultar(fornecedor));
                fornecedor.setProdutosOfertados(produtoDAO.consultar(fornecedor));
                fornecedor.setServicosOfertados(servicoDAO.consultar(fornecedor));
                fornecedor.setEndereco((Endereco)enderecoDAO.consultar(rs.getInt("fnc_end_id")));
               
            }
            
            return fornecedor;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar fornecedor no banco de dados \nErro:" + ex.getMessage());
        }finally{
            Conectar.closeConnection(conn, stmt, rs);
        }
        return null;
    }

    public Fornecedor consultar(Fornecedor fornecedor) {
        Endereco endereco =     new Endereco();
        
        this.conn = Conectar.getConnection();
        String sql = "SELECT * FROM FORNECEDORES WHERE fnc_cnpj = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fornecedor.getCnpj());
            rs = stmt.executeQuery();
            fornecedor = new Fornecedor();
            
            
            while(rs.next()){
                       
                fornecedor.setId(rs.getInt("fnc_id"));               
                fornecedor.setEmail(rs.getString("fnc_email"));               
                fornecedor.setCnpj(rs.getString("fnc_cnpj"));               
                fornecedor.setInscricaoMunicipal(rs.getString("fnc_inscMunicipal")); 
                fornecedor.setInscricaoEstadual(rs.getString("fnc_inscEstadual"));         
                fornecedor.setDtCadastro(rs.getDate("fnc_dataCad"));
                fornecedor.setRzSocial(rs.getString("fnc_rzSocial"));
                fornecedor.setNmFantasia(rs.getString("fnc_nmFantasia"));
                endereco.setId(rs.getInt("fnc_end_id"));
                fornecedor.setEndereco(endereco);
                fornecedor.setTpFornecimento(rs.getString("fnc_tipoFornecimento"));
                fornecedor.setStatus(rs.getString("fnc_estatus"));
                
            }
            
            return fornecedor;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar fornecedor no banco de dados \nErro:" + ex.getMessage());
        }finally{
            Conectar.closeConnection(conn, stmt, rs);
        }
        return null;
    }
}
