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
public class EnderecoDAO extends AbstractDAO{
    
    public EnderecoDAO(){
        this.conn =  null;
    }
    
    public EnderecoDAO(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;
        int id = 0;
       
        String sql = "INSERT INTO ENDERECOS(end_id, end_cep, end_logradouro, end_numero, end_bairro, "
                + "end_complemento, end_cidade, end_estado,  end_pais, end_tipoEndereco, end_TipoLogradouro)" 
                + "\n VALUES (end_id, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
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

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,endereco.getCep());
            stmt.setString(2,endereco.getLogradouro());
            stmt.setString(3,endereco.getNumero());
            stmt.setString(4,endereco.getBairro());
            stmt.setString(5,endereco.getComplemento());
            stmt.setString(6,endereco.getCidade().getDescricao());
            stmt.setString(7,endereco.getCidade().getUf().getDescricao());
            stmt.setString(8,endereco.getCidade().getUf().getPais().getDescricao());
            stmt.setString(9,endereco.getTpEndereco().getDescricao());
            stmt.setString(10,endereco.getTpLogradouro().getDescricao());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            
            endereco.setId(id);

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível salvar o Endereço no banco de dados \nErro:" + ex.getMessage());
            
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }     
    }

    @Override
    public void alterar(EntidadeDominio entidade) { 
        Endereco endereco = (Endereco) entidade;
        
        String sql = "UPDATE ENDERECOS SET end_cep = ?, end_logradouro = ?, end_numero = ?, end_bairro = ?, "
                + "end_complemento = ?, end_cidade = ?, end_estado = ?,  end_pais = ?, end_tipoEndereco = ?, end_TipoLogradouro = ? "
                + " WHERE end_id = ?;";
         
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

            stmt.setString(1,endereco.getCep());
            stmt.setString(2,endereco.getLogradouro());
            stmt.setString(3,endereco.getNumero());
            stmt.setString(4,endereco.getBairro());
            stmt.setString(5,endereco.getComplemento());
            stmt.setString(6,endereco.getCidade().getDescricao());
            stmt.setString(7,endereco.getCidade().getUf().getDescricao());
            stmt.setString(8,endereco.getCidade().getUf().getPais().getDescricao());
            stmt.setString(9,endereco.getTpEndereco().getDescricao());
            stmt.setString(10,endereco.getTpLogradouro().getDescricao());
            stmt.setInt(11,endereco.getId());

            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível alterar o Endereço no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }   
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        String sql = "DELETE FROM ENDERECOS WHERE end_id = ?;";
        
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
            
            stmt.setInt(1, entidade.getId());
            
            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();
            
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível excluir o endereco do banco de dados" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        } 
    }

    @Override
    public List consultar(EntidadeDominio entidade) {
        Cidade cidade =               new Cidade();
        UF estado =                   new UF();
        Pais pais =                   new Pais();
        TipoLogradouro tpLogradouro = new TipoLogradouro();
        TipoEndereco tpEndereco =     new TipoEndereco();
        Endereco endereco =           new Endereco();
        
        List<Endereco> enderecos = new ArrayList();
        
        String sql = "SELECT * FROM ENDERECOS;";
        
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
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                endereco.setId(rs.getInt("end_id"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setNumero(rs.getString("end_numero"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));
                pais.setDescricao(rs.getString("end_pais"));
                estado.setPais(pais);
                estado.setDescricao(rs.getString("end_estado"));
                cidade.setUf(estado);
                cidade.setDescricao(rs.getString("end_cidade"));
                tpLogradouro.setDescricao(rs.getString("end_tipoLogradouro"));
                tpEndereco.setDescricao(rs.getString("end_tipoEndereco"));
                endereco.setCidade(cidade);
                endereco.setTpLogradouro(tpLogradouro);
                endereco.setTpEndereco(tpEndereco);
                
                enderecos.add(endereco);            
            }
            
            return enderecos;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar esse endereço no banco de dados \nErro: " + ex.getMessage());
            
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }

    @Override
    public EntidadeDominio consultar(int id) {  
        
        Cidade cidade =               new Cidade();
        UF estado =                   new UF();
        Pais pais =                   new Pais();
        TipoLogradouro tpLogradouro = new TipoLogradouro();
        TipoEndereco tpEndereco =     new TipoEndereco();
        Endereco endereco =           new Endereco();
     
        String sql = "SELECT * FROM ENDERECOS WHERE end_id = ?;";
        
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
                
                endereco.setId(rs.getInt("end_id"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setNumero(rs.getString("end_numero"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setComplemento(rs.getString("end_complemento"));
                pais.setDescricao(rs.getString("end_pais"));
                estado.setPais(pais);
                estado.setDescricao(rs.getString("end_estado"));
                cidade.setUf(estado);
                cidade.setDescricao(rs.getString("end_cidade"));
                tpLogradouro.setDescricao(rs.getString("end_tipoLogradouro"));
                tpEndereco.setDescricao(rs.getString("end_tipoEndereco"));
                endereco.setCidade(cidade);
                endereco.setTpLogradouro(tpLogradouro);
                endereco.setTpEndereco(tpEndereco);
            }
            
            return endereco;
        
        }catch(SQLException ex){
            
            System.out.println("Não foi possível consultar esse endereço no banco de dados \nErro: " + ex.getMessage());
            
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }
}

    

