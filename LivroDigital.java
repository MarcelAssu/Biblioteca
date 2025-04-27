public final class LivroDigital extends Livro {
    
    private double tamanhoArquivo;
    private String formatoArquivo;

    @Override
    public String toString() {

        String descricao = super.toString();
        descricao += 
            "\n- Formato: " + getFormato() +
            "\n- Tipo do Arquivo: " + getFormatoArquivo() +
            "\n- Tamanho do Arquivo: " + getTamanhoArquivo() + " MB" +
            "\n";
        return descricao;

    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
    public String getFormatoArquivo() {
        return formatoArquivo;
    }
    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    @Override
    public String getFormato() {
        return "Livro Digital";
    }

    
}