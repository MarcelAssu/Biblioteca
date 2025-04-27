public final class LivroFisico extends Livro {
    
    private int numeroExemplares;
    private String dimensoes;

    @Override
    public String toString() {

        String descricao = super.toString();
        descricao += 
            "\n- Formato: " + getFormato() +
            "\n- Número de Exemplares: " + getNumeroExemplares() +
            "\n- Dimensões: " + getDimensoes() +
            "\n";
        return descricao;

    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }
    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
    public String getDimensoes() {
        return dimensoes;
    }
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public String getFormato() {
        return "Livro Físico";
    }
    
}