import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    
    private List<Livro> acervo = new ArrayList<>();

    private final int ANO_PUBLICACAO_MINIMO = 1400;

    public Livro adicionar(Livro novoLivro) throws Exception{
        
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título obrigatório: Você não informou o título do livro.");

        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty())
            throw new Exception("Autor obrigatório: Você não informou o nome do autor.");

        if (novoLivro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido: deve estar entre 1400 e o ano atual.");
            
        if (novoLivro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas inválido: o livro deve ter pelo menos uma página.");

        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(novoLivro.getTitulo()))
                throw new Exception("Livro duplicado: já existe um cadastro com este título.");
        }



        //if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
           // throw new Exception("Título inválido");
        
        acervo.add(novoLivro);
        return novoLivro;
    }

    public List<Livro> pesquisarporTitulo(String titulo){
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisarTodos(){
        return acervo;
    }

    public int removerPorTitulo(String titulo) {
        int contagem = 0;
        for (int i = 0; i < acervo.size(); i++) {
            if (acervo.get(i).getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                acervo.remove(i);
                contagem++;
                i--;
            }
        }
        return contagem;
    }
}