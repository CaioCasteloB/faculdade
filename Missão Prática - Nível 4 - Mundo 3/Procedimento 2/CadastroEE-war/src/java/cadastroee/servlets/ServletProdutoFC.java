/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author grego
 */

@WebServlet(name="ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;
    
    int idAtual =6;
    
    public int aleatorio() {
        // Math.random() gera um número aleatório entre 0.0 e 0.999
        // Assim, Math.random()*5 estará entre 0.0 e 4.999
        double doubleRandomNumber = Math.random() * 100;
        int randomNumber = (int)doubleRandomNumber;
        return randomNumber;
    }
   

     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              String acao = request.getParameter("acao"); 
              String destino = "";
              
              if(acao != null){
              switch(acao){
                                   
                   case "listar":
                    request.setAttribute("lista", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                    
                   case "excluir":
                    int idProduto = Integer.valueOf(request.getParameter("idproduto")); 
                    facade.remove(facade.find(idProduto));
                    request.setAttribute("lista", facade.findAll());
                    RequestDispatcher rd = request.getRequestDispatcher("ProdutoLista.jsp");
                    rd.forward(request, response);
                    break;
                 
                  case "formIncluir":
                    destino = "ProdutoDados.jsp";
                    break;
                  
                  case "formAlterar":
                    int id_produto = Integer.valueOf(request.getParameter("idproduto"));
                    request.setAttribute("lista", facade.find(id_produto));
                    destino = "ProdutoDados.jsp";
                    break;
        }
                    RequestDispatcher rd = request.getRequestDispatcher(destino);
                    rd.forward(request, response);
                    
            } else{
                    request.setAttribute("lista", facade.findAll());
                    RequestDispatcher rd = request.getRequestDispatcher("ProdutoLista.jsp");
                    rd.forward(request, response);
    }}
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    
                    String acao = request.getParameter("acao"); 
                    
                    switch(acao){
                        
                        case("alterar"):
                            int produtoID = Integer.valueOf(request.getParameter("idproduto")); 
                            String nome = request.getParameter("nome");
                            int quantidadeProduto = Integer.valueOf(request.getParameter("quantidade"));
                            float precoProduto = Float.valueOf(request.getParameter("preco"));
                            Produto produtoAlterar = facade.find(produtoID);
                            produtoAlterar.setNome(request.getParameter("nome"));
                            produtoAlterar.setQuantidade(quantidadeProduto);
                            produtoAlterar.setPrecoVenda(precoProduto);
                            facade.edit(produtoAlterar);
                            request.setAttribute("lista", facade.findAll());
                            RequestDispatcher rd = request.getRequestDispatcher("ProdutoLista.jsp");
                            rd.forward(request, response);
                            break;
                        
                        case("incluir"):
                            int idNext = aleatorio(); 
                            
                            if (idNext != idAtual){
                                float preco = Float.valueOf(request.getParameter("preco"));
                                String nome2 = request.getParameter("nome");
                                int quantidade = Integer.valueOf(request.getParameter("quantidade"));
                                Produto produto = new Produto(idNext,
                                        request.getParameter("nome"), 
                                        quantidade, 
                                        preco);
                                facade.create(produto);
                                request.setAttribute("lista", facade.findAll());
                                idAtual = idNext;
                                RequestDispatcher rd2 = request.getRequestDispatcher("ProdutoLista.jsp");
                                rd2.forward(request, response);
                                break;
                            } else {
                                idNext = idNext++;
                                idAtual = idNext;
                                float preco = Float.valueOf(request.getParameter("preco"));
                                String nome2 = request.getParameter("nome");
                                int quantidade = Integer.valueOf(request.getParameter("quantidade"));
                                Produto produto = new Produto(idNext,
                                        request.getParameter("nome"), 
                                        quantidade, 
                                        preco);
                                facade.create(produto);
                                request.setAttribute("lista", facade.findAll());
                                idAtual = idNext;
                                RequestDispatcher rd2 = request.getRequestDispatcher("ProdutoLista.jsp");
                                rd2.forward(request, response);
                                break;
                            }        
                    }          
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
