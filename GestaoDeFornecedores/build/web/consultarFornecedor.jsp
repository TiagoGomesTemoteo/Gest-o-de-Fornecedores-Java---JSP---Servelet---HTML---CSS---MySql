<%-- 
    Document   : consultarFornecedor
    Created on : 25 de nov. de 2021, 18:44:54
    Author     : Tiago
--%>
<%@page import="model.dominio.Fornecedor"%>
<%@page import="java.util.List"%>
<%@page import="model.dominio.EntidadeDominio"%>
<%@page import="util.Resultado"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Consultar</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/padraoCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/consultarFornecedor.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <%
            Resultado resultado = (Resultado) session.getAttribute("resultado");
        %>
        
        <div class="barraLateralEsquerda">
            <a href="index.html"><div class="BotoesMenuLateral">Home</div></a>
            <a href="cadastroFornecedor.html"><div class="BotoesMenuLateral"> Cadastrar Fornecedor </div></a>
            <a href="consultarFornecedor.jsp"><div class="BotoesMenuLateral ativo"> Consultar Fornecedor </div></a>
            <a href="login.jsp"><div class="BotoesMenuLateral"> Sair </div></a>
        </div>
        
        <div class="telaAcao">
            <div class="tituloPrincipalConsultar">Consultar fornecedor</div>
            <div class="linha-separadoraFiltro linha-separadoraConsultar"></div>
            <div class="subtitulosConsultarFiltro subtitulosConsultar"> Filtro</div>
            <label class="textoFiltro">Selecione os filtros desejados para realizar a pesquisa.</label>
        
            <form action = "Fornecedor" method ="post" accept-charset=“iso-8859-1,utf-8”>
                
                <div class="checkBoxUnitarioPrimeiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckRazaoSocial" name="ckRazaoSocial" value="ckRazaoSocial">
                    <label class="textoCheckBox">Razão Social</label>
                </div>   
                <div class="checkBoxUnitarioPrimeiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckEmail" name="ckEmail" value="ckEmail">
                    <label class="textoCheckBox">Email</label>
                </div>
                <div class="checkBoxUnitarioPrimeiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckServico" name="ckServico" value="ckServico">
                    <label class="textoCheckBox">Serviço</label>
                </div>
                <div class="checkBoxUnitarioPrimeiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckContato" name="ckContato" value="ckContato">
                    <label class="textoCheckBox">Contato</label>
                </div>
                
                <br>
                
                <div class="checkBoxUnitarioSegundaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckNomeFantasia" name="ckNomeFantasia" value="ckNomeFantasia">
                    <label class="textoCheckBox">Nome Fantasia</label>
                </div>
                <div class="checkBoxUnitarioSegundaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckProduto" name="ckProduto" value="ckProduto">
                    <label class="textoCheckBox">Produto</label>
                </div>
                <div class="checkBoxUnitarioSegundaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckTipoFornecimento" name="ckTipoFornecimento" value="ckTipoFornecimento">
                    <label class="textoCheckBox">Tipo de fornecimento</label>
                </div>  
                <div class="checkBoxUnitarioSegundaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckEndereco" name="ckEndereco" value="ckEndereco">
                    <label class="textoCheckBox">Endereço</label>
                </div>
                
                <br>
                
                <div class="checkBoxUnitarioTerceiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckTelefone" name="ckTelefone" value="ckTelefone">
                    <label class="textoCheckBox">Telefone</label>
                </div> 
                <div class="checkBoxUnitarioTerceiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckID" name="ckID" value="ckID">
                    <label class="textoCheckBox">ID</label>
                </div>
                <div class="checkBoxUnitarioTerceiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckInscricaoMunicipal" name="ckInscricaoMunicipal" value="ckInscricaoMunicipal">
                    <label class="textoCheckBox">Inscrição Municipal</label>
                </div>
                <div class="checkBoxUnitarioTerceiraLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckCNPJ" name="ckCNPJ" value="ckCNPJ">
                    <label class="textoCheckBox">CNPJ</label>
                </div>
                
                <br>
                
                <div class="checkBoxUnitarioQuartaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckCNAE" name="ckCNAE" value="ckCNAE">
                    <label class="textoCheckBox">CNAE</label>
                </div> 
                <div class="checkBoxUnitarioQuartaLinha checkBoxUnitario mesma-linha">
                    <input type="checkbox" id="ckInscricaoEstadual" name="ckInscricaoEstadual" value="ckInscricaoEstadual">
                    <label class="textoCheckBox">Inscrição Estadual</label>
                </div>
                
                <div class="linha-separadoraCamposFiltro linha-separadoraConsultar"></div>
                
                <br><br><br><br><br><br><br><br><br><br><br>
                <div class="camposSelecionadosFiltro">
                    
                </div>
                <input type="submit" value="Consultar" name="operacao" id="botaoBuscar" class="botaoBuscar botaoAdd">
                
                <br><br>
                
                <div class="cabecalhoPesquisa">
                    <div class="cabecalhoPesquisaID textoCabecalho mesma-linha"> ID </div>
                    <div class="cabecalhoPesquisaFornecedor textoCabecalho mesma-linha"> Fornecedor </div>
                    <div class="cabecalhoPesquisaStatus textoCabecalho mesma-linha"> Status </div>
                    <div class="cabecalhoPesquisaCampoPesquisa mesma-linha">
                        <input type="text" class="cabecalhoPesquisaNomeFornecedor" placeholder="Pesquisar pelo nome do fornecedor">
                    </div>
                </div>
                
                <div class="resultadosPesquisa">
                    <div class="divisoria"></div>
                    <%
                        if(resultado != null){
                            List<EntidadeDominio> entidades = resultado.getEntidades();
                            StringBuilder sbRegistro = new StringBuilder();
                            StringBuilder sbLink = new StringBuilder();
                            StringBuilder sbLinkProdutos = new StringBuilder();
                            StringBuilder sbLinkServicos = new StringBuilder();

                            if(entidades != null){
                                for(int i = 0; i < entidades.size(); i++){
                                    Fornecedor fornecedor = (Fornecedor)entidades.get(i);
                                    sbRegistro.setLength(0);
                                    sbLink.setLength(0);
                                    sbLinkProdutos.setLength(0);
                                    sbLinkServicos.setLength(0);
                                
                                    sbLink.append("<a href=Fornecedor?");
                                    sbLink.append("idFornecedor=");
                                    sbLink.append(fornecedor.getId());						
                                    sbLink.append("&");
                                    sbLink.append("operacao=");
                                    sbLink.append("Visualizar");
                                
                                    sbLinkProdutos.append("<a href=Fornecedor?");
                                    sbLinkProdutos.append("idFornecedor=");
                                    sbLinkProdutos.append(fornecedor.getId());						
                                    sbLinkProdutos.append("&");
                                    sbLinkProdutos.append("operacao=");
                                    sbLinkProdutos.append("VisualizarProdutos");    
                                        
                                    sbLinkServicos.append("<a href=Fornecedor?");
                                    sbLinkServicos.append("idFornecedor=");
                                    sbLinkServicos.append(fornecedor.getId());						
                                    sbLinkServicos.append("&");
                                    sbLinkServicos.append("operacao=");
                                    sbLinkServicos.append("VisualizarServicos"); 

                                    sbRegistro.append("<div class='resultado'>"
                                        + sbLink.toString() + ">" 
                                        + "<label class='semBordaDireita cabecalhoPesquisaID textoResultado mesma-linha cursorMao' name='idFornecedor'>" + fornecedor.getId() + "</label>"
                                        + "</a>"
                                        
                                        + sbLink.toString() + ">"
                                        + "<label class='semBordaDireita cabecalhoPesquisaFornecedor textoResultado mesma-linha cursorMao'>" + fornecedor.getNmFantasia() + "</label>"
                                        + "</a>"
                                        
                                        + sbLink.toString() + ">"
                                        + "<label class='semBordaDireita cabecalhoPesquisaStatus textoResultado mesma-linha cursorMao'>" + fornecedor.getStatus() + "</label>"
                                        + "</a>"
                                       
                                        + sbLinkProdutos.toString() + " class='linkAddPrd'><div id='botaoPesquisaAddProduto' class=' botaoPesquisaAddProduto  botaoPesquisaAdd mesma-linha'> + Produto</div></a>"
                                        + sbLinkServicos.toString() + " class='linkAddServ'><div id='botaoPesquisaAddServico' class=' botaoPesquisaAddServico botaoPesquisaAdd mesma-linha'> + Serviço </div></a>"
                                        
                                    + "</div>"
                                    + "<div class='divisoria'></div>");

                                    out.print(sbRegistro.toString());
                                } 

                            }
                        }
                    %>
                </div>
            </form>
        </div>
    </body>
</html>
