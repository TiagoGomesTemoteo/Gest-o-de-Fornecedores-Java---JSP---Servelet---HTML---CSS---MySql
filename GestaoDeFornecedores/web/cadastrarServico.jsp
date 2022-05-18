<%-- 
    Document   : cadastrarServico
    Created on : 30 de nov. de 2021, 23:23:33
    Author     : Tiago
--%>

<%@page import="java.util.List"%>
<%@page import="model.dominio.Servico"%>
<%@page import="model.dominio.Fornecedor"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar servico</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/padraoCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/cadastrarServico.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            Fornecedor fornecedor = (Fornecedor)request.getAttribute("fornecedor");
            Servico servicoAlt = (Servico)request.getAttribute("servico");
    
        %>
        <div class="barraLateralEsquerda">
            <a href="index.html"><div class="BotoesMenuLateral">Home</div></a>
            <a href="cadastroFornecedor.html"><div class="BotoesMenuLateral ativo"> Cadastrar Fornecedor </div></a>
            <a href="consultarFornecedor.jsp"><div class="BotoesMenuLateral"> Consultar Fornecedor </div></a>
            <a href="login.jsp"><div class="BotoesMenuLateral"> Sair </div></a>
        </div>
        
        <div class="telaAcao">
            
            <div class="tituloPrincipalCadastrarServico">Cadastrar Serviço</div>
            <div class="msgObrigatoria">
                    <label> Os campos destacados em vermelho são de preenchimento obrigatório para salvar ou alterar um serviço.</label>
            </div>
            <div class="linha-separadoraCadastrarServico"></div>
            
            
            <div class="cadastroServicoCampoID campoPadraoServico mesma-linha">
                <label> ID </label><br><br>
                <input type="text" class="cadastroServicoID" id="idFornecedor" readonly name="cadastroServicoID" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getId() + "'");
                    %>
                    ><br>
            </div>  
            
            <div class="cadastroServicoCampoFornecedor campoPadraoServico mesma-linha">
                <label>Fornecedor</label><br><br>
                <input type="text" class="cadastroServicoFornecedor" id="nomeFantasiaFornecedor" readonly name="cadastroFornecedorCampoFornecedor" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getNmFantasia() + "'");
                    %>
                    ><br>
            </div>

            <div class="cadastroServicoCampoDtRegistro campoPadraoServico mesma-linha">
                <label>Data de registro</label><br><br>
                <input type="text" class="cadastroServicoDtRegistro" id="dtCadastroFornecedor" readonly name="cadastroServicoDtRegistro" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getDtCadastro()+ "'");
                    %>><br>
            </div>
            
            <div class="cabecalhoPesquisaServico">
                <div class="cabecalhoPesquisaIDServico textoCabecalhoServico mesma-linha"> ID </div>
                <div class="cabecalhoPesquisaFornecedorServico textoCabecalhoServico mesma-linha"> Serviço </div>
                <div class="cabecalhoPesquisaCampoPesquisaServico mesma-linha">
                    <input type="text" class="cabecalhoPesquisaNomeServico" placeholder="Pesquisar pelo nome do serviço">
                </div>
            </div>
            
            <form action="Servico" method="post">  
                <div class="resultadosPesquisaServico">          
                    <%
                        if(fornecedor.getServicosOfertados()!= null && fornecedor.getServicosOfertados().size() > 0){
                            List<Servico> servicos = fornecedor.getServicosOfertados();
                            StringBuilder sbRegistro = new StringBuilder();
                            StringBuilder sbLinkVisualizar = new StringBuilder();
                            StringBuilder sbLinkExcluir = new StringBuilder();
                            StringBuilder sbLink = new StringBuilder();

                            if(servicos != null){
                                for(int i = 0; i < servicos.size(); i++){
                                    Servico servico = servicos.get(i);
                                    sbRegistro.setLength(0);
                                    sbLink.setLength(0);
                                    sbLinkVisualizar.setLength(0);
                                    sbLinkExcluir.setLength(0);
                                    
                                    sbLink.append("<a href=Servico?");
                                    sbLink.append("idServico=");
                                    sbLink.append(servico.getId());						
                                    sbLink.append("&");
                                    sbLink.append("idFornecedor=");
                                    sbLink.append(fornecedor.getId());						
                                    sbLink.append("&");
                                    sbLink.append("operacao=");
                                            
                                    sbLinkVisualizar.append(sbLink.toString());
                                    sbLinkVisualizar.append("Visualizar"); 

                                    sbLinkExcluir.append(sbLink.toString());
                                    sbLinkExcluir.append("Excluir_Servico");
                                    
                                    sbRegistro.append("<div class='resultadoServico'>"               
                                        + "<label name='idServico'class='semBordaDireitaServico cabecalhoPesquisaIDServico textoResultadoServico mesma-linha'> "+servico.getId()+" </label>"
                                        + "<label class='semBordaDireitaServico cabecalhoPesquisaFornecedorServico textoResultadoServico mesma-linha'> "+servico.getDescricao()+" </label>"
                                        
                                        + sbLinkVisualizar.toString() + ">"
                                        + "<div id='botaoPesquisaEditarProdutoServico' class=' botaoPesquisaEditarServico  botaoPesquisaAddServico mesma-linha'> Alterar</div>"
                                        + "</a>"
                                        
                                        + sbLinkExcluir.toString() + ">"
                                        + "<input type='button' value='Excluir' id='botaoPesquisaEditarAddServiço' class=' botaoPesquisaExcluirServico botaoPesquisaAddServico  mesma-linha'>"
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
            
            
            <div class="linha-separadoraCadastroServico linha-separadoraCadastroServico"></div>
            
            <form action="Servico" method="post">
                <input type="hidden" name="idFornecedor" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getId() + "'");
                    %>      
                >
                <div class="cadastroServico campoPadrao">             
                    <div class="cadastroServico-campoIdServico campoPadrao">
                        <label>ID</label><br><br>
                        <input type="text" class="cadastroServicoIdServico" id="idServico" readonly name="idServico" value=
                            <%
                                if(servicoAlt != null) out.print("'" + servicoAlt.getId()+ "'");
                            %>       
                            ><br>
                    </div>

                    <div class="cadastroServico-campoDescricaoServico campoPadrao">
                        <label>Descrição</label><br><br>
                        <input type="text" class="cadastroServico-descricaoServico" required="required" id="cadastroServico-descricaoServico" name="descricaoServico" value=
                            <%
                                if(servicoAlt != null) out.print("'" + servicoAlt.getDescricao()+ "'");
                            %>
                        ><br>
                    </div>
                    <br><br>  
                </div>
                <br><br>
                <input type="submit" value="Salvar_Servico" name="operacao" id="botaoAdicionarProduto" class="botaoBuscarServico botaoAdd">
                <input type="submit" value="Alterar_Servico"name="operacao" id="botaoAlterarProduto" class="botaoBuscarServico botaoAltServico">
            </form>
        </div>
    </body>
</html>
