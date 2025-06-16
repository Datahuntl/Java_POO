package biblioteca.servico;

import biblioteca.excecao.ItemNaoEncontradoException;
import biblioteca.excecao.LivroNaoDisponivelException;
import biblioteca.io.GerenciadorTexto;
import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.ItemBiblioteca;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GerenciadorBiblioteca {
    private List<ItemBiblioteca> itensBiblioteca;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    // Uma única instância do GerenciadorTexto
    private GerenciadorTexto gerenciadorTexto;

    public GerenciadorBiblioteca() {
        this.itensBiblioteca = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();

        this.gerenciadorTexto = new GerenciadorTexto(); // Instancia o gerenciador de texto

        carregarDados();
    }

    private void carregarDados() {
        try {
            // 1. Carrega itens e usuários primeiro
            this.itensBiblioteca = gerenciadorTexto.carregarItens();
            this.usuarios = gerenciadorTexto.carregarUsuarios();
            List<Emprestimo> emprestimosTemporarios = gerenciadorTexto.carregarEmprestimos();

            // 2. Liga os Empréstimos com os objetos reais
            List<Emprestimo> emprestimosReligados = new ArrayList<>();
            for (Emprestimo empTemp : emprestimosTemporarios) {
                // Ao carregar de texto, Livro e Usuario dentro de Emprestimo são apenas placeholders com IDs/ISBNs.

                String isbnLivro = empTemp.getLivro().getIdentificadorUnico();
                String idUsuario = empTemp.getUsuario().getIdUsuario();

                Optional<ItemBiblioteca> livroRealOpc = buscarItemPorIdentificador(isbnLivro);
                Optional<Usuario> usuarioRealOpc = buscarUsuarioPorId(idUsuario);

                if (livroRealOpc.isPresent() && usuarioRealOpc.isPresent() && (livroRealOpc.get() instanceof Livro)) {
                    Livro livroReal = (Livro) livroRealOpc.get();
                    Usuario usuarioReal = usuarioRealOpc.get();

                    // Cria um novo objeto Emprestimo com os objetos reais
                    Emprestimo emprestimoReal = new Emprestimo(
                            livroReal,
                            usuarioReal,
                            empTemp.getDataEmprestimo(),
                            empTemp.getDataDevolucaoPrevista()
                    );
                    emprestimoReal.setDataDevolucaoReal(empTemp.getDataDevolucaoReal());
                    emprestimosReligados.add(emprestimoReal);

                    // Garante que o estado de disponibilidade do livro seja consistente
                    if (emprestimoReal.estaAberto()) {
                        livroReal.setDisponivel(false);
                    } else {
                        // Se o empréstimo não está aberto, o livro deveria estar disponível,
                        // a menos que haja outro empréstimo ativo para ele.
                        boolean aindaEmprestado = emprestimosReligados.stream() // Verifica entre os já religados
                                .filter(e -> e.getLivro().getIdentificadorUnico().equals(livroReal.getIdentificadorUnico()))
                                .anyMatch(Emprestimo::estaAberto);
                        livroReal.setDisponivel(!aindaEmprestado);
                    }

                } else {
                    System.err.println("Aviso: Empréstimo com ISBN " + isbnLivro +
                            " ou ID de Usuário " + idUsuario + " não pôde ser re-ligado. Ignorando este empréstimo.");
                }
            }
            this.emprestimos.addAll(emprestimosReligados); // Adiciona os empréstimos re-ligados

        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            // Se houver um erro de IO (arquivo não encontrado, etc.), as listas permanecem vazias.
        }
    }


    public void salvarDados() {
        try {
            gerenciadorTexto.salvarTodosDados(itensBiblioteca, usuarios, emprestimos); // Chama o novo metodo único
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // Métodos de negócio (adicionar, buscar, emprestar, devolver, listar)

    public void adicionarItem(ItemBiblioteca item) {
        this.itensBiblioteca.add(item);
    }

    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public Optional<ItemBiblioteca> buscarItemPorIdentificador(String identificador) {
        return itensBiblioteca.stream()
                .filter(item -> item.getIdentificadorUnico().equalsIgnoreCase(identificador))
                .findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorId(String idUsuario) {
        return usuarios.stream()
                .filter(usuario -> usuario.getIdUsuario().equalsIgnoreCase(idUsuario))
                .findFirst();
    }

    public void realizarEmprestimo(String isbnLivro, String idUsuario) throws LivroNaoDisponivelException, ItemNaoEncontradoException {
        Optional<ItemBiblioteca> itemOpcional = buscarItemPorIdentificador(isbnLivro);
        if (!itemOpcional.isPresent()) {
            throw new ItemNaoEncontradoException("Livro com ISBN " + isbnLivro + " não encontrado.");
        }

        if (!(itemOpcional.get() instanceof Livro)) {
            throw new ItemNaoEncontradoException("O item com identificador " + isbnLivro + " não é um livro e não pode ser emprestado.");
        }

        Livro livro = (Livro) itemOpcional.get();

        if (!livro.isDisponivel()) {
            throw new LivroNaoDisponivelException("O livro '" + livro.getTitulo() + "' não está disponível para empréstimo.");
        }

        Optional<Usuario> usuarioOpcional = buscarUsuarioPorId(idUsuario);
        if (!usuarioOpcional.isPresent()) {
            throw new ItemNaoEncontradoException("Usuário com ID " + idUsuario + " não encontrado.");
        }
        Usuario usuario = usuarioOpcional.get();

        livro.setDisponivel(false);
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(7);
        Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista);
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo realizado: " + emprestimo);
    }

    public void registrarDevolucao(String isbnLivro, String idUsuario) throws ItemNaoEncontradoException {
        Optional<ItemBiblioteca> itemOpcional = buscarItemPorIdentificador(isbnLivro);
        if (!itemOpcional.isPresent()) {
            throw new ItemNaoEncontradoException("Livro com ISBN " + isbnLivro + " não encontrado.");
        }

        if (!(itemOpcional.get() instanceof Livro)) {
            throw new ItemNaoEncontradoException("O item com identificador " + isbnLivro + " não é um livro.");
        }
        Livro livro = (Livro) itemOpcional.get();

        Optional<Emprestimo> emprestimoAberto = emprestimos.stream()
                .filter(e -> e.getLivro().getIdentificadorUnico().equalsIgnoreCase(isbnLivro) &&
                        e.getUsuario().getIdUsuario().equalsIgnoreCase(idUsuario) &&
                        e.estaAberto())
                .findFirst();

        if (emprestimoAberto.isPresent()) {
            Emprestimo emprestimo = emprestimoAberto.get();
            emprestimo.setDataDevolucaoReal(LocalDate.now());
            livro.setDisponivel(true);
            System.out.println("Devolução registrada para: " + emprestimo);
        } else {
            throw new ItemNaoEncontradoException("Nenhum empréstimo em aberto encontrado para o livro " + isbnLivro + " e usuário " + idUsuario + ".");
        }
    }

    public List<ItemBiblioteca> listarItensDisponiveis() {
        return itensBiblioteca.stream()
                .filter(item -> item instanceof Livro && ((Livro) item).isDisponivel())
                .collect(Collectors.toList());
    }

    public List<ItemBiblioteca> listarTodosItens() {
        return new ArrayList<>(itensBiblioteca);
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimos.stream()
                .filter(Emprestimo::estaAberto)
                .collect(Collectors.toList());
    }

    public List<Usuario> listarTodosUsuarios() {
        return new ArrayList<>(usuarios);
    }
}
