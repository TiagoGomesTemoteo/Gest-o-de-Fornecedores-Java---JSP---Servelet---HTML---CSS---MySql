    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dominio;

/**
 *
 * @author Tiago
 */
public class Telefone extends EntidadeDominio{
    private String ddd;
    private String numero;
    private String ddi;
    private TipoTelefone tpTelefone;
    private boolean fromFornecedor;
    private Fornecedor fornecedor;
    
    public Telefone(){
        super(0);
        this.ddd = " ";
        this.numero = " ";
        this.ddi = " ";
        this.tpTelefone = new TipoTelefone();
        this.fornecedor = new Fornecedor();
    }

    public Telefone(String ddd, String numero, String ddi, TipoTelefone tpTelefone, boolean fromFornecedor) {
        super();
        this.ddd = ddd;
        this.numero = numero;
        this.ddi = ddi;
        this.tpTelefone = tpTelefone;
        this.fromFornecedor = fromFornecedor;
        this.fornecedor = new Fornecedor();
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public TipoTelefone getTpTelefone() {
        return tpTelefone;
    }

    public void setTpTelefone(TipoTelefone tpTelefone) {
        this.tpTelefone = tpTelefone;
    }

    public boolean isFromFornecedor() {
        return fromFornecedor;
    }

    public void setFromFornecedor(boolean FromFornecedor) {
        this.fromFornecedor = FromFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
       
}
