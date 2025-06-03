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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body class="container">
        
         <% 
            try{
                Produto produto = (Produto) request.getAttribute("lista");
                if (produto != null){
        %>
        
                <h1> Dados do Produto </h1>
                <form class="form" action="ServletProdutoFC" method="post" >
                    <input type="hidden" name="acao" value="alterar">
                    <input type="hidden" name="idproduto" value="<%=produto.getIdproduto()%>">
                    <div class=" mb-3"> 
                        <label class="form-label"> Nome: </label>   
                        <input class="form-control" name="nome" value="<%=produto.getNome()%>"/>
                    </div>
                    <div class=" mb-3"> 
                        <label class="form-label">Quantidade: </label>  
                        <input class="form-control" name="quantidade" value="<%=produto.getQuantidade()%>"/>
                    </div>
                     <div class=" mb-3"> 
                         <label class="form-label"> Preco de Venda: </label>
                         <input class="form-control" name="preco" value="<%=produto.getPrecoVenda()%>"/>
                    </div>
                    <div class=" mb-3"> 
                        <input class=" btn btn-primary" type="submit" value="Alterar Produto"/>
                    </div>
                    

                </form>
         <% 
             } else {
         %>    
                <h1> Dados do Produto </h1>
                <form class="form" action="ServletProdutoFC" method="post" >
                    <input type="hidden" name="acao" value="incluir">
                    <div class=" mb-3">   
                        <label class="form-label">Nome: </label>
                        <input class="form-control" name="nome"/>
                    </div> 
                    <div class=" mb-3">   
                        <label class="form-label">Quantidade: </label>
                        <input class="form-control" name="quantidade"/>
                    </div>
                    <div class=" mb-3">   
                        <label class="form-label">Preco de Venda: </label>
                        <input class="form-control" name="preco"/>
                    </div>       
                <input class=" btn btn-primary" type="submit" value="Adicionar Produto"/>
                </form>
        <%                    
                    }  
                    } catch(ClassCastException nexc){
                     out.print("<h1>"+nexc.getMessage()+"</h1>");}
        %>
    </body>
</html>
