<%-- 
    Document   : alterarFornecedor
    Created on : 29 de nov. de 2021, 00:44:50
    Author     : Tiago
--%>

<%@page import="model.dominio.Fornecedor"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <title> Alterar fornecedor</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/padraoCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/alterarFornecedor.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="js/jquery.mask.min.js"> </script>
    </head>
<body>
    <%
        Fornecedor fornecedor = (Fornecedor)request.getAttribute("fornecedor");
    
    %>
        <div class="barraLateralEsquerda">
            <a href="index.html"><div class="BotoesMenuLateral">Home</div></a>
            <a href="cadastroFornecedor.html"><div class="BotoesMenuLateral ativo"> Cadastrar Fornecedor </div></a>
            <a href="consultarFornecedor.jsp"><div class="BotoesMenuLateral"> Consultar Fornecedor </div></a>
            <a href="login.jsp"><div class="BotoesMenuLateral"> Sair </div></a>
        </div>  

        <form action="Fornecedor" method="post">
            <input type="hidden" name="hiddenIdCnae" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getListaCNAE().get(0).getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdTelefone" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getListaTelefones().get(0).getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdEndereco" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdContato" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdTelContato" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getTelefone().getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdProduto" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getProdutosOfertados().get(0).getId() + "'");
            %>       
            >
            <input type="hidden" name="hiddenIdServico" value=
            <%
                if(fornecedor != null) out.print("'" + fornecedor.getServicosOfertados().get(0).getId() + "'");
            %>       
            >
            
            <div id="formularioFornecedor" class="formularioFornecedor">
                
                <div class="tituloPrincipal">
                    <label> Alterar Fornecedor</label>
                </div>  
                <div class="msgObrigatoria">
                    <label> Os campos destacados em vermelho são de preenchimento obrigatório.</label>
                </div> 
                
                <div class="campoRazaoSocial campoPadrao">
                    <label>Razão Social </label><br><br>
                    <input type="text" id="razaoSocial" required="required" name="razaoSocial" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getRzSocial() + "'");
                    %>
                    >
                    </input><br>
                </div>

                <div class="campoIdFornecedor campoPadrao">
                    <label>ID: </label><br><br>
                    <input type="text" id="idFornecedor" name="idFornecedor" readonly value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getId() + "'");
                    %>
                    >
                    </input><br>
                    
                </div>    
                
                <div class="campoNomeFantasia campoPadrao">
                    <label>Nome Fantasia</label><br><br>
                    <input type="text" id="nomeFantasia" required="required" name="nomeFantasia" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getNmFantasia() + "'");
                    %>       
                    >
                    </input><br>
                </div>


                <div class="campoEmail campoPadrao"><br>
                    <label>Email</label><br><br>
                    <input type="text" id="email" required="required" name="email" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getEmail() + "'");
                    %>          
                    >
                    </input><br>   
                </div>

                <div class="campoCNPJ campoPadrao"><br>
                    <label>CNPJ</label><br><br>
                    <input type="text" id="cnpj" minlength="18" required="required" name="cnpj" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getCnpj() + "'");
                    %>          
                    >
                    </input><br>
                           
                </div>

                <div class="campoTipoFornecimento campoPadrao"><br>
                    <label>Tipo de fornecimento</label><br><br>
                    <select name="tipoFornecimento" required="required" id="tipoFornecimento">
                        <option value="">Selecione...</option>
                        <option value="venda"
                        <%
                        if(fornecedor != null && fornecedor.getTpFornecimento().equals("venda")) out.print(" selected");
                        %>
                        >Venda</option>
                        <option value="servico"
                        <%
                        if(fornecedor != null && fornecedor.getTpFornecimento().equals("servico")) out.print(" selected");
                        %>       
                        >Serviço</option>
                    </select><br>
                </div>

                <div class="campoInscricaoEstadual campoPadrao"><br><br>
                    <label>Incrição Estadual</label><br><br>
                    <input type="text" id="inscricaoEstadual" minlength="15" required="required" name="inscricaoEstadual" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getInscricaoEstadual() + "'");
                    %>         
                    >
                    </input><br>
                </div>

                <div class="campoInscricaoMunicipal campoPadrao"><br><br>
                    <label>Inscrição Municipal</label><br><br>
                    <input type="text" id="inscricaoMunicipal" required="required" name="inscricaoMunicipal"value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getInscricaoMunicipal() + "'");
                    %>         
                    >
                    </input><br>
                </div>

                <div class="campoStatus campoPadrao"><br><br>   
                    <label for="status">Status</label><br><br>
                    <select name="status" id="status" required="required">
                        <option value="">Selecione...</option>
                        <option value="ativo"
                        <%
                            if(fornecedor != null && fornecedor.getStatus().equals("ativo")) out.print(" selected");
                        %>
                        >Ativo</option>
                        <option value="rascunho"
                        <%
                            if(fornecedor != null && fornecedor.getStatus().equals("rascunho")) out.print(" selected");
                        %>        
                        >Rascunho</option>
                        <option value="inativo"
                        <%
                            if(fornecedor != null && fornecedor.getStatus().equals("inativo")) out.print(" selected");
                        %>          
                        >Inativo</option>
                    </select>
                </div>


                <div id="campoCNAE" class="campoCNAE campoPadrao"><br><br><br>
                    <label>CNAE(S)</label><br><br>
                    <input type="text" id="cnae" minlength="9" required="required" name="cnae" value=
                    <%
                        if(fornecedor != null) out.print("'" + fornecedor.getListaCNAE().get(0).getNumero()+ "'");
                    %>   
                    ></input>
                </div>

                <input type="button" value="Adicionar" id="botaoAddCNA" class="botaoAddCNAE botaoAdd">

            </div>
            <div class="linha-separadora linha-separadora-fornecedor"></div> 

            <div class="formularioTelefone">
                <div class="Subtitulos">
                    <label> Telefone (S)</label>
                </div>
                <div id="campoTelefone" class="telefone campoPadrao">
                    <div class="campoDDI campoPadrao mesma-linha"><br><br>   
                        <label>DDI</label><br><br>
                        <input id="ddi" class="ddi" type="text" name="ddi" minlength="2" required="required" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaTelefones().get(0).getDdi()+ "'");
                        %> 
                        ></input>
                    </div>

                    <div class="campoNumeroTelefone mesma-linha">
                        <label>Número</label><br><br>
                        <input id="numeroTelefone" class="numeroTelefone" minlength="14" required="required" type="text" name="numeroTelefone" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaTelefones().get(0).getDdd() + fornecedor.getListaTelefones().get(0).getNumero()+ "'");
                        %>               
                        ></input>
                    </div>  

                    <div class="campoTipoTelefone campoPadrao mesma-linha"><br><br>   
                        <label>Tipo de telefone</label><br><br>
                        <select name="tipoTelefone" id="tipoTelefone" required="required">
                            <option value="">Selecione...</option>
                            <option value="fixo"
                            <%
                            if(fornecedor != null && fornecedor.getListaTelefones().get(0).getTpTelefone().getDescricao().equals("fixo")) out.print(" selected");
                            %>       
                            >fixo</option>
                            <option value="celular"
                            <%
                            if(fornecedor != null && fornecedor.getListaTelefones().get(0).getTpTelefone().getDescricao().equals("celular")) out.print(" selected");
                            %>          
                            >celular</option>
                        </select>
                    </div>   
                    <br>
                </div>
                <input type="button" value="Adicionar" id="botaoAddTelefone" class="botaoAddTelefone botaoAdd">  
            </div>

            <div class="linha-separadora linha-separadora-telefone"></div>

            <div class="formularioEndereços">
                <div class="Subtitulos">
                    <label> Endereço (S)</label>
                </div> 
                <div id="campoEndereco" class="endereco campoPadrao">
                    <div class="campoTipoLogradouro campoPadrao mesma-linha">
                        <label>Tipo de Logradouro</label><br><br>
                        <select name="tipoLogradouro" id="tipoLogradouro" required="required">
                            <option value="">Selecione...</option>
                            <option value="avenida"
                            <%
                            if(fornecedor != null && fornecedor.getEndereco().getTpLogradouro().getDescricao().equals("avenida")) out.print(" selected");
                            %>
                            >Avenida</option>
                            <option value="estrada"
                            <%
                            if(fornecedor != null && fornecedor.getEndereco().getTpLogradouro().getDescricao().equals("estrada")) out.print(" selected");
                            %>
                            >Estrada</option>
                            <option value="rua"
                            <%
                            if(fornecedor != null && fornecedor.getEndereco().getTpLogradouro().getDescricao().equals("rua")) out.print(" selected");
                            %>
                            >Rua</option>   
                        </select>
                    </div>

                    <div class="campoLogradouro campoPadrao mesma-linha">
                        <label>Logradouro</label><br><br>
                        <input type="text" class="logradouro" id="logradouro" required="required" name="logradouro" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getLogradouro() + "'");
                        %> 
                        ></input><br>
                    </div>

                    <div class="campoNumero campoPadrao mesma-linha">
                        <label>Número</label><br><br> 
                        <input type="text" id="numero" required="required" name="numero" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getNumero()+ "'");
                        %> 
                        ></input><br>        
                    </div>

                    <div class="campoCEP campoPadrao mesma-linha">
                        <label>CEP</label><br><br>  
                        <input type="text" class="cep" id="cep" minlength="9" required="required" name="cep" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getCep()+ "'");
                        %> 
                        ></input><br>       
                    </div>
                    <br>
                    <div class="campoBairro campoPadrao mesma-linha">
                        <label>Bairro</label><br><br>
                        <input type="text" class="bairro" id="bairro" required="required" name="bairro" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getBairro()+ "'");
                        %> 
                        ></input><br>
                    </div>

                    <div class="campoCidade campoPadrao mesma-linha">
                        <label>Cidade</label><br><br>
                        <input type="text" class="cidade" id="cidade" required="required" name="cidade" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getCidade().getDescricao()+ "'");
                        %> 
                        ></input><br>
                    </div>
                    <br>
                    <div class="campoUF campoPadrao mesma-linha">
                        <label>Unidade Federal</label><br><br>
                        <select name="uf" class="uf" required="required" id="uf">
                            <option value="">Selecione...</option>
                            <option value="Sao Paulo - SP"
                            <%
                                if(fornecedor != null && fornecedor.getEndereco().getCidade().getUf().getDescricao().equals("Sao Paulo - SP")) out.print(" selected");
                            %>        
                            >São Paulo - SP</option>
                            <option value="Rio de Janeiro - RJ"
                            <%
                                if(fornecedor != null && fornecedor.getEndereco().getCidade().getUf().getDescricao().equals("Rio de Janeiro - RJ")) out.print(" selected");
                            %>
                            >Rio de Janeiro - RJ</option>
                        </select>
                    </div>

                    <div class="campoPais campoPadrao mesma-linha">
                        <label>País</label><br><br>
                        <input type="text" class="pais" required="required" id="pais" name="pais" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getCidade().getUf().getPais().getDescricao()+ "'");
                        %> 
                        ></input><br>
                    </div>

                    <div class="campoTipoEndereco campoPadrao mesma-linha">
                        <label>Tipo de endereço</label><br><br>
                        <select name="tipoEndereco" required="required" class="tipoEndereco" id="tipoEndereco">
                            <option value="">Selecione...</option>
                            <option value="galpao"
                            <%
                                if(fornecedor != null && fornecedor.getEndereco().getTpEndereco().getDescricao().equals("galpao")) out.print(" selected");
                            %>        
                            >Galpão</option>
                            <option value="escritorio"
                            <%
                                if(fornecedor != null && fornecedor.getEndereco().getTpEndereco().getDescricao().equals("escritorio")) out.print(" selected");
                            %>        
                            >Escritório</option>
                        </select>
                    </div>
                    <br>
                    <div class="campoComplemento campoPadrao">
                        <label>Complemento</label><br><br>
                        <input type="text" class="complemento" id="complemento" name="complemento" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getEndereco().getComplemento()+ "'");
                        %> 
                        ></input><br>
                    </div>
                </div>
                <input type="button" value="Adicionar" id="botaoAddEndereco" class="botaoAddEndereco botaoAdd">
            </div>

            <div class="linha-separadora linha-separadora-endereco"></div>

            <div class="formularioContatos"> 
                <div class="Subtitulos">
                    <label> Contato (S)</label>
                </div>

                <div id="campoContato" class="contato campoPadrao">

                    <div class="campoNomeContato campoPadrao mesma-linha">
                        <label>Nome</label><br><br>
                        <input type="text" required="required" class="nomeContato" id="nomeContato" name="nomeContato" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getNome()+ "'");
                        %> 
                        ></input><br>
                    </div>

                    <div class="campoEmailContato campoPadrao mesma-linha">
                        <label>Email</label><br><br>
                        <input type="text" required="required" class="emailContato" id="emailContato" name="emailContato" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getEmail()+ "'");
                        %> 
                        ></input><br>
                    </div>

                    <br>

                    <div class="campoDepartamentoContato campoPadrao mesma-linha">
                        <label>Departamento</label><br><br>
                        <input type="text" required="required" class="departamentoContato" id="departamentoContato" name="departamentoContato" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getDepartamento().getDescricao()+ "'");
                        %> 
                        ></input><br>
                    </div>

                    <div class="campoDDIContato campoPadrao mesma-linha"><br><br>   
                        <label>DDI</label><br><br>
                        <input id="ddiContato" minlength="2" required="required" class="ddiContato" type="text" name="ddiContato" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getTelefone().getDdi()+ "'");
                        %> 
                        ></input>
                    </div>

                    <div class="campoNumeroTelefoneContato mesma-linha">
                        <label>Número</label><br><br>
                        <input id="numeroTelefoneContato" minlength="14" required="required"  class="numeroTelefoneContato" type="text" name="numeroTelefoneContato" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getListaContatos().get(0).getTelefone().getDdd() + fornecedor.getListaContatos().get(0).getTelefone().getNumero()+ "'");
                        %> 
                        ></input>
                    </div>  

                    <div class="campoTipoTelefoneContato campoPadrao mesma-linha"><br><br>   
                        <label>Tipo de telefone</label><br><br>
                        <select name="tipoTelefoneContato" required="required" id="tipoTelefoneContato">
                            <option value="">Selecione...</option>
                            <option value="fixo"
                            <%
                                if(fornecedor != null && fornecedor.getListaContatos().get(0).getTelefone().getTpTelefone().getDescricao().equals("fixo")) out.print(" selected");
                            %>        
                            >fixo</option>
                            <option value="celular"
                            <%
                                if(fornecedor != null && fornecedor.getListaContatos().get(0).getTelefone().getTpTelefone().getDescricao().equals("celular")) out.print(" selected");
                            %>        
                            >celular</option>
                        </select>
                    </div>  
                </div> 
                <br>    
                <input type="button" value="Adicionar" id="botaoAddContato" class="botaoAddContato botaoAdd">
            </div>
            
            <div class="linha-separadora linha-separadora-contatos"></div>
            
            <div class="formularioProdutos">
                
                <div class="Subtitulos">
                    <label> Produto (S)</label>
                </div>
                
                <div id="campoProduto" class="produto campoPadrao">             
                    <div class="campoNomeProduto campoPadrao">
                        <label>Nome</label><br><br>
                        <input type="text" required="required" class="nomeProduto" id="nomeProduto" name="nomeProduto" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getProdutosOfertados().get(0).getNome() + "'");
                        %> 
                        ></input><br>
                    </div>
                    
                    <div class="campoDescricaoProduto campoPadrao">
                        <label>Descrição</label><br><br>
                        <input type="text" required="required" class="descricaoProduto" id="descricaoProduto" name="descricaoProduto" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getProdutosOfertados().get(0).getDescricao()+ "'");
                        %> 
                        ></input><br>
                    </div>
                    <br><br>  
                </div>
                
                <input type="button" value="Adicionar" id="botaoAddProduto" class="botaoAddProduto botaoAdd">
            </div> 
            <br>
            <div class="linha-separadora linha-separadora-contatos"></div>
            
            <div class="formularioServicos">
                <div class="Subtitulos">
                    <label> Serviço (S)</label>
                </div>
                <br>
                <div id="campoServico" class="servico campoPadrao">
                    <div class="campoDescricaoServico campoPadrao mesma-linha">
                        <label>Descrição</label><br><br>
                        <input type="text" class="descricaoServico" id="descricaoServico" name="descricaoServico" value=
                        <%
                            if(fornecedor != null) out.print("'" + fornecedor.getServicosOfertados().get(0).getDescricao() + "'");
                        %> 
                        ></input><br>
                    </div>
                    <br><br> 
                </div>  
                
                <input type="button" value="Adicionar" id="botaoAddServico" class="botaoAddServico botaoAdd">
            </div>
            <div class="formularioBotoesSalvarRascunhoCancelar">
                <input type="submit" value="Alterar" name="operacao" id="botaoSalvar" class="botao-Salvar-Rascunho-Excluir botaoSalvar">
                <input type="submit" value="Excluir" name="operacao" id="botaoExcluir" class="botao-Salvar-Rascunho-Excluir botaoExcluir">
            </div>  
            <br><br>
            <div class="espacamento"></div>    
            
        </form>  





        <script>
            /*Mascáras*/
            $(document).ready(function(){
                $('#cnpj').mask('00.000.000/0000-00', {reverse: false});
                $('#inscricaoEstadual').mask('000.000.000.000', {reverse: false});
                $('#cnae').mask('0000-0/00', {reverse: false});
                $('#ddi').mask('+000', {reverse: false});
                $('#numeroTelefone').mask('(00) 00000-0000', {reverse: false});
                $('#cep').mask('00000-000', {reverse: false});
                $('#ddiContato').mask('+000', {reverse: false});
                $('#numeroTelefoneContato').mask('(00) 00000-0000', {reverse: false});
            });
            
            /*CNAE*/
            var contCNAE = 1;
            $("#botaoAddCNA").click(function () {
                contCNAE++;
                $("#campoCNAE").append('<div id="cnae' + contCNAE + '"><br>\n\
                <input class="mesma-linha" type="text" name="cnae[]">\n\
                <button id="' + contCNAE + '" class="btn-apagar mesma-linha"> X </button>\n\
                </div>');
            });

            $('form').on('click', '.btn-apagar', function () {
                var button_id = $(this).attr("id");
                $('#cnae' + button_id + '').remove();
            });


            var contTelefone = 1;
            $("#botaoAddTelefone").click(function () {
                contTelefone++;
                $("#campoTelefone").append('\
                <div id="campoDDI' + contTelefone + '" class="campoDDI campoPadrao mesma-linha"><br><br>\n\
                    <label>DDI</label><br><br>\n\
                <input id="ddi" class="ddi" type="text" name="ddi[]">\n\
                </div>\n\
\n\
                <div id="campoNumeroTelefone' + contTelefone + '" class="campoNumeroTelefone mesma-linha">\n\
                    <label>Número</label><br><br>\n\
                    <input id="numeroTelefone" class="numeroTelefone" type="text" name="numeroTelefone[]">\n\
                </div>\n\
\n\
                <div id="campoTipoTelefone' + contTelefone + '" class="campoTipoTelefone campoPadrao mesma-linha"><br><br>\n\
                    <label>Tipo de telefone</label><br><br>\n\
                    <select name="tipoTelefone[]" id="tipoTelefone">\n\
                        <option>Selecione...</option>\n\
                        <option value="fixo">fixo</option>\n\
                        <option value="celular">celular</option>\n\
                    </select>\n\
\n\
                    <button id="' + contTelefone + '" class="btn-apagar-telefone mesma-linha"> X </button>\n\
                </div>\n\
                <br id="brTelefone' + contTelefone + '">\n\
');
            });

            $('form').on('click', '.btn-apagar-telefone', function () {
                var button_id = $(this).attr("id");
                $('#campoDDI' + button_id + '').remove();
                $('#campoNumeroTelefone' + button_id + '').remove();
                $('#campoTipoTelefone' + button_id + '').remove();
                $('#campoTipoTelefone' + button_id + '').remove();
                $('#brTelefone' + button_id + '').remove();
            });




            /*ENDEREÇOS*/

            var contEndereco = 1;
            $("#botaoAddEndereco").click(function () {
                contEndereco++;
                $("#campoEndereco").append('\
                    <div id="l-separa-end' + contEndereco + '" class="linha-separadora-endereco-adicionais"></div>\n\
                    <br id="brEndereco1' + contEndereco + '"> <br id="brEndereco2' + contEndereco + '">\n\
\n\                 <br id="brEndereco6' + contEndereco + '"> <br id="brEndereco7' + contEndereco + '">\n\
\n\                 <br id="brEndereco8' + contEndereco + '">\n\
\n\
                    <div id="campoTipoLogradouro' + contEndereco + '" class="campoTipoLogradouro campoPadrao mesma-linha">\n\
                        <label>Tipo de Logradouro</label><br><br>\n\
                            <select name="tipoLogradouro[]" id="tipoLogradouro">\n\
                                <option>Selecione...</option>\n\
                                <option value="avenida">Avenida</option>\n\
                                <option value="estrada">Estrada</option>\n\
                                <option value="jardim">Jardim</option>\n\
                                <option value="rua">Rua</option>\n\
                                <option value="vila">Vila</option>\n\
                            </select>\n\
                    </div>\n\
\n\
                    <div id="campoLogradouro' + contEndereco + '" class="campoLogradouro campoPadrao mesma-linha">\n\
                        <label>Logradouro</label><br><br>\n\
                        <input type="text" class="logradouro" id="logradouro" name="logradouro[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoNumero' + contEndereco + '" class="campoNumero campoPadrao mesma-linha">\n\
                        <label>Número</label><br><br>\n\
                        <input type="text" id="numero" name="numero[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoCEP' + contEndereco + '" class="campoCEP campoPadrao mesma-linha">\n\
                        <label>CEP</label><br><br>\n\
                        <input type="text" class="cep" id="cep" name="cep[]"><br>\n\
                    </div>\n\
\n\
\n\                 <br id="brEndereco3' + contEndereco + '">\n\
\n\
                    <div id="campoBairro' + contEndereco + '" class="campoBairro campoPadrao mesma-linha">\n\
                        <label>Bairro</label><br><br>\n\
                        <input type="text" class="bairro" id="bairro" name="bairro[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoCidade' + contEndereco + '" class="campoCidade campoPadrao mesma-linha">\n\
                        <label>Cidade</label><br><br>\n\
                        <input type="text" class="cidade" id="cidade" name="cidade[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoUF' + contEndereco + '" class="campoUF campoPadrao mesma-linha">\n\
                        <label>Unidade Federal</label><br><br>\n\
                        <select name="uf[]" class="uf" id="uf">\n\
                            <option>Selecione...</option>\n\
                            <option value="São Paulo - SP">São Paulo - SP</option>\n\
                            <option value="Rio de Janeiro - RJ">Rio de Janeiro - RJ</option>\n\
                        </select>\n\
                    </div>\n\
\n\
\n\                 <div id="campoPais' + contEndereco + '" class="campoPais campoPadrao mesma-linha">\n\
                        <label>País</label><br><br>\n\
                        <input type="text" class="pais" id="pais" name="pais[]"><br>\n\
                    </div>\n\
\n\
\n\                 <div id="campoTipoEndereco' + contEndereco + '" class="campoTipoEndereco campoPadrao mesma-linha">\n\
                        <label>Tipo de endereço</label><br><br>\n\
                        <select name="tipoEndereco[]" class="tipoEndereco" id="tipoEndereco">\n\
                            <option>Selecione...</option>\n\
                            <option value="galpao">Galpão</option>\n\
                            <option value="escritorio">Escritório</option>\n\
                        </select>\n\
                    </div>\n\
\n\
                    <br id="brEndereco4' + contEndereco + '">\n\
\n\
\n\                 <div id="campoComplemento' + contEndereco + '" class="campoComplemento campoPadrao">\n\
                        <label>Complemento</label><br><br>\n\
                        <input type="text" class="complemento" id="complemento" name="complemento[]"><br><br>\n\
\n\                     <button id="' + contEndereco + '" class="btn-apagar-endereco"> Descartar </button>\n\
                    </div>\n\
\n\
                    <br id="brEndereco5' + contEndereco + '">\n\
');
            });

            $('form').on('click', '.btn-apagar-endereco', function () {
                var button_id = $(this).attr("id");
                $('#campoTipoLogradouro' + button_id + '').remove();
                $('#campoLogradouro' + button_id + '').remove();
                $('#campoNumero' + button_id + '').remove();
                $('#campoCEP' + button_id + '').remove();
                $('#campoBairro' + button_id + '').remove();
                $('#campoCidade' + button_id + '').remove();
                $('#campoUF' + button_id + '').remove();
                $('#campoPais' + button_id + '').remove();
                $('#campoTipoEndereco' + button_id + '').remove();
                $('#campoComplemento' + button_id + '').remove();

                $('#brEndereco1' + button_id + '').remove();
                $('#brEndereco2' + button_id + '').remove();
                $('#brEndereco3' + button_id + '').remove();
                $('#brEndereco4' + button_id + '').remove();
                $('#brEndereco5' + button_id + '').remove();
                $('#brEndereco6' + button_id + '').remove();
                $('#brEndereco7' + button_id + '').remove();
                $('#brEndereco8' + button_id + '').remove();

                $('#l-separa-end' + button_id + '').remove();

            });



            /*CONTATOS*/

            var contContatos = 1;
            $("#botaoAddContato").click(function() {
            contContatos++;
                    $("#campoContato").append('\
                    <br id="brContato1' + contContatos + '">\n\
                    <br id="brContato2' + contContatos + '">\n\
                    <br id="brContato3' + contContatos + '">\n\
                    <br id="brContato4' + contContatos + '">\n\
\n\
                    <div id="campoNomeContato'+contContatos+'" class="campoNomeContato campoPadrao mesma-linha">\n\
                        <label>Nome</label><br><br>\n\
                        <input type="text" class="nomeContato" id="nomeContato" name="nomeContato[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoEmailContato'+contContatos+'" class="campoEmailContato campoPadrao mesma-linha">\n\
                        <label>Email</label><br><br>\n\
                        <input type="text" class="emailContato" id="emailContato" name="emailContato[]"><br>\n\
                    </div>\n\
\n\
                    <br id="brContato5' + contContatos + '">\n\
\n\
                    <div id="campoDepartamento'+contContatos+'" class="campoDepartamentoContato campoPadrao mesma-linha">\n\
                        <label>Departamento</label><br><br>\n\
                        <input type="text" class="departamentoContato" id="departamentoContato" name="departamentoContato[]"><br>\n\
                    </div>\n\
\n\
                    <div id="campoDDIContato'+contContatos+'" class="campoDDIContato campoPadrao mesma-linha"><br><br>\n\
                        <label>DDI</label><br><br>\n\
                        <input id="ddiContato" class="ddiContato" type="text" name="ddiContato[]">\n\
                    </div>\n\
\n\
\n\                 <div id="campoNumeroTelefoneContato'+contContatos+'" class="campoNumeroTelefoneContato mesma-linha">\n\
                        <label>Número</label><br><br>\n\
                        <input id="numeroTelefoneContato" class="numeroTelefoneContato" type="text" name="numeroTelefoneContato[]">\n\
                    </div>\n\
\n\
                    <div id="campoTipoTelefoneContato'+contContatos+'" class="campoTipoTelefoneContato campoPadrao mesma-linha"><br><br>\n\
\n\                     <label>Tipo de telefone</label><br><br>\n\
\n\                     <select name="tipoTelefoneContato[]" id="tipoTelefoneContato">\n\
                            <option>Selecione...</option>\n\
                            <option value="fixo">fixo</option>\n\
                            <option value="celular">celular</option>\n\
                        </select>\n\
                    </div>\n\
\n\
\n\                 <br id="brContato6' + contContatos + '">\n\
                    <br id="brContato7' + contContatos + '">\n\
\n\
\n\                 <button id="' + contContatos + '" class="btn-apagar-contato"> Descartar </button>\n\
');
                    });
                    
            $('form').on('click', '.btn-apagar-contato', function () {
                var button_id = $(this).attr("id");
                $('#campoNomeContato' + button_id + '').remove();
                $('#campoEmailContato' + button_id + '').remove();
                $('#campoDepartamento' + button_id + '').remove();
                $('#campoDDIContato' + button_id + '').remove();
                $('#campoNumeroTelefoneContato' + button_id + '').remove();
                $('#campoTipoTelefoneContato' + button_id + '').remove();
                $('#' + button_id + '').remove();
                
                $('#brContato1' + button_id + '').remove();
                $('#brContato2' + button_id + '').remove();
                $('#brContato3' + button_id + '').remove();
                $('#brContato4' + button_id + '').remove();
                $('#brContato5' + button_id + '').remove();
                $('#brContato6' + button_id + '').remove();
                $('#brContato7' + button_id + '').remove();
            });
            
            
            /*PRODUTOS*/
            
            var contProduto = 1;
            $("#botaoAddProduto").click(function () {
                contProduto++;
                $("#campoProduto").append('\n\
                    <br id="brProduto1'+contProduto+'">\n\
                    <br id="brProduto2'+contProduto+'">\n\
\n\
                    <div id="campoNomeProduto'+contProduto+'" class="campoNomeProduto campoPadrao">\n\
                        <label>Nome</label><br><br>\n\
                        <input type="text" class="nomeProduto" id="nomeProduto" name="nomeProduto[]"><br>\n\
                    </div>\n\
                    \n\
\n\
                    <div id="campoDescricaoProduto'+contProduto+'" class="campoDescricaoProduto campoPadrao">\n\
                        <label>Descrição</label><br><br>\n\
                        <input type="text" class="descricaoProduto" id="descricaoProduto" name="descricaoProduto[]"><br>\n\
                    </div>\n\
\n\
                    <br id="brProduto3'+contProduto+'">\n\
                    <br id="brProduto4'+contProduto+'">\n\
\n\
\n\                 <button id="' + contProduto + '" class="btn-apagar-produto"> Descartar </button>\n\
\n\
\n\                 <br id="brProduto5'+contProduto+'">\n\
\n\                 <br id="brProduto6'+contProduto+'">\n\
');
        
            });
            
            $('form').on('click', '.btn-apagar-produto', function () {
                var button_id = $(this).attr("id");
                $('#campoNomeProduto' + button_id + '').remove();
                $('#campoDescricaoProduto' + button_id + '').remove();
                $('#' + button_id + '').remove();
                
                $('#brProduto1' + button_id + '').remove();
                $('#brProduto2' + button_id + '').remove();
                $('#brProduto3' + button_id + '').remove();
                $('#brProduto4' + button_id + '').remove();
                $('#brProduto5' + button_id + '').remove();
                $('#brProduto6' + button_id + '').remove();

            });
            
            /*SERVICOS*/
            var contServico = 1;
            $("#botaoAddServico").click(function () {
                contServico++;
                $("#campoServico").append('\n\
                    <div id="campoDescricaoServico'+contServico+'" class="campoDescricaoServico campoPadrao mesma-linha">\n\
                        <label>Descrição</label><br><br>\n\
                        <input type="text" class="descricaoServico" id="descricaoServico" name="descricaoProdutoServico[]"><br>\n\
                    </div>\n\
\n\                 <button id="' + contServico + '" class="btn-apagar-servico mesma-linha"> X </button>\n\
\n\
                    <br id="brServico1'+contServico+'">\n\
                    <br id="brServico2'+contServico+'">');
            });
            
            $('form').on('click', '.btn-apagar-servico', function () {
                var button_id = $(this).attr("id");
                $('#campoDtInicioServico' + button_id + '').remove();
                $('#campoDescricaoServico' + button_id + '').remove();
                $('#' + button_id + '').remove();
     
                $('#brServico1' + button_id + '').remove();
                $('#brServico2' + button_id + '').remove();
            });

        </script>
    </body>
</html>
