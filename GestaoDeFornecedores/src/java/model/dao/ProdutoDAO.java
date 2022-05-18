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
public class ProdutoDAO extends AbstractDAO{
    
    public ProdutoDAO (){
        this.conn = null;
    }
    
    public ProdutoDAO (Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;
        int id = 0;
        
        String sqlProduto = "INSERT INTO PRODUTOS(pdt_id, pdt_nome, pdt_descricao)\n" +
                     "VALUES (pdt_id, ?, ?);";
         
        String sqlFornecedorProduto = "INSERT INTO FORNECEDORES_PRODUTOS(fnp_id, fnp_fnc_id, fnp_pdt_id)\n" +
        "VALUES(fnp_id, ?,?);";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            if(this.conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }
            
            this.conn.setAutoCommit(false);

            stmt = this.conn.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1,produto.getNome());
            stmt.setString(2,produto.getDescricao());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            
            produto.setId(id);
            
            stmt = this.conn.prepareStatement(sqlFornecedorProduto);
            stmt.setInt(1,produto.getFornecedor().getId());
            stmt.setInt(2,id);
            stmt.executeUpdate();

            if(ctrlTransacao)this.conn.commit();

        }catch(Exception ex){
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            System.out.println("Não foi possível salvar o Produto no banco de dados \nErro:" + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
    }
    
    
    @Override
    public void alterar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;
        int id = 0;
   
        String sqlProduto = "UPDATE PRODUTOS SET pdt_nome=?, pdt_descricao=? WHERE pdt_id = ?;";
         
        PreparedStatement stmt = null;
   
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }

            conn.setAutoCommit(false);
            
            //Alterando na Tabela Produto
            stmt = conn.prepareStatement(sqlProduto);
            stmt.setString(1,produto.getNome());
            stmt.setString(2,produto.getDescricao());
            stmt.setInt(3, produto.getId());

            stmt.executeUpdate();
            
            if(ctrlTransacao) conn.commit();
            
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível alterar o Produto no banco de dados \nErro:" + ex.getMessage());
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }  
    }


    @Override
    public void excluir(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;
        String sqlFornecedorProduto = "DELETE FROM FORNECEDORES_PRODUTOS WHERE fnp_pdt_id = ?;";
        String sqlProduto = "DELETE FROM PRODUTOS WHERE pdt_id = ?;";
        
        PreparedStatement stmt = null;
        
        try{
            if(conn == null || this.conn.isClosed()){
                this.conn = Conectar.getConnection();
                ctrlTransacao = true; 
            }else{
                ctrlTransacao = false;
            }

            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(sqlFornecedorProduto);
            stmt.setInt(1,produto.getId());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(sqlProduto);
            stmt.setInt(1,produto.getId());
            stmt.executeUpdate();

            if(ctrlTransacao) conn.commit();

        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
            
            System.out.println("Não foi possível excluir o produto do banco de dados");
        
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt);
        }
    }

    @Override
    public List consultar(EntidadeDominio entidade) {
        Produto produto = new Produto();
        
        String sql = "SELECT * FROM FORNECEDORES_PRODUTOS WHERE fnp_fnc_id = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList();
        
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
                produto = (Produto)consultar(rs.getInt("fnp_pdt_id"));
                produtos.add(produto);
            }
            
            return produtos;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o produto no banco de dados \nErro: " + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
    
        return null;
    }

    @Override
    public EntidadeDominio consultar(int id) {
        Produto produto = new Produto();
        
        String sql = "SELECT * FROM PRODUTOS WHERE pdt_id = ?";
        
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
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
        
            while(rs.next()){ 
                produto.setId(rs.getInt("pdt_id"));
                produto.setNome(rs.getString("pdt_nome"));
                produto.setDescricao(rs.getString("pdt_descricao"));
            }
            
            return produto;
            
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o produto no banco de dados \nErro: " + ex.getMessage());
        }finally{
            if(ctrlTransacao) Conectar.closeConnection(conn, stmt, rs);
        }
        
        return null;
    }
    
}
