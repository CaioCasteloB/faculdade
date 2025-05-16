/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;

import cadastro.model.util.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cadastrobd.model.PessoaJuridica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grego
 */
public class PessoaJuridicaDAO {
    
    public ConectorBD connection = new ConectorBD();
    
    public PessoaJuridica getPessoa(int id)throws Exception {
        PessoaJuridica pessoa = null;
        String sql = "select *\n" +
                      "from pessoa, pessoa_juridica\n" +
                      "where pessoa.idpessoa = "+ id + "AND " +
                      "pessoa.idpessoa = pessoa_juridica.idpessoa;";
            PreparedStatement ps = connection.getPrepared(sql);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                pessoa = new PessoaJuridica(resultado.getInt("idpessoa"),
                resultado.getString("nome"),
                resultado.getString("logradouro"),
                resultado.getString("cidade"),
                resultado.getString("estado"),
                resultado.getString("telefone"),
                resultado.getString("email"),
                resultado.getString("cnpj"));
                connection.closeConnection();
                //connection.closeResult(ps);
                connection.closeStatement(sql);
            } return pessoa;  
    } 
    
    public List<PessoaJuridica> getPessoas() throws Exception{
        List<PessoaJuridica> lista = new ArrayList<>();
        String sql = "select *\n" +
                      "from pessoa, pessoa_juridica\n" +
                      "where pessoa.idpessoa = pessoa_juridica.idpessoa;";
            PreparedStatement ps = connection.getPrepared(sql);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                //System.out.println(resultado.getString(5));
                lista.add(new PessoaJuridica(resultado.getInt("idpessoa"),
                resultado.getString("nome"),
                resultado.getString("logradouro"),
                resultado.getString("cidade"),
                resultado.getString("estado"),
                resultado.getString("telefone"),
                resultado.getString("email"),
                resultado.getString("cnpj")));
                connection.closeConnection();
                //connection.closeResult(ps);
                connection.closeStatement(sql);
            } return lista;
            
    }   

    public void incluir(PessoaJuridica pessoajuridica)throws Exception{
        String sqljuridica  = "insert into pessoa_juridica (idpessoa, cnpj) values (?,?)"; 
        String sqlpessoa = "insert into pessoa (idpessoa,nome,logradouro, cidade,"
                + "estado, telefone, email ) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.getPrepared(sqljuridica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        //ResultSet resultado = ps.executeQuery();
        ps.setInt(1, pessoajuridica.getId());
        ps.setString(2, pessoajuridica.getCnpj());
        ps1.setInt(1, pessoajuridica.getId());
        ps1.setString(2, pessoajuridica.getNome());
        ps1.setString(3, pessoajuridica.getLogradouro());
        ps1.setString(4, pessoajuridica.getCidade());
        ps1.setString(5, pessoajuridica.getEstado());
        ps1.setString(6, pessoajuridica.getTelefone());
        ps1.setString(7, pessoajuridica.getEmail());
        ps1.execute();
        ps.execute();
        connection.closeConnection();
        //connection.closeResult(ps);
        connection.closeStatement(sqljuridica);
    }  
    
    public void alterar(int id, String cnpj, String nome, String logradouro, 
        String cidade, String estado,String telefone, String email)throws Exception{
        PessoaJuridica pessoa = getPessoa(id);
        String sqljuridica = "UPDATE pessoa_juridica SET cnpj=? where idpessoa = "+id;
        String sqlpessoa = "UPDATE pessoa SET nome=?, logradouro=?, cidade=?,"
                + "estado=?, telefone=?, email=? WHERE idpessoa= "+id;
        PreparedStatement ps = connection.getPrepared(sqljuridica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        if(cnpj.equals("")){
           ps.setString(1, pessoa.getCnpj());
        } else{
            ps.setString(1, cnpj);
        }
        
        if(nome.equals("")){
           ps1.setString(1, pessoa.getNome());
        } else{
            ps1.setString(1, nome);
        } 
        
        if(logradouro.equals("")){
           ps1.setString(2, pessoa.getLogradouro());
        } else{
            ps1.setString(2, logradouro);
        }
        
        if(cidade.equals("")){
           ps1.setString(3, pessoa.getCidade());
        } else{
            ps1.setString(3, cidade);
        }
        
        if(estado.equals("")){
           ps1.setString(4, pessoa.getEstado());
        } else{
            ps1.setString(4, estado);
        }
        
        if(telefone.equals("")){
           ps1.setString(5, pessoa.getTelefone());
        } else{
            ps1.setString(5, telefone);
        }
        if(email.equals("")){
           ps1.setString(6, pessoa.getEmail());
        } else{
            ps1.setString(6, email);
        } 
        ps.execute();
        ps1.execute();
        connection.closeConnection();
        //connection.closeResult(ps);
        connection.closeStatement(sqljuridica);
    }
    
    public void excluir(int id)throws Exception{
        String sqljuridica = "DELETE FROM pessoa_juridica WHERE idpessoa="+id;
        String sqlpessoa = "DELETE FROM pessoa WHERE idpessoa="+id;
        PreparedStatement ps = connection.getPrepared(sqljuridica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        ps.execute();
        ps1.execute();
        connection.closeConnection();
        //connection.closeResult(ps);
        connection.closeStatement(sqljuridica);
    }
    
}
