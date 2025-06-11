
import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caio
 */
public class CadastroBDTeste2 {
    
    public static void main(String[] args)throws Exception {
        
        Scanner scan = new Scanner(System.in);
        String escolha;

        do {
            System.out.println("==============================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==============================");

            escolha = scan.next();
            SequenceManager seq = new SequenceManager();

            switch (escolha) {

                // Incluir
                case "1":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        escolha = scan.next();
                        scan.nextLine();

                        switch (escolha.toUpperCase()) {
          
                            case "F":
                                System.out.println("Insira os dados... ");
                                System.out.print("Nome: ");
                                String nome = scan.nextLine();
                                System.out.print("Logradouro: ");
                                String logradouro = scan.nextLine();
                                System.out.print("Cidade: ");
                                String cidade = scan.nextLine();
                                System.out.print("Estado: ");
                                String estado = scan.nextLine();
                                System.out.print("Telefone: ");
                                String telefone = scan.nextLine();
                                System.out.print("Email: ");
                                String email = scan.nextLine();
                                System.out.print("CPF: ");
                                String cpf = scan.nextLine();
                                                                
                                PessoaFisica pessoaIncluir = new PessoaFisica(seq.getValue("seq_Pessoa"),nome, logradouro, 
                                cidade, estado, telefone,email,cpf);
                                PessoaFisicaDAO pessoaPF = new PessoaFisicaDAO();
                                pessoaPF.incluir(pessoaIncluir);

                                System.out.println("Inclusao realizada com sucesso!");
                                break;

                            case "J":
                                System.out.println("Insira os dados... ");
                                System.out.print("Nome: ");
                                String nomej = scan.nextLine();
                                System.out.print("Logradouro: ");
                                String logradouroj = scan.nextLine();
                                System.out.print("Cidade: ");
                                String cidadej = scan.nextLine();
                                System.out.print("Estado: ");
                                String estadoj = scan.nextLine();
                                System.out.print("Telefone: ");
                                String telefonej = scan.nextLine();
                                System.out.print("Email: ");
                                String emailj = scan.nextLine();
                                System.out.print("CNPJ: ");
                                String cnpj = scan.nextLine();
       
                                PessoaJuridica pessoaJIncluir = new PessoaJuridica(seq.getValue("seq_Pessoa"),nomej,
                                logradouroj,cidadej, estadoj, telefonej,emailj,cnpj);
                                PessoaJuridicaDAO pessoaPJ = new PessoaJuridicaDAO();
                                pessoaPJ.incluir(pessoaJIncluir);

                                System.out.println("Inclusao realizada com sucesso!");
                                break;
                                
                            case "M":                        
                                break;
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                    } while (!escolha.equalsIgnoreCase("M"));
                    break;

                // Alterar
                case "2":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        escolha = scan.next();
                        scan.nextLine();

                        switch (escolha.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();
                                scan.nextLine();
                                
                                PessoaFisica pessoaFisicaLocalizada = new PessoaFisicaDAO().getPessoa(idPessoaFisica);                               
                                PessoaFisicaDAO pessoaFisicaLocalizadaAlterar = new PessoaFisicaDAO();
                                //PessoaFisica pessoaFisicaLocalizada = pfRepo.obter(idPessoaFisica);

                                if (pessoaFisicaLocalizada != null) {
                                    pessoaFisicaLocalizada.exibir();

                                    System.out.println("Nome atual: " + pessoaFisicaLocalizada.getNome());
                                    System.out.print("Novo nome: ");
                                    String novoNome = scan.nextLine();
                                    
                                    System.out.println("Logradouro: " + pessoaFisicaLocalizada.getLogradouro());
                                    System.out.print("Novo Logradouro: ");
                                    String novoLogradouro = scan.nextLine();
                                    
                                    System.out.println("Cidade: " + pessoaFisicaLocalizada.getCidade());
                                    System.out.print("Nova Cidade: ");
                                    String novoCidade = scan.nextLine();
                                    
                                    System.out.println("Estado: " + pessoaFisicaLocalizada.getEstado());
                                    System.out.print("Novo Estado: ");
                                    String novoEstado = scan.nextLine();
                                    
                                    System.out.println("Telefone: " + pessoaFisicaLocalizada.getTelefone());
                                    System.out.print("Novo Telefone: ");
                                    String novoTelefone = scan.nextLine();
                                    
                                    System.out.println("Email: " + pessoaFisicaLocalizada.getEmail());
                                    System.out.print("Novo Email: ");
                                    String novoEmail = scan.nextLine();

                                    System.out.println("CPF atual: " + pessoaFisicaLocalizada.getCpf());
                                    System.out.print("Novo CPF: ");
                                    String novoCPF = scan.nextLine();

                                    pessoaFisicaLocalizadaAlterar.alterar( idPessoaFisica,novoCPF, novoNome, novoLogradouro, novoCidade,  
                                       novoEstado, novoTelefone,  novoEmail );

                                    System.out.println("Pessoa alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada! ");
                                break;

                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();
                                scan.nextLine();

                                PessoaJuridica pessoaJuridicaLocalizada = new PessoaJuridicaDAO().getPessoa(idPessoaJuridica);                               
                                PessoaJuridicaDAO pessoaJurdicaLocalizadaAlterar = new PessoaJuridicaDAO();

                                if (pessoaJuridicaLocalizada != null) {
                                    pessoaJuridicaLocalizada.exibir();

                                    System.out.println("Nome atual: " + pessoaJuridicaLocalizada.getNome());
                                    System.out.print("Novo nome: ");
                                    String novoNome = scan.nextLine();
                                    
                                    System.out.println("Logradouro: " + pessoaJuridicaLocalizada.getLogradouro());
                                    System.out.print("Novo Logradouro: ");
                                    String novoLogradouro = scan.nextLine();
                                    
                                    System.out.println("Cidade: " + pessoaJuridicaLocalizada.getCidade());
                                    System.out.print("Nova Cidade: ");
                                    String novoCidade = scan.nextLine();
                                    
                                    System.out.println("Estado: " + pessoaJuridicaLocalizada.getEstado());
                                    System.out.print("Novo Estado: ");
                                    String novoEstado = scan.nextLine();
                                    
                                    System.out.println("Telefone: " + pessoaJuridicaLocalizada.getTelefone());
                                    System.out.print("Novo Telefone: ");
                                    String novoTelefone = scan.nextLine();
                                    
                                    System.out.println("Email: " + pessoaJuridicaLocalizada.getEmail());
                                    System.out.print("Novo Email: ");
                                    String novoEmail = scan.nextLine();

                                    System.out.println("CNPJ atual: " + pessoaJuridicaLocalizada.getCnpj());
                                    System.out.print("Novo CNPJ: ");
                                    String novoCNPJ = scan.nextLine();

                                   pessoaJurdicaLocalizadaAlterar.alterar( idPessoaJuridica, novoCNPJ, novoNome, novoLogradouro, novoCidade,  
                                       novoEstado, novoTelefone,  novoEmail);

                                    System.out.println("Pessoa alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;
                                
                            case "M":                        
                                break;
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                    } while (!escolha.equalsIgnoreCase("M"));
                    break;

                // EXCLUIR
                case "3":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        escolha = scan.next();
                        scan.nextLine();

                        switch (escolha.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();

                                PessoaFisica pessoaFisicaLocalizada = new PessoaFisicaDAO().getPessoa(idPessoaFisica); 
                                PessoaFisicaDAO pessoaFisicaLocalizadaExcluir = new PessoaFisicaDAO();
                                
                                if (pessoaFisicaLocalizada != null) {
                                    pessoaFisicaLocalizada.exibir();
                                    pessoaFisicaLocalizadaExcluir.excluir(idPessoaFisica);

                                    System.out.println("Pessoa excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;


                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();

                                PessoaJuridica pessoaJuridicaLocalizada = new PessoaJuridicaDAO().getPessoa(idPessoaJuridica);                               
                                PessoaJuridicaDAO pessoaJurdicaLocalizadaExcluir = new PessoaJuridicaDAO();

                                if (pessoaJuridicaLocalizada != null) {
                                    pessoaJuridicaLocalizada.exibir();
                                    pessoaJurdicaLocalizadaExcluir.excluir(idPessoaJuridica);

                                    System.out.println("Pessoa excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;
                            
                            case "M":                        
                                break;

                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }

                    } while (!escolha.equalsIgnoreCase("M"));
                    break;

                // obter pelo Id
                case "4":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        escolha = scan.next();
                        scan.nextLine();

                        switch (escolha.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();

                                PessoaFisica pessoaFisicaLocalizada = new PessoaFisicaDAO().getPessoa(idPessoaFisica);

                                if (pessoaFisicaLocalizada != null) {
                                    System.out.println("Pessoa localizada!");
                                    pessoaFisicaLocalizada.exibir();
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;

                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();

                                PessoaJuridica pessoaJuridicaLocalizada = new PessoaJuridicaDAO().getPessoa(idPessoaJuridica);

                                if (pessoaJuridicaLocalizada != null) {
                                    System.out.println("Pessoa localizada!");
                                    pessoaJuridicaLocalizada.exibir();
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;

                            case "M":                        
                                break;    
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }

                    } while (!escolha.equalsIgnoreCase("M"));
                    break;

                //obterTodos 
                case "5":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        escolha = scan.next();
                        scan.nextLine();

                        switch (escolha.toUpperCase()) {

                            case "F":
                                System.out.println("Pessoas fisicas:");
                                PessoaFisicaDAO pessoasFisica = new PessoaFisicaDAO();
                                List<PessoaFisica> resultado = pessoasFisica.getPessoas();
                                 for (PessoaFisica pessoaFisica : resultado) {
                                    pessoaFisica.exibir();
                                 }       
                                break;
                                
                            case "J":
                                System.out.println("Pessoas juridicas:");    
                                PessoaJuridicaDAO pessoasJuridica = new PessoaJuridicaDAO();
                                List<PessoaJuridica> resultado2 = pessoasJuridica.getPessoas();
                                 for (PessoaJuridica pessoaJuridica : resultado2) {
                                    pessoaJuridica.exibir();
                                 } 
                                break;
                                
                            case "M":                        
                                break;    

                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }

                    } while (!escolha.equalsIgnoreCase("M"));
                    break;          
                case "0":
                    System.out.println("Sistema Finalizado com sucesso.");
                    break;
                   
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (!escolha.equals("0"));   
        scan.close();
     
    }
}
