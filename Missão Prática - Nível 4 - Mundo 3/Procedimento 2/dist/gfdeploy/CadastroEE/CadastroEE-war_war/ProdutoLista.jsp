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
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Listagem de Produtos</h1>
            <h3>
                <a href="ServletProdutoFC?acao=formIncluir"> Novo Produto  </a>
                <!-- <a href="ServletProdutoFC">  Atualizar Dados  </a> !-->
            </h3>
            
            <table border="1" width="100%">
                <tr>
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
                        <a href="ServletProdutoFC?acao=formAlterar&idproduto=<%=p.getIdproduto()%>"> Alterar </a>
                        <a href="ServletProdutoFC?acao=excluir&idproduto=<%=p.getIdproduto()%>"> Excluir </a>
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
