/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.fachada;

import java.util.List;
import model.dominio.EntidadeDominio;
import util.Resultado;

/**
 *
 * @author Tiago
 */
public interface IFachada {
    
    public Resultado salvar (EntidadeDominio entidade);
    public Resultado alterar (EntidadeDominio entidade);
    public Resultado excluir (EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
    public Resultado visualizar(EntidadeDominio entidade);
    public Resultado acessar(EntidadeDominio entidade);
}
