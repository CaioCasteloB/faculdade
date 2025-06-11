/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;
import cadastrobd.model.PessoaFisica;
import java.util.ArrayList;
import java.util.List;
import cadastro.model.util.ConectorBD;
import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author caio
 */
public class PessoaFisicaDAO {
    
    public ConectorBD connection = new ConectorBD();
    
    public PessoaFisica getPessoa(int id)throws Exception {
        PessoaFisica pessoa = null;
        String sql = "select *\n" +
                      "from pessoa, pessoa_fisica\n" +
                      "where pessoa.idpessoa = "+ id + "AND " +
                      "pessoa.idpessoa = pessoa_fisica.idpessoa;";
            PreparedStatement ps = connection.getPrepared(sql);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                pessoa = new PessoaFisica(resultado.getInt("idpessoa"),
                resultado.getString("nome"),
                resultado.getString("logradouro"),
                resultado.getString("cidade"),
                resultado.getString("estado"),
                resultado.getString("telefone"),
                resultado.getString("email"),
                resultado.getString("cpf"));
                connection.closeConnection();
                //connection.closeResult(ps);
                connection.closeStatement(sql);
            } return pessoa;  
    } 
    
       
    public List<PessoaFisica> getPessoas() throws Exception{
        List<PessoaFisica> lista = new ArrayList<>();
        String sql = "select *\n" +
                      "from pessoa, pessoa_fisica\n" +
                      "where pessoa.idpessoa = pessoa_fisica.idpessoa;";
            PreparedStatement ps = connection.getPrepared(sql);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                //System.out.println(resultado.getString(5));
                lista.add(new PessoaFisica(resultado.getInt("idpessoa"),
                resultado.getString("nome"),
                resultado.getString("logradouro"),
                resultado.getString("cidade"),
                resultado.getString("estado"),
                resultado.getString("telefone"),
                resultado.getString("email"),
                resultado.getString("cpf")));
                connection.closeConnection();
                //connection.closeResult(ps);
                connection.closeStatement(sql);
            } return lista;
            
    }       
   
    
    public void incluir(PessoaFisica pessoafisica)throws Exception{
        String sqlfisica  = "insert into pessoa_fisica (idpessoa, cpf) values (?,?)"; 
        String sqlpessoa = "insert into pessoa (idpessoa,nome,logradouro, cidade,"
                + "estado, telefone, email ) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.getPrepared(sqlfisica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        //ResultSet resultado = ps.executeQuery();
        ps.setInt(1, pessoafisica.getId());
        ps.setString(2, pessoafisica.getCpf());
        ps1.setInt(1, pessoafisica.getId());
        ps1.setString(2, pessoafisica.getNome());
        ps1.setString(3, pessoafisica.getLogradouro());
        ps1.setString(4, pessoafisica.getCidade());
        ps1.setString(5, pessoafisica.getEstado());
        ps1.setString(6, pessoafisica.getTelefone());
        ps1.setString(7, pessoafisica.getEmail());
        ps1.execute();
        ps.execute();
        connection.closeConnection();
        //connection.closeResult(ps);
        connection.closeStatement(sqlfisica);
    }
    
    public void alterar(int id, String cpf, String nome, String logradouro, 
        String cidade, String estado,String telefone, String email)throws Exception{
        PessoaFisica pessoa = getPessoa(id);
        String sqlfisica = "UPDATE pessoa_fisica SET cpf=? where idpessoa = "+id;
        String sqlpessoa = "UPDATE pessoa SET nome=?, logradouro=?, cidade=?,"
                + "estado=?, telefone=?, email=? WHERE idpessoa= "+id;
        PreparedStatement ps = connection.getPrepared(sqlfisica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        if(cpf.equals("")){
           ps.setString(1, pessoa.getCpf());
        } else{
            ps.setString(1, cpf);
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
        connection.closeStatement(sqlfisica);
    }
    
    public void excluir(int id)throws Exception{
        String sqlfisica = "DELETE FROM pessoa_fisica WHERE idpessoa="+id;
        String sqlpessoa = "DELETE FROM pessoa WHERE idpessoa="+id;
        PreparedStatement ps = connection.getPrepared(sqlfisica);
        PreparedStatement ps1 = connection.getPrepared(sqlpessoa);
        ps.execute();
        ps1.execute();
        connection.closeConnection();
        //connection.closeResult(ps);
        connection.closeStatement(sqlfisica);
    }
    
}
