import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblio = new Biblioteca();
    public static void main(String[] args) {

        String menu = """

                =========== BIBLIOTECA ============

                Escolha uma das opções abaixo:
                1 - Adicionar Novo livro
                2 - Pesquisar Livro por Título
                3 - Listar todos os Livros
                4 - Remover Livro por Título
                5 - Listar Livros por Ano
                0 - Sair
                """;
        
        System.out.println("=========== BIBLIOTECA CARREGANDO============");
        
        Scanner lerTeclado = new Scanner(System.in);
        int opcao;

        do {

            limparTelaTempo(3000);
            
            opcao = inputNumerico(lerTeclado, menu);
            
            switch (opcao) {
                case 1:
                    adicionar(lerTeclado);
                    break;
                
                case 2:
                    pesquisarPorTitulo(lerTeclado);
                    break;

                case 3:
                    pesquisarTodos(lerTeclado);
                    break;

                case 4:
                    removerPorTitulo(lerTeclado);
                    break;
                
                case 5:
                    listarLivrosPorAno(lerTeclado);
                    break;
                
                case 0:
                    System.out.println("\nEncerrando o Programa ...");
                    break;
            
                default:
                System.out.println("\nOPÇÃO INVÁLIDA");
                    break;
            }
            
        } while (opcao != 0);
    }


    private static void adicionar(Scanner lerTeclado){

        limparTela();

        System.out.println("\n---------CADASTRANDO UM LIVRO---------\n");
        
        System.out.println("\nDigite o Título do Livro: ");
        String titulo = lerTeclado.nextLine();

        System.out.println("\nDigite o Autor do Livro: ");
        String autor = lerTeclado.nextLine();

        int anoPublicacao = inputNumerico(lerTeclado, "\nDigite o Ano da Publicação: ");
        int numeroPaginas = inputNumerico(lerTeclado, "\nDigite o número de Páginas: ");

        Livro novoLivro;

        int tipoLivro = inputNumerico(lerTeclado, "\nQual o tipo do Livro: 1 -  FÍSICO,  2 DIGITAL");

        if (tipoLivro == 1){
            novoLivro = new LivroFisico();

            System.out.println("\nDigite as dimensões do Livro: ");
            String dimensoes = lerTeclado.nextLine();
            ((LivroFisico) novoLivro).setDimensoes(dimensoes);

            int numeroExemplares = inputNumerico(lerTeclado, "\nDigite o número de Exemplares: ");
            ((LivroFisico) novoLivro).setNumeroExemplares(numeroExemplares);
        }
        else{
            novoLivro = new LivroDigital();

            System.out.println("\nDigite o formato do Arquivo: ");
            String formatoArquivo = lerTeclado.nextLine();
            ((LivroDigital) novoLivro).setFormatoArquivo(formatoArquivo);

            System.out.println("\nDigite o Tamanho do Arquivo (em MB): ");
            double tamanhoArquivo = lerTeclado.nextDouble();
            lerTeclado.nextLine();
            ((LivroDigital) novoLivro).setTamanhoArquivo(tamanhoArquivo);
        }

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setNumeroPaginas(numeroPaginas);
        novoLivro.setAnoPublicacao(anoPublicacao);
        limparTela();

        try {
            biblio.adicionar(novoLivro);
            System.out.println("\nLivro Adicionado com Sucesso!");

        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }


    public static void pesquisarTodos(Scanner lerTeclado){

        limparTela();
        List<Livro> livros = biblio.pesquisarTodos();
        if(livros.isEmpty()) {
            System.out.println("\nNenhum Livro Cadastrado! ");
        } 
        else {

            System.out.println("\n---------LIVROS CADASTRADOS---------\n");

            for(Livro livro : livros){

                System.out.print(livro.toString());
                System.out.println("- Tempo desde sua publicação: " + livro.calcularTempoPublicacao() + " anos");
                System.out.println("\n-------------------------------------");
            }
            pausar(lerTeclado);
            limparTela();

        }
    }


    private static void pesquisarPorTitulo(Scanner lerTeclado) {

        limparTela();

        System.out.println("\nDigite o título para pesquisa:");
        String tituloPesquisa = lerTeclado.nextLine();
        List<Livro> livrosEncontrados = biblio.pesquisarporTitulo(tituloPesquisa);
    
        limparTela();

        if (livrosEncontrados.isEmpty()) {
            System.out.println("\nNenhum livro encontrado com esse título.");
        } 
        else {

            System.out.println("\n---------LIVROS ENCONTRADOS---------\n");

            for (Livro livro : livrosEncontrados) {

                System.out.print(livro.toString());
                System.out.println("- Tempo desde sua publicação: " + livro.calcularTempoPublicacao() + " anos");
                System.out.println("\n-------------------------------------");
            }
        }
        pausar(lerTeclado);
        limparTela();
    }


    private static int inputNumerico(Scanner lerTeclado, String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("\nErro. Por favor, informe um NÚMERO VÁLIDO");
            }
        } while (!entradaValida);
        return valor;
    }


    private static void removerPorTitulo(Scanner lerTeclado) {

        limparTela();

        System.out.println("\nDigite o título para remoção: ");
        String titulo = lerTeclado.nextLine();
        int livrosRemovidos = biblio.removerPorTitulo(titulo);

        limparTela();

        if (livrosRemovidos > 0) {
            System.out.println("\n" + livrosRemovidos + " livro(s) removido(s) com sucesso.");
        } else {
            System.out.println("\nNenhum livro encontrado para remoção.");
        }

        pausar(lerTeclado);
        limparTela();
    }


    private static void listarLivrosPorAno(Scanner lerTeclado) {

        limparTela();

        int anoPesquisa = inputNumerico(lerTeclado, "\nDigite o ano de publicação:");

        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : biblio.pesquisarTodos()) {
            if (livro.getAnoPublicacao() == anoPesquisa) {
                livrosEncontrados.add(livro);
            }
        }

        limparTela();

        if (livrosEncontrados.isEmpty()) {
            System.out.println("\nNenhum livro encontrado publicado no ano " + anoPesquisa);
        } else {

            System.out.println("\n---------LIVROS ENCONTRADOS---------\n");

            for (Livro livro : livrosEncontrados) {
                System.out.print(livro.toString());
                System.out.println("- Tempo desde sua publicação: " + livro.calcularTempoPublicacao() + " anos");
                System.out.println("\n-------------------------------------");
            }
        }

        pausar(lerTeclado);
        limparTela();
    }

    private static void limparTela(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void limparTelaTempo(int tempoEmMilissegundos) {
        try {
            Thread.sleep(tempoEmMilissegundos); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
    }


    private static void pausar(Scanner lerTeclado) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        lerTeclado.nextLine();
    }

    
}

