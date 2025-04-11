package cadastropoo;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;
import java.util.Scanner;
import java.io.IOException;

public class CadastroPOO {
    private static final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma alternativa: ");
            processarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n==========================\n");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo Id");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa\n");
        System.out.println("\n==========================\n");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> incluirPessoa();
            case 2 -> alterarPessoa();
            case 3 -> excluirPessoa();
            case 4 -> buscarPorId();
            case 5 -> exibirTodos();
            case 6 -> persistirDados();
            case 7 -> recuperarDados();
            case 0 -> System.out.println("Programa finalizado.");
            default -> System.out.println("Alternativa inválida!");
        }
    }

    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static char lerTipoPessoa() {
        System.out.print("\nF - Pessoa Fisica\nJ - Pessoa Juridica\nDigite a alternativa que deseja: ");
        return scanner.nextLine().toUpperCase().charAt(0);
    }

    private static void incluirPessoa() {
        char tipo = lerTipoPessoa();
        int id = lerInt("Digite o ID: ");
        String nome = lerString("Nome: ");

        if (tipo == 'F') {
            String cpf = lerString("CPF: ");
            int idade = lerInt("Idade: ");
            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
            System.out.println("Pessoa física cadastrada com sucesso!");
        } else {
            String cnpj = lerString("CNPJ: ");
            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
            System.out.println("Pessoa juridica cadastrada com sucesso!");
        }
    }

    private static void alterarPessoa() {
        char tipo = lerTipoPessoa();
        int id = lerInt("Digite o ID para alteração: ");

        if (tipo == 'F') {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.println("Dados atuais:");
                pf.exibir();
                pf.setNome(lerString("Novo nome: "));
                pf.setCpf(lerString("Novo CPF: "));
                pf.setIdade(lerInt("Nova idade: "));
                repoFisica.alterar(pf);
                System.out.println("Pessoa física alterada com sucesso!");
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        } else {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.println("Dados atuais:");
                pj.exibir();
                pj.setNome(lerString("Novo nome: "));
                pj.setCnpj(lerString("Novo CNPJ: "));
                repoJuridica.alterar(pj);
                System.out.println("Pessoa juridica alterada com sucesso!");
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        }
    }

    private static void excluirPessoa() {
        char tipo = lerTipoPessoa();
        int id = lerInt("Digite o ID para exclusao: ");

        if (tipo == 'F') {
            if (repoFisica.excluir(id)) {
                System.out.println("Pessoa fisica excluida com sucesso!");
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        } else {
            if (repoJuridica.excluir(id)) {
                System.out.println("Pessoa juridica excluida com sucesso!");
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        }
    }

    private static void buscarPorId() {
        char tipo = lerTipoPessoa();
        int id = lerInt("Digite o ID para busca: ");

        if (tipo == 'F') {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        } else {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa nao encontrada!");
            }
        }
    }

    private static void exibirTodos() {
        char tipo = lerTipoPessoa();

        if (tipo == 'F') {
            System.out.println("\nLista de todas as pessoas fisicas:\n");
            for (PessoaFisica pf : repoFisica.obterTodos()) {
                pf.exibir();
                System.out.println("------------------");
            }
        } else {
            System.out.println("\nLista de todas as pessoas juridicas:\n");
            for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                pj.exibir();
                System.out.println("------------------");
            }
        }
    }

    private static void persistirDados() {
        try {
            String prefixo = lerString("Digite o prefixo para os arquivos: ");
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        try {
            String prefixo = lerString("Digite o prefixo dos arquivos: ");
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
    }
}