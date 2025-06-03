<%-- 
    Document   : ProdutoLista
    Created on : 3 de out. de 2023, 21:16:09
    Author     : grego
--%>


<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@page import="cadastroee.model.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cadastroee.controller.ProdutoFacadeLocal"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>        
    </head>
    
    <body class="container">
        <div>
            <h1>Listagem de Produtos</h1>
            <h3>
                <a class="btn btn-primary m-2" href="ServletProdutoFC?acao=formIncluir"> Novo Produto  </a>
                <!-- <a href="ServletProdutoFC">  Atualizar Dados  </a> !-->
            </h3>
            
            <table class="table table-striped"  border="1" width="100%">
                <tr class="table-dark">
                    <td> ID </td>
                    <td> Nome </td>
                    <td> Quantidade </td>
                    <td> Preco de Venda </td>
                    <td> Opções </td>
                </tr>
                
                <% 
                    try{
                    List<Produto> lista = (List) request.getAttribute("lista");
                        for(Produto p: lista){
                %>
                <tr>
                    <td>
                        <%=p.getIdproduto()%>
                    </td>
                    <td>
                        <%=p.getNome()%>
                    </td>
                    <td>
                        <%=p.getQuantidade()%>
                    </td>
                    <td>
                        <%=p.getPrecoVenda()%>
                    </td>
                    <td>
                        <a class="btn btn-primary btn-sm" href="ServletProdutoFC?acao=formAlterar&idproduto=<%=p.getIdproduto()%>"> Alterar </a>
                        <a class="btn btn-danger btn-sm" href="ServletProdutoFC?acao=excluir&idproduto=<%=p.getIdproduto()%>"> Excluir </a>
                    </td>
                    
                </tr>
                <% }
                        } catch(NullPointerException nexc){
                        out.print("<h1>"+nexc.getMessage()+"</h1>");
                        
                  }    

                %> 

                
            </table>
        </div>
    </body>
</html>
