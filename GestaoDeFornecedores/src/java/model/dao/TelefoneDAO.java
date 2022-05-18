/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import util.Conectar;
import model.dominio.*;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class TelefoneDAO extends AbstractDAO {

    public TelefoneDAO(){
        this.conn =  null;
    }
    
    public TelefoneDAO(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;
        int id = 0;

        String sqlTelContato = "INSERT INTO TELEFONES (tel_id, tel_ddd, tel_numero, tel_ddi, tel_tipoTelefone)\n" +
                     "VALUES (tel_id, ?, ?, ?, ?);";
        
        String sqlTelFornecedor = "INSERT INTO TELEFONES (tel_id, tel_ddd, tel_numero, tel_ddi, tel_tipoTelefone, tel_fnc_id)\n" +
                     "VALUES (tel_id, ?, ?, ?, ?, ?);";
        
        PreparedStatement stmt = null;  
        ResultSet rs = null;
        
        String sql;

        if(telefone.isFromFornecedor()){
            sql = sqlTelFornecedor;
        }else{
            sql = sqlTelContato;
        }

        try {
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setString(3, telefone.getDdi());
            stmt.setString(4, telefone.getTpTelefone().getDescricao());

            if(telefone.isFromFornecedor()){
                stmt.setInt(5, telefone.getFornecedor().getId());
            }

            stmt.executeUpdate();      

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            
            telefone.setId(id);

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível salvar o telefone no banco de dados.\nErro: " + ex.getMessage());
        
        } finally {
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }    
    }
   
    @Override
    public void alterar(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;

        String sql = "UPDATE TELEFONES SET tel_ddd=?, tel_numero=?, tel_ddi=?, tel_tipoTelefone=? WHERE tel_id=?; ";
        
        PreparedStatement stmt = null;  
     
        try {
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setString(3, telefone.getDdi());
            stmt.setString(4, telefone.getTpTelefone().getDescricao());
            stmt.setInt(5, telefone.getId());
            
            stmt.executeUpdate();
            
            
            if(ctrlTransacao) conn.commit();

        } catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            
            ex.printStackTrace();
        
        } finally {
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    }        

    @Override
    public void excluir(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;
        
        String sql = "DELETE FROM TELEFONES WHERE tel_id = ?;";
        
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
            stmt.setInt(1, telefone.getId());
            
            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
    
            System.out.println("Não foi possível excluir os dados no banco de dados" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    }

    @Override
    public List consultar(EntidadeDominio entidade) {
        Telefone telefone = new Telefone();
        TipoTelefone tipoTelefone = new TipoTelefone();
        
        String sql = "SELECT * FROM TELEFONES WHERE tel_fnc_id = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Telefone> telefones = new ArrayList();
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
                
                telefone.setId(rs.getInt("tel_id"));
                telefone.setDdd(rs.getString("tel_ddd"));
                telefone.setDdi(rs.getString("tel_ddi"));
                telefone.setNumero(rs.getString("tel_numero"));
                tipoTelefone.setDescricao(rs.getString("tel_tipoTelefone"));
                telefone.setTpTelefone(tipoTelefone);  
                
                telefones.add(telefone);
            }
            
            return telefones;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o telefone no banco de dados \nErro: " + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }
    
    @Override
    public EntidadeDominio consultar(int id) {
        Telefone telefone = new Telefone();
        TipoTelefone tipoTelefone = new TipoTelefone();
        
        String sql = "SELECT * FROM TELEFONES WHERE tel_id = ?;";
        
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

                telefone.setId(rs.getInt("tel_id"));
                telefone.setDdd(rs.getString("tel_ddd"));
                telefone.setDdi(rs.getString("tel_ddi"));
                telefone.setNumero(rs.getString("tel_numero"));
                tipoTelefone.setDescricao(rs.getString("tel_tipoTelefone"));
                telefone.setTpTelefone(tipoTelefone);      
            }
            
            return telefone;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o telefone no banco de dados \nErro: " + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }
}
