import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBinariaBusca {
    class Nodo {

        public String elemento;
        public Nodo esquerdo;
        public Nodo direito;
        public ListaDuplamenteEncadeada linhasLista;

        public Nodo(String elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
            this.linhasLista = new ListaDuplamenteEncadeada();
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void imprimeEmLargura() {

        FilaDinamica<Nodo> fila = new FilaDinamica<Nodo>();

        fila.enfileira(this.raiz);
        while (!fila.estaVazia()) {

            Nodo cursor = fila.desenfileira();

            System.out.print(cursor.elemento + " ");

            if (cursor.esquerdo != null) {
                fila.enfileira(cursor.esquerdo);
            }

            if (cursor.direito != null) {
                fila.enfileira(cursor.direito);
            }
        }
        System.out.println();
    }

    public void imprimePreOrdem() {
        this.preOrdem(this.raiz);
        System.out.println();
    }

    public void imprimePosOrdem() {
        this.posOrdem(this.raiz);
        System.out.println();
    }

    public void imprimeEmOrdem() {
        this.emOrdem(this.raiz);
        System.out.println();
    }

    public void preOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        System.out.print(nodo.elemento + " ");
        this.preOrdem(nodo.esquerdo);
        this.preOrdem(nodo.direito);
    }

    public void posOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.posOrdem(nodo.esquerdo);
        this.posOrdem(nodo.direito);
        System.out.print(nodo.elemento + " ");
    }

    public void emOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.emOrdem(nodo.esquerdo);
        System.out.print(nodo.elemento + " ");
        this.emOrdem(nodo.direito);
    }

    public void insere(String elemento) {
        this.insere(elemento, this.raiz);
    }

    public void insere(String elemento, Nodo nodo) {

        Nodo novo = new Nodo(elemento);

        if (nodo == null) {
            this.raiz = novo;
            this.nElementos++;
            return;
        }

        if (elemento.compareTo(nodo.elemento) < 0) {
            if (nodo.esquerdo == null) {
                nodo.esquerdo = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(elemento, nodo.esquerdo);
            }
        } else if (elemento.compareTo(nodo.elemento) > 0) {
            if (nodo.direito == null) {
                nodo.direito = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(elemento, nodo.direito);
            }
        }
    }

    public void adicionarRemissivo(String elemento, int linha) {
        adicionarRemissivo(this.raiz, elemento, linha);
    }

    private void adicionarRemissivo(Nodo nodo, String elemento, int linha) {
        if (nodo == null) {
            return;
        }
        if (elemento.equals(nodo.elemento)) {
            nodo.linhasLista.insereFinal(Integer.toString(linha));
        } else if (elemento.compareTo(nodo.elemento) < 0) {
            adicionarRemissivo(nodo.esquerdo, elemento, linha);
        } else {
            adicionarRemissivo(nodo.direito, elemento, linha);
        }
    }

    public void imprimirRemissivo(String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            imprimirRemissivo(this.raiz, writer);
        }
    }
    private void imprimirRemissivo(Nodo nodo, BufferedWriter writer) throws IOException {
        if (nodo != null) {
            imprimirRemissivo(nodo.esquerdo, writer);
            writer.write(nodo.elemento + " ");
            nodo.linhasLista.imprimirLista(writer);
            imprimirRemissivo(nodo.direito, writer);
        }
    }


    private Nodo maiorElemento(Nodo nodo) {
        while (nodo.direito != null) {
            nodo = nodo.direito;
        }
        return nodo;
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    public boolean remove(String elemento) {
        return this.remove(elemento, this.raiz) != null;
    }

    private Nodo remove(String elemento, Nodo nodo) {

        if (nodo == null) {
            System.out.println("Valor não encontrado");
            return null;
        }

        if (elemento.compareTo(nodo.elemento) < 0) {
            nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
        } else if (elemento.compareTo(nodo.elemento) > 0) {
            nodo.direito = this.remove(elemento, nodo.direito);
        } else {
            if (nodo.esquerdo == null) {
                this.nElementos--;
                return nodo.direito;
            } else if (nodo.direito == null) {
                this.nElementos--;
                return nodo.esquerdo;
            } else {
                Nodo substituto = this.menorElemento(nodo.direito);
                nodo.elemento = substituto.elemento;
                this.remove(substituto.elemento, nodo.direito);
            }
        }

        return nodo;
    }

    public boolean busca(String elemento) {
        return this.busca(elemento, this.raiz);
    }

    public boolean busca(String elemento, Nodo nodo) {

        if (nodo == null) {
            return false;
        }

        if (elemento.compareTo(nodo.elemento) < 0) {
            return this.busca(elemento, nodo.esquerdo);
        } else if (elemento.compareTo(nodo.elemento) > 0) {
            return this.busca(elemento, nodo.direito);
        } else {
            return true;
        }
    }

    private int altura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
        int alturaDireita = this.altura(nodo.direito) + 1;

        return Math.max(alturaEsquerda, alturaDireita);
    }

    public int altura() {
        return this.altura(this.raiz);
    }
}