import java.time.LocalDate;

public abstract class Livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas; 

    public abstract String getFormato();

    public final int calcularTempoPublicacao(){
        int anoAtual = LocalDate.now().getYear();
        return anoAtual - this.anoPublicacao;
    }
    
    @Override
    public String toString() {

        String descricao = 
            "\n- Titulo: " + getTitulo() +
            "\n- Autor: " + getAutor() +
            "\n- Ano: " + getAnoPublicacao() +
            "\n- Número de Páginas: " + getNumeroPaginas();
        return descricao;

    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    
}