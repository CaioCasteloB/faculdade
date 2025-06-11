/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;
import java.io.Serializable;

/**
 *
 * @author caio
 */
public class PessoaFisica extends Pessoa implements Serializable {

    private String cpf;
    
    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado,
            String telefone, String email, String cpf){
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

     public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void exibir(){
        System.out.print("id: "+ getId()+ "\n" + "Nome: " + getNome() + "\n" + 
        "logradouro: "+getLogradouro()+"\n"+"cidade: "+getCidade()+"\n"+
        "estado: "+getEstado()+"\n" + "telefone: " +  getTelefone() + "\n"+ "email: " + getEmail() + "\n"+
        "CPF: "+this.cpf + "\n");
    }
}
