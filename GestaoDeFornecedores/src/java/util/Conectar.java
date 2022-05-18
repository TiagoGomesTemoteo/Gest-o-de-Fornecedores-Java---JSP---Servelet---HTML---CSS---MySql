package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tiago
 */
public class Conectar {
  
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/prodam";
    private static final String USER = "root";
    private static final String PASS = "1234";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível acessar a classe de Conexão.\nErro: " + ex.getMessage());
            return null;
        } catch (SQLException ex) {
            System.out.println("Não foi possível acessar o banco de dados.\nErro: " + ex.getMessage());
            return null;
        }
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Não foi possível fechar a conexão.\nErro: " + ex.getMessage());
            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Não foi possível fechar a conexão.\nErro: " + ex.getMessage());
            }
        }
        closeConnection(conn);
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Não foi possível fechar a conexão.\nErro: " + ex.getMessage());
            }
        }
        closeConnection(conn, stmt);
    }
    

}
