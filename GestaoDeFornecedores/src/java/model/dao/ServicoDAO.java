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
public class ServicoDAO extends AbstractDAO{
    
    public ServicoDAO(){
        this.conn =  null;
    }
    
    public ServicoDAO(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Servico servico = (Servico) entidade;
        int id = 0;
        
        String sqlServico = "INSERT INTO SERVICOS(svc_id, svc_descricao)\n" +
                            "VALUES (svc_id, ?);";
         
        String sqlFornecedorServico = "INSERT INTO FORNECEDORES_SERVICOS(fns_id, fns_fnc_id, fns_svc_id)\n" +
                                      "VALUES(fns_id, ?,?);";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sqlServico, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,servico.getDescricao());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);

            servico.setId(id);
            
            stmt = conn.prepareStatement(sqlFornecedorServico);
            stmt.setInt(1,servico.getFornecedor().getId());
            stmt.setInt(2,id);
            stmt.executeUpdate();

            if(ctrlTransacao)conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível salvar o Servico no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Servico servico = (Servico) entidade;
        
        String sqlServico = "UPDATE SERVICOS SET svc_descricao=? WHERE svc_id=?;";
         
        PreparedStatement stmt = null;
    
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }

            conn.setAutoCommit(false);
            
            //Alterando na Tabela Serviço
            stmt = conn.prepareStatement(sqlServico);
            stmt.setString(1,servico.getDescricao());
            stmt.setInt(2, servico.getId());

            stmt.executeUpdate();

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }

            System.out.println("Não foi possível alterar o Servico no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
           if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    }


    @Override
    public void excluir(EntidadeDominio entidade) {
        Servico servico = (Servico) entidade;
        
        String fornecedorServico = "DELETE FROM FORNECEDORES_SERVICOS WHERE fns_svc_id = ?;";
        String sqlServico = "DELETE FROM SERVICOS WHERE svc_id = ?;";
        
        PreparedStatement stmt = null;
        
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }

            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(fornecedorServico);
            stmt.setInt(1, servico.getId());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(sqlServico);
            stmt.setInt(1, servico.getId());
            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível excluir o servico do banco de dados" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    }

    @Override
    public List consultar(EntidadeDominio entidade) {
        Servico servico = new Servico();
        
        String sql = "SELECT * FROM FORNECEDORES_SERVICOS WHERE fns_fnc_id = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Servico> servicos = new ArrayList();
        
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, entidade.getId());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                servico = (Servico)consultar(rs.getInt("fns_svc_id"));
                servicos.add(servico);
            }
            
            return servicos;
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o serviço no banco de dados \nErro" + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }

    @Override
    public EntidadeDominio consultar(int id) {
        Servico servico = new Servico();
        
        String sql = "SELECT * FROM SERVICOS WHERE svc_id = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                servico.setId(rs.getInt("svc_id"));
                servico.setDescricao(rs.getString("svc_descricao"));    
            }
            
            return servico;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o serviço no banco de dados \nErro" + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }
    
}
