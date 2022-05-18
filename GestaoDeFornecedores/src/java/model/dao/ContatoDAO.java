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
public class ContatoDAO extends AbstractDAO{
    TelefoneDAO telefoneDAO;
    
    public ContatoDAO (){
        this.conn =  null;
    }
    
    public ContatoDAO (Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Contato contato = (Contato) entidade;
        int id = 0;

        String sql = "INSERT INTO CONTATOS (ctt_id, ctt_nome, ctt_email, ctt_departamento, ctt_tel_id, ctt_fnc_id)\n" +
                     "VALUES (ctt_id, ?, ?, ?, ?, ?);";
         
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
            
            telefoneDAO = new TelefoneDAO(conn);
            telefoneDAO.salvar(contato.getTelefone());
            
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getDepartamento().getDescricao());
            stmt.setInt(4, contato.getTelefone().getId());
            stmt.setInt(5, contato.getFornecedor().getId());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            
            contato.setId(id);

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível salvar o Contato no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }   
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Contato contato = (Contato) entidade;
        
        String sql = "UPDATE CONTATOS SET ctt_nome = ?, ctt_email = ?, ctt_departamento = ?, ctt_tel_id = ?, ctt_fnc_id = ? "
                + "WHERE ctt_id = ?";
         
        PreparedStatement stmt = null;
  
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getDepartamento().getDescricao());
            stmt.setInt(4, contato.getTelefone().getId());
            stmt.setInt(5, contato.getFornecedor().getId());
            stmt.setInt(6, contato.getId());

            stmt.executeUpdate();
            
            telefoneDAO = new TelefoneDAO(conn);
            telefoneDAO.alterar(contato.getTelefone());
            
            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível alterar o contato no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    } 

    @Override
    public void excluir(EntidadeDominio entidade) {
        Contato contato = (Contato) entidade;
        
        String sql = "DELETE FROM CONTATOS WHERE ctt_id = ?;";
        
        PreparedStatement stmt = null;
        
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contato.getId());    
            stmt.executeUpdate();
            
            telefoneDAO = new TelefoneDAO(conn);
            telefoneDAO.excluir(contato.getTelefone());
            
            if(ctrlTransacao) conn.commit();
            
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível excluir o contato do banco de dados" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }    
    }

    @Override
    public List consultar(EntidadeDominio entidade) {        
        Contato contato = new Contato();
        Departamento departamento = new Departamento();
        Telefone telefone = new Telefone();
        
        String sql = "SELECT * FROM CONTATOS WHERE ctt_fnc_id = ?;";
         
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Contato> contatos = new ArrayList();

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
            
            telefoneDAO = new TelefoneDAO(conn);
            
            while(rs.next()){

                contato.setId(rs.getInt("ctt_id"));
                contato.setNome(rs.getString("ctt_nome"));
                contato.setEmail(rs.getString("ctt_email"));
                departamento.setDescricao(rs.getString("ctt_departamento"));
                contato.setDepartamento(departamento);
                telefone.setId(rs.getInt("ctt_tel_id"));
                
                telefone = (Telefone)telefoneDAO.consultar(telefone.getId());
                
                contato.setTelefone(telefone);

                contatos.add(contato);
            } 

            return contatos;

        }catch(SQLException ex){
            System.out.println("Não foi possível pesquisar o contato no banco de dados \nErro: " + ex.getMessage());   
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }       
        return null; 
    }

    @Override
    public EntidadeDominio consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
