<%-- 
    Document   : login
    Created on : 2 de dez. de 2021, 15:10:03
    Author     : Tiago
--%>

<%@page import="util.Resultado"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Login </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/padraoCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/telaLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        Resultado resultado = (Resultado) request.getAttribute("acesso");
    %>
    <body>
        <form action="Usuario" method="post">
            <div class="formularioLogin">
                <label class="tituloFormularioLogin">Fazer Login</label>
                <%
                    if(resultado != null && resultado.getMsg().equals("Negado")){
                        String mensagem = "<div class='msgAcessoNegado'> Usuário ou senha incorretos.</div>";
                        out.print(mensagem);
                    }
                %>
                <div class="campoLogin camposTelaLogin">
                    <label class="camposTitulos">Usuário</label>
                    <input type="text" id="campoLogin"  name="campoLogin" class="campoLogin-Senha" required>
                </div>

                <div class="campoSenha camposTelaLogin">
                    <label class="camposTitulos">Senha</label>
                    <input type="password" id="campoSenha" requerid name="campoSenha" class="campoLogin-Senha" required>
                </div>

                <div class="campoBotaoEntrar camposTelaLogin">
                    <input type="submit" value="Acessar" name="operacao" id="botaoEntrar" class="botaoEntrar">
                </div>
            </div>
        </form>        
    </body>
</html>
