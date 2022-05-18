<%-- 
    Document   : cadastrarProduto
    Created on : 30 de nov. de 2021, 11:22:48
    Author     : Tiago
--%>

<%@page import="java.util.List"%>
<%@page import="model.dominio.EntidadeDominio"%>
<%@page import="model.dominio.Fornecedor"%>
<%@page import="model.dominio.Produto"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar produto</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/padraoCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/cadastroProduto.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            Fornecedor fornecedor = (Fornecedor)request.getAttribute("fornecedor");
            Produto produtoAlt = (Produto)request.getAttribute("produto");
    
        %>
        <div class="barraLateralEsquerda">
            <a href="index.html"><div class="BotoesMenuLateral">Home</div></a>
            <a href="cadastroFornecedor.html"><div class="BotoesMenuLateral ativo"> Cadastrar Fornecedor </div></a>
            <a href="consultarFornecedor.jsp"><div class="BotoesMenuLateral"> Consultar Fornecedor </div></a>
            <a href="login.jsp"><div class="BotoesMenuLateral"> Sair </div></a>
        </div>
        
        <div class="telaAcao">
            
            <div class="tituloPrincipalCadastrarProduto">Cadastrar produto</div>
            <div class="msgObrigatoria">
                    <label> Os campos destacados em vermelho são de preenchimento obrigatório para salvar ou alterar um produto.</label>
            </div>
            <div class="linha-separadoraCadastrarProduto"></div>
            
            <div class="cadastroProdutoCampoID campoPadraoProduto mesma-linha">
                <label> ID </label><br><br>
                <input type="text" class="cadastroProdutoID" id="idFornecedor" readonly name="cadastroProdutoID" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getId() + "'");
                    %>
                ><br>
            </div>  
            
            <div class="cadastroProdutoCampoFornecedor campoPadraoProduto mesma-linha">
                <label>Fornecedor</label><br><br>
                <input type="text" class="cadastroProdutoFornecedor" id="nomeFantasiaFornecedor" readonly name="cadastroFornecedorCampoFornecedor" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getNmFantasia() + "'");
                    %>       
                ><br>
            </div>

            <div class="cadastroProdutoCampoDtRegistro campoPadraoProduto mesma-linha">
                <label>Data de registro</label><br><br>
                <input type="text" class="cadastroProdutoDtRegistro" id="dtCadastroFornecedor" readonly name="cadastroProdutoDtRegistro" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getDtCadastro()+ "'");
                    %>       
                ><br>
            </div>
            
            <div class="cabecalhoPesquisaProduto">
                <div class="cabecalhoPesquisaIDProduto textoCabecalhoProduto mesma-linha"> ID </div>
                <div class="cabecalhoPesquisaFornecedorProduto textoCabecalhoProduto mesma-linha"> Produto </div>
                <div class="cabecalhoPesquisaCampoPesquisaProduto mesma-linha">
                    <input type="text" class="cabecalhoPesquisaNomeProduto" placeholder="Pesquisar pelo nome do produto">
                </div>
            </div>
            <form action="Produto" method="post">  
                <div class="resultadosPesquisaProduto">           
                    <%
                        if(fornecedor.getProdutosOfertados() != null && fornecedor.getProdutosOfertados().size() > 0){
                            List<Produto> produtos = fornecedor.getProdutosOfertados();
                            StringBuilder sbRegistro = new StringBuilder();
                            StringBuilder sbLinkVisualizar = new StringBuilder();
                            StringBuilder sbLinkExcluir = new StringBuilder();
                            StringBuilder sbLink = new StringBuilder();

                            if(produtos != null){
                                for(int i = 0; i < produtos.size(); i++){
                                    Produto produto = produtos.get(i);
                                    sbRegistro.setLength(0);
                                    sbLink.setLength(0);
                                    sbLinkVisualizar.setLength(0);
                                    sbLinkExcluir.setLength(0);
                                    
                                    sbLink.append("<a href=Produto?");
                                            sbLink.append("idProduto=");
                                            sbLink.append(produto.getId());						
                                            sbLink.append("&");
                                            sbLink.append("idFornecedor=");
                                            sbLink.append(fornecedor.getId());						
                                            sbLink.append("&");
                                            sbLink.append("operacao=");
                                            
                                            sbLinkVisualizar.append(sbLink.toString());
                                            sbLinkVisualizar.append("Visualizar"); 
                                            
                                            sbLinkExcluir.append(sbLink.toString());
                                            sbLinkExcluir.append("Excluir_Produto");

                                    sbRegistro.append("<div class='resultadoProduto'>"
                                        + "<label name='idProduto' class='semBordaDireitaProduto cabecalhoPesquisaIDProduto textoResultadoProduto mesma-linha'> "+produto.getId()+" </label>"
                                        + "<label class='semBordaDireitaProduto cabecalhoPesquisaFornecedorProduto textoResultadoProduto mesma-linha'> "+produto.getNome()+" </label>"
                                        
                                        + sbLinkVisualizar.toString() + ">"
                                        + "<div id='botaoPesquisaEditarProdutoProduto' class=' botaoPesquisaEditarProduto  botaoPesquisaAddProduto mesma-linha'> Alterar</div>"
                                        + "</a>"
                                        
                                        + sbLinkExcluir.toString() + ">"
                                        + "<input type='button' value='Excluir' id='botaoPesquisaEditarAddServiço' class=' botaoPesquisaExcluirProduto botaoPesquisaAddProduto  mesma-linha'>"
                                        + "</a>"
                                        
                                        + "</div>"
                                        + "<div class='divisoria'></div>");

                                    out.print(sbRegistro.toString());
                                } 

                            }
                        }
                    %>

                </div>
            </form>
            <br><br><br><br><br><br><br><br>
            
            
            <div class="linha-separadoraCadastroProduto linha-separadoraCadastroProduto"></div>
            
            <form action="Produto" method="post">
                <input type="hidden" name="idFornecedor" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getId() + "'");
                    %>      
                >
                <div class="cadastroProdutoProduto campoPadrao">             
                    <div class="cadastroProduto-campoNomeProduto campoPadrao mesma-linha">
                        <label>Nome</label><br><br>
                        <input type="text" class="cadastroProduto-nomeProduto" id="cadastroProduto-nomeProduto" required="required" name="nomeProduto" value=
                        <%
                            if(produtoAlt != null) out.print("'" + produtoAlt.getNome()+ "'");
                        %>       
                        ><br>
                    </div>
                    <div class="cadastroProduto-campoIdProduto campoPadrao mesma-linha">
                        <label>ID</label><br><br>
                        <input type="text" readonly class="cadastroProduto-idProduto" id="cadastroProduto-nomeProduto" name="idProduto" value=
                        <%
                            if(produtoAlt != null) out.print("'" + produtoAlt.getId()+ "'");
                        %>       
                        ><br>
                    </div>
                    <div class="cadastroProduto-campoDescricaoProduto campoPadrao">
                        <label>Descrição</label><br><br>
                        <input type="text" class="cadastroProduto-descricaoProduto" id="cadastroProduto-descricaoProduto" required="required" name="descricaoProduto" value=
                        <%
                            if(produtoAlt != null) out.print("'" + produtoAlt.getDescricao()+ "'");
                        %>      
                        
                        ><br>
                    </div>
                    <br><br>  
                </div>
                <br><br>
                <input type="submit" name="operacao" value="Salvar_Produto" id="botaoSalvarProduto" class="botaoBuscarProduto botaoAdd">
                <input type="submit" name="operacao" value="Alterar_Produto" id="botaoAlterarProduto" class="botaoBuscarProduto botaoAltProduto">
            </form>
        </div>
    </body>
</html>
