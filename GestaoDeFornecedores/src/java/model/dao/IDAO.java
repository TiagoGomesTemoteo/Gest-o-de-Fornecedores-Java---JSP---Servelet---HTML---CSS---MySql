/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import model.dominio.EntidadeDominio;
import java.util.List;
/**
 *
 * @author Tiago
 */
public interface IDAO {
    
    public void salvar (EntidadeDominio entidade);
    public void alterar (EntidadeDominio entidade);
    public void excluir (EntidadeDominio entidade);
    public List consultar(EntidadeDominio entidade);
    public EntidadeDominio consultar(int id);
}
