package biblioteca.app;

import biblioteca.excecao.ItemNaoEncontradoException;
import biblioteca.excecao.LivroNaoDisponivelException;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Revista;
import biblioteca.modelo.Usuario;
import biblioteca.servico.GerenciadorBiblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instancia o gerenciador principal da biblioteca.
        // Ele carregará os dados salvos automaticamente na inicialização.
        GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();
        // Cria um Scanner para ler a entrada do usuário no console.
        Scanner scanner = new Scanner(System.in);

        // Variável para armazenar a opção escolhida pelo usuário no menu.
        // Inicializada com -1 para garantir que o loop 'do-while' seja executado pelo menos uma vez.
        int opcao = -1;

        // Loop principal do menu interativo.
        do {
            // Exibe o menu de opções para o usuário.
            System.out.println("\n--- Sistema de Gerenciamento de Biblioteca ---");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Adicionar Revista");
            System.out.println("3. Adicionar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Registrar Devolução");
            System.out.println("6. Listar Livros Disponíveis");
            System.out.println("7. Listar Todos os Itens da Biblioteca"); // Inclui Livros e Revistas
            System.out.println("8. Listar Empréstimos Ativos");
            System.out.println("9. Listar Todos os Usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            // Bloco try-catch para tratamento de exceções de entrada e outras exceções.
            try {
                // Tenta ler a opção do usuário.
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha pendente após nextInt()

                // Estrutura switch-case para executar a ação com base na opção escolhida.
                switch (opcao) {
                    case 1: // Adicionar Livro
                        System.out.print("Título do Livro: ");
                        String tituloLivro = scanner.nextLine();
                        System.out.print("Autor do Livro: ");
                        String autorLivro = scanner.nextLine();
                        System.out.print("ISBN do Livro: ");
                        String isbnLivro = scanner.nextLine();
                        System.out.print("Ano de Publicação: ");
                        int anoLivro = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha
                        gerenciador.adicionarItem(new Livro(tituloLivro, autorLivro, isbnLivro, anoLivro));
                        System.out.println("Livro adicionado com sucesso!");
                        break;

                    case 2: // Adicionar Revista
                        System.out.print("Título da Revista: ");
                        String tituloRevista = scanner.nextLine();
                        System.out.print("ISSN da Revista: ");
                        String issnRevista = scanner.nextLine();
                        System.out.print("Número da Edição: ");
                        int numeroEdicao = scanner.nextInt();
                        System.out.print("Ano de Publicação: ");
                        int anoRevista = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha
                        gerenciador.adicionarItem(new Revista(tituloRevista, issnRevista, numeroEdicao, anoRevista));
                        System.out.println("Revista adicionada com sucesso!");
                        break;

                    case 3: // Adicionar Usuário
                        System.out.print("Nome do Usuário: ");
                        String nomeUsuario = scanner.nextLine();
                        System.out.print("ID do Usuário: ");
                        String idUsuario = scanner.nextLine();
                        gerenciador.adicionarUsuario(new Usuario(nomeUsuario, idUsuario));
                        System.out.println("Usuário adicionado com sucesso!");
                        break;

                    case 4: // Realizar Empréstimo
                        System.out.print("ISBN do Livro para empréstimo: ");
                        String isbnEmprestimo = scanner.nextLine();
                        System.out.print("ID do Usuário: ");
                        String idUsuarioEmprestimo = scanner.nextLine();
                        try {
                            gerenciador.realizarEmprestimo(isbnEmprestimo, idUsuarioEmprestimo);
                        } catch (LivroNaoDisponivelException | ItemNaoEncontradoException e) {
                            // Captura e exibe exceções específicas do negócio.
                            System.err.println("Erro ao realizar empréstimo: " + e.getMessage());
                        }
                        break;

                    case 5: // Registrar Devolução
                        System.out.print("ISBN do Livro para devolução: ");
                        String isbnDevolucao = scanner.nextLine();
                        System.out.print("ID do Usuário: ");
                        String idUsuarioDevolucao = scanner.nextLine();
                        try {
                            gerenciador.registrarDevolucao(isbnDevolucao, idUsuarioDevolucao);
                        } catch (ItemNaoEncontradoException e) {
                            // Captura e exibe exceções específicas do negócio.
                            System.err.println("Erro ao registrar devolução: " + e.getMessage());
                        }
                        break;

                    case 6: // Listar Livros Disponíveis
                        System.out.println("\n--- Livros Disponíveis ---");
                        if (gerenciador.listarItensDisponiveis().isEmpty()) {
                            System.out.println("Nenhum livro disponível no momento.");
                        } else {
                            gerenciador.listarItensDisponiveis().forEach(System.out::println);
                        }
                        break;

                    case 7: // Listar Todos os Itens da Biblioteca (Livros e Revistas)
                        System.out.println("\n--- Todos os Itens da Biblioteca ---");
                        if (gerenciador.listarTodosItens().isEmpty()) {
                            System.out.println("Nenhum item na biblioteca.");
                        } else {
                            // O polimorfismo é demonstrado aqui, chamando getDescricaoCompleta()
                            // que será implementado de forma diferente em Livro e Revista.
                            gerenciador.listarTodosItens().forEach(item -> System.out.println(item.getDescricaoCompleta()));
                        }
                        break;

                    case 8: // Listar Empréstimos Ativos
                        System.out.println("\n--- Empréstimos Ativos ---");
                        if (gerenciador.listarEmprestimosAtivos().isEmpty()) {
                            System.out.println("Nenhum empréstimo ativo no momento.");
                        } else {
                            gerenciador.listarEmprestimosAtivos().forEach(System.out::println);
                        }
                        break;

                    case 9: // Listar Todos os Usuários
                        System.out.println("\n--- Todos os Usuários ---");
                        if (gerenciador.listarTodosUsuarios().isEmpty()) {
                            System.out.println("Nenhum usuário cadastrado.");
                        } else {
                            gerenciador.listarTodosUsuarios().forEach(System.out::println);
                        }
                        break;

                    case 0: // Sair do programa
                        System.out.println("Saindo do sistema. Salvando dados...");
                        gerenciador.salvarDados(); // Salva os dados antes de sair.
                        System.out.println("Dados salvos. Até mais!");
                        break;

                    default: // Opção inválida
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                // Captura erro se o usuário digitar algo que não seja um número.
                System.err.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpa o buffer do scanner para evitar loop infinito.
                opcao = -1; // Define um valor inválido para continuar o loop.
            } catch (Exception e) {
                // Captura qualquer outra exceção inesperada.
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
                opcao = -1; // Define um valor inválido para continuar o loop.
            }

        } while (opcao != 0); // O loop continua enquanto a opção não for 0.

        scanner.close(); // Fecha o scanner para liberar recursos.
    }
}
