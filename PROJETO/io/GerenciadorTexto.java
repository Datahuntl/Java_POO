package biblioteca.io;

import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.ItemBiblioteca;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Revista;
import biblioteca.modelo.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTexto {

    private static final String ARQUIVO_ITENS = "itens.txt";
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_EMPRESTIMOS = "emprestimos.txt";

    // --- Métodos de Salvar ---

    public void salvarTodosDados(List<ItemBiblioteca> itens, List<Usuario> usuarios, List<Emprestimo> emprestimos) throws IOException {
        salvarItens(itens);
        salvarUsuarios(usuarios);
        salvarEmprestimos(emprestimos);
        System.out.println("Todos os dados salvos com sucesso.");
    }

    private void salvarItens(List<ItemBiblioteca> itens) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_ITENS)))) {
            for (ItemBiblioteca item : itens) {
                writer.write(converterItemParaString(item));
                writer.newLine();
            }
            System.out.println("Itens salvos em " + ARQUIVO_ITENS);
        }
    }

    private void salvarUsuarios(List<Usuario> usuarios) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_USUARIOS)))) {
            for (Usuario usuario : usuarios) {
                writer.write(converterUsuarioParaString(usuario));
                writer.newLine();
            }
            System.out.println("Usuários salvos em " + ARQUIVO_USUARIOS);
        }
    }

    private void salvarEmprestimos(List<Emprestimo> emprestimos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_EMPRESTIMOS)))) {
            for (Emprestimo emprestimo : emprestimos) {
                writer.write(converterEmprestimoParaString(emprestimo));
                writer.newLine();
            }
            System.out.println("Empréstimos salvos em " + ARQUIVO_EMPRESTIMOS);
        }
    }

    // --- Métodos de Carregar ---

    public List<ItemBiblioteca> carregarItens() throws IOException {
        List<ItemBiblioteca> itensCarregados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVO_ITENS)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                try {
                    itensCarregados.add(converterStringParaItem(linha));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao carregar linha de item: '" + linha + "' - " + e.getMessage());
                }
            }
            System.out.println("Itens carregados de " + ARQUIVO_ITENS);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + ARQUIVO_ITENS + " não encontrado. Retornando lista vazia.");
        }
        return itensCarregados;
    }

    public List<Usuario> carregarUsuarios() throws IOException {
        List<Usuario> usuariosCarregados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVO_USUARIOS)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                try {
                    usuariosCarregados.add(converterStringParaUsuario(linha));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao carregar linha de usuário: '" + linha + "' - " + e.getMessage());
                }
            }
            System.out.println("Usuários carregados de " + ARQUIVO_USUARIOS);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + ARQUIVO_USUARIOS + " não encontrado. Retornando lista vazia.");
        }
        return usuariosCarregados;
    }

    public List<Emprestimo> carregarEmprestimos() throws IOException {
        List<Emprestimo> emprestimosCarregados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVO_EMPRESTIMOS)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                try {
                    emprestimosCarregados.add(converterStringParaEmprestimo(linha));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao carregar linha de empréstimo: '" + linha + "' - " + e.getMessage());
                }
            }
            System.out.println("Empréstimos carregados de " + ARQUIVO_EMPRESTIMOS);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + ARQUIVO_EMPRESTIMOS + " não encontrado. Retornando lista vazia.");
        }
        return emprestimosCarregados;
    }

    // --- Métodos de Conversão Objeto <-> String ---

    private String converterItemParaString(ItemBiblioteca item) {
        if (item instanceof Livro) {
            Livro livro = (Livro) item;
            return String.format("LIVRO;%s;%s;%s;%d;%b",
                    escaparString(livro.getTitulo()),
                    escaparString(livro.getAutor()),
                    escaparString(livro.getIdentificadorUnico()),
                    livro.getAnoPublicacao(),
                    livro.isDisponivel());
        } else if (item instanceof Revista) {
            Revista revista = (Revista) item;
            return String.format("REVISTA;%s;%s;%d;%d",
                    escaparString(revista.getTitulo()),
                    escaparString(revista.getIdentificadorUnico()),
                    revista.getNumeroEdicao(),
                    revista.getAnoPublicacao());
        }
        throw new IllegalArgumentException("Tipo de item desconhecido: " + item.getClass().getName());
    }

    private ItemBiblioteca converterStringParaItem(String linha) {
        String[] partes = linha.split(";", -1);
        String tipo = partes[0];

        switch (tipo) {
            case "LIVRO":
                if (partes.length < 6) throw new IllegalArgumentException("Formato de linha de Livro inválido: " + linha);
                String tituloLivro = desescaparString(partes[1]);
                String autorLivro = desescaparString(partes[2]);
                String isbnLivro = desescaparString(partes[3]);
                int anoLivro = Integer.parseInt(partes[4]);
                boolean disponivelLivro = Boolean.parseBoolean(partes[5]);
                Livro livro = new Livro(tituloLivro, autorLivro, isbnLivro, anoLivro);
                livro.setDisponivel(disponivelLivro);
                return livro;
            case "REVISTA":
                if (partes.length < 5) throw new IllegalArgumentException("Formato de linha de Revista inválido: " + linha);
                String tituloRevista = desescaparString(partes[1]);
                String issnRevista = desescaparString(partes[2]);
                int numeroEdicaoRevista = Integer.parseInt(partes[3]);
                int anoRevista = Integer.parseInt(partes[4]);
                return new Revista(tituloRevista, issnRevista, numeroEdicaoRevista, anoRevista);
            default:
                throw new IllegalArgumentException("Tipo de item desconhecido na linha: " + linha);
        }
    }

    private String converterUsuarioParaString(Usuario usuario) {
        // Formato: nome;idUsuario
        return String.format("%s;%s",
                escaparString(usuario.getNome()),
                escaparString(usuario.getIdUsuario()));
    }

    private Usuario converterStringParaUsuario(String linha) {
        String[] partes = linha.split(";", -1);
        if (partes.length < 2) throw new IllegalArgumentException("Formato de linha de Usuário inválido: " + linha);
        String nomeUsuario = desescaparString(partes[0]);
        String idUsuario = desescaparString(partes[1]);
        return new Usuario(nomeUsuario, idUsuario);
    }

    private String converterEmprestimoParaString(Emprestimo emprestimo) {
        // Formato: isbnLivro;idUsuario;dataEmprestimo;dataDevolucaoPrevista;dataDevolucaoReal(ou NULL)
        String dataDevolucaoRealStr = (emprestimo.getDataDevolucaoReal() != null) ?
                emprestimo.getDataDevolucaoReal().toString() : "NULL";
        return String.format("%s;%s;%s;%s;%s",
                escaparString(emprestimo.getLivro().getIdentificadorUnico()),
                escaparString(emprestimo.getUsuario().getIdUsuario()),
                emprestimo.getDataEmprestimo().toString(),
                emprestimo.getDataDevolucaoPrevista().toString(),
                dataDevolucaoRealStr);
    }

    private Emprestimo converterStringParaEmprestimo(String linha) {
        String[] partes = linha.split(";", -1);
        if (partes.length < 5) throw new IllegalArgumentException("Formato de linha de Empréstimo inválido: " + linha);

        String isbnLivroEmprestimo = desescaparString(partes[0]);
        String idUsuarioEmprestimo = desescaparString(partes[1]);
        LocalDate dataEmprestimo = LocalDate.parse(partes[2]);
        LocalDate dataDevolucaoPrevista = LocalDate.parse(partes[3]);
        LocalDate dataDevolucaoReal = "NULL".equals(partes[4]) ? null : LocalDate.parse(partes[4]);

        // Placeholders para Livro e Usuário. A "re-ligação" ocorrerá no GerenciadorBiblioteca.
        Livro livroPlaceholder = new Livro("", "", isbnLivroEmprestimo, 0);
        Usuario usuarioPlaceholder = new Usuario("", idUsuarioEmprestimo);

        Emprestimo emprestimo = new Emprestimo(livroPlaceholder, usuarioPlaceholder, dataEmprestimo, dataDevolucaoPrevista);
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
        return emprestimo;
    }

    // Métodos para escapar e desescapar o delimitador (ponto e vírgula) dentro dos dados.
    // Usamos &#semi; para ; e &#nl; para \n.
    private String escaparString(String texto) {
        if (texto == null) return "";
        return texto.replace(";", "&#semi;").replace("\n", "&#nl;");
    }

    private String desescaparString(String textoEscapado) {
        if (textoEscapado == null) return "";
        return textoEscapado.replace("&#semi;", ";").replace("&#nl;", "\n");
    }
}
