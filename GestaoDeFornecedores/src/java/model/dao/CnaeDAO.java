/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import util.Conectar;
import model.dominio.CNAE;
import model.dominio.EntidadeDominio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class CnaeDAO extends AbstractDAO{ 
   
    public CnaeDAO (){
        this.conn =  null;
    }
    
    public CnaeDAO (Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        CNAE cnae = (CNAE) entidade;
        int id = 0;
        
        String sql = "INSERT INTO CNAES(cna_id, cna_numero, cna_fnc_id)\n" +
                     "VALUES (cna_id, ?, ?);";
         
        PreparedStatement stmt = null;
        ResultSet  rs = null;
        
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,cnae.getNumero());
            stmt.setInt(2, cnae.getFornecedor().getId());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            
            cnae.setId(id);
            
            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível salvar o CNAE no banco de dados \nErro:" + ex.getMessage());
            
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }     
    }
    
    @Override
    public void alterar(EntidadeDominio entidade) {
        CNAE cnae = (CNAE) entidade;
        
        String sql = "UPDATE CNAES SET cna_numero = ?, cna_fnc_id = ? WHERE cna_id = ?;";
         
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

            stmt.setString(1,cnae.getNumero());
            stmt.setInt(2, cnae.getFornecedor().getId());
            stmt.setInt(3, cnae.getId());

            stmt.executeUpdate();  

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }

            System.out.println("Não foi possível alterar o CNAE no banco de dados \nErro:" + ex.getMessage());

        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }   
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        CNAE cnae = (CNAE) entidade;
        String sql = "DELETE FROM CNAES WHERE cna_id = ?;";
        
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
            stmt.setInt(1, cnae.getId());
            
            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();
            
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível exclui no banco de dados" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }      
    }

    @Override
    public List consultar(EntidadeDominio entidade) {
        CNAE cnae = new CNAE();
        String sql = "SELECT * FROM CNAES WHERE cna_fnc_id = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CNAE> Cnaes = new ArrayList();
        
        try{   
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,entidade.getId());
            rs = stmt.executeQuery();
            
            while(rs.next()){

                cnae.setId(rs.getInt("cna_id"));
                cnae.setNumero(rs.getString("cna_numero"));
                
                Cnaes.add(cnae);
                
            }
            
            return Cnaes;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o CNAE no banco de dados \nErro: " + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }        
        return null;     
    }
    
    //Caso o CNAE exista retorna true caso contrário retorna false.
    public boolean existeCNAE(CNAE cnae) {
        String sql = "SELECT * FROM CNAES WHERE cna_numero = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            if(conn == null || this.conn.isClosed()) this.conn = Conectar.getConnection();
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cnae.getNumero());
            rs = stmt.executeQuery();
            
            if(rs.next()) return true;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o CNAE no banco de dados \nErro: " + ex.getMessage());
        }finally{
            Conectar.closeConnection(conn, stmt, rs);
        }
        return false;
    }

    @Override
    public EntidadeDominio consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
