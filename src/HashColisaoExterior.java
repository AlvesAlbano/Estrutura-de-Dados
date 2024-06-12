public class HashColisaoExterior {

    public ListaDuplamenteEncadeada[] vetor;
    public int nElementos;

    public HashColisaoExterior(int capacidade) {
        this.vetor = new ListaDuplamenteEncadeada[capacidade];
        for (int i = 0; i < vetor.length; i++) {
            this.vetor[i] = new ListaDuplamenteEncadeada();
        }
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprime() {
        System.out.println("Chave\tValor");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(i + " -->\t");
            vetor[i].imprimirLista();
        }
    }

    private int funcaoHash(String elemento) {
        return Math.abs(elemento.hashCode()) % this.vetor.length;
    }

    public void insere(String elemento) {
        int endereco = funcaoHash(elemento);
        this.vetor[endereco].insereFinal(elemento);
        this.nElementos++;
    }

    public boolean remove(String elemento) {
        int endereco = funcaoHash(elemento);
        boolean removeu = this.vetor[endereco].removeElemento(elemento);

        if (removeu) this.nElementos--;

        return removeu;
    }

    public boolean contem(String elemento) {
        int endereco = funcaoHash(elemento);
        return this.vetor[endereco].contem(elemento) != null;
    }
}

