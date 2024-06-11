import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Arquivo arquivo = new Arquivo();
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        String caminho = "D:\\arquivo.txt";
        String[] palavrasChave = {"and"};

        String[] palavras = arquivo.lerArquivo(caminho).split("\\W+");

        HashColisaoExterior hash = new HashColisaoExterior(palavras.length);

        for (int i = 0;i < palavras.length;i++) {
            arvore.insere(palavras[i]);
            hash.insere(palavras[i]);
        }

        hash.imprime();
        arvore.imprimeEmOrdem();
    }
}
