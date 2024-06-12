import java.util.NoSuchElementException;

public class ListaDuplamenteEncadeada {

    private static class Nodo {
        public String valor;
        public Nodo proximo;
        public Nodo anterior;

        public Nodo(String valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private Nodo primeiro;
    private Nodo ultimo;
    private int nElementos;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.nElementos = 0;
    }

    public void insereInicio(String valor) {
        Nodo novoNodo = new Nodo(valor);
        if (primeiro == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            novoNodo.proximo = primeiro;
            primeiro.anterior = novoNodo;
            primeiro = novoNodo;
        }
        nElementos++;
    }

    public void removeInicio() {
        if (primeiro == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            primeiro = primeiro.proximo;
            if (primeiro != null)
                primeiro.anterior = null;
            nElementos--;
            if (nElementos == 0) {
                ultimo = null;
            }
        }
    }

    public void insereFinal(String valor) {
        Nodo novoNodo = new Nodo(valor);
        if (ultimo == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            novoNodo.anterior = ultimo;
            ultimo = novoNodo;
        }
        nElementos++;
    }

    public void removeFinal() {
        if (ultimo == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            if (primeiro == ultimo) {
                primeiro = null;
                ultimo = null;
            } else {
                ultimo = ultimo.anterior;
                ultimo.proximo = null;
            }
            nElementos--;
        }
    }

    public void inserePosicao(String valor, int posicao) {
        if (posicao < 0 || posicao > nElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            insereInicio(valor);
        } else if (posicao == nElementos) {
            insereFinal(valor);
        } else {
            Nodo novoNodo = new Nodo(valor);
            Nodo atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.proximo;
            }
            novoNodo.proximo = atual.proximo;
            novoNodo.anterior = atual;
            atual.proximo.anterior = novoNodo;
            atual.proximo = novoNodo;
            nElementos++;
        }
    }

    public void removePosicao(int posicao) {
        if (posicao < 0 || posicao >= nElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            removeInicio();
        } else if (posicao == nElementos - 1) {
            removeFinal();
        } else {
            Nodo atual = primeiro;
            for (int i = 0; i < posicao; i++) {
                atual = atual.proximo;
            }
            atual.anterior.proximo = atual.proximo;
            atual.proximo.anterior = atual.anterior;
            nElementos--;
        }
    }

    public void imprimirLista() {
        Nodo atual = primeiro;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public Nodo contem(String elemento) {
        Nodo atual = primeiro;
        while (atual != null) {
            if (atual.valor.equals(elemento)) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean removeElemento(String valor) {
        Nodo atual = primeiro;
        while (atual != null) {
            if (atual.valor == valor) {
                if (atual == primeiro) {
                    removeInicio();
                } else if (atual == ultimo) {
                    removeFinal();
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                    nElementos--;
                }
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }
}