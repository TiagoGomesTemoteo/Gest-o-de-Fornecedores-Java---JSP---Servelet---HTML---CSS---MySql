/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dominio.EntidadeDominio;
import model.dominio.Usuario;
import util.Conectar;

/**
 *
 * @author Tiago
 */
public class UsuarioDAO {

    private Connection conn = null;
    
    public boolean validarLogin(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        
        String sql = "SELECT * FROM usuarios WHERE usu_login = ? AND usu_senha = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            
            this.conn = Conectar.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            
            rs = stmt.executeQuery();

            if(rs.next()) return true;
        
        }catch(SQLException ex){
            System.out.println("Não foi possível consultar o usuario no banco de dados \nErro: " + ex.getMessage());
        }finally{
            Conectar.closeConnection(conn, stmt, rs);
        }
        
        return false;
    }
   
}
