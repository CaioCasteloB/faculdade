<%-- 
    Document   : ProdutoDados
    Created on : 5 de out. de 2023, 14:26:07
    Author     : grego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        
         <% 
            try{
                Produto produto = (Produto) request.getAttribute("lista");
                if (produto != null){
        %>
        
                <h1> Dados do Produto </h1>
                <form action="ServletProdutoFC" method="post" >
                    <input type="hidden" name="acao" value="alterar">
                    <input type="hidden" name="idproduto" value="<%=produto.getIdproduto()%>">
                    Nome: <input name="nome" value="<%=produto.getNome()%>"/>
                    Quantidade: <input name="quantidade" value="<%=produto.getQuantidade()%>"/>
                    Preco de Venda: <input name="preco" value="<%=produto.getPrecoVenda()%>"/>
                    <input type="submit" value="Alterar Produto"/>

                </form>
         <% 
             } else {
         %>    
                <h1> Dados do Produto </h1>
                <form action="ServletProdutoFC" method="post" >
                <input type="hidden" name="acao" value="incluir">
                Nome: <input name="nome"/>
                Quantidade: <input name="quantidade"/>
                Preco de Venda: <input name="preco"/>
                <input type="submit" value="Adicionar Produto"/>
                </form>
        <%                    
                    }  
                    } catch(ClassCastException nexc){
                     out.print("<h1>"+nexc.getMessage()+"</h1>");}
        %>
    </body>
</html>
