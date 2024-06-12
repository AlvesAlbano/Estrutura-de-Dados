import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HashColisaoExterior hash = new HashColisaoExterior(50);
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        Arquivo arquivo = new Arquivo();

        String caminhoTexto = "EstruturaDados-main/src/arquivo.txt";
        String caminhoChaves = "EstruturaDados-main/src/palavras.txt";
        String caminhoCriar = "EstruturaDados-main/src/indice.txt";
        
        String[] palavrasChaves = arquivo.lerArquivo(caminhoChaves).split("[\\s,]+");

        for (int i = 0; i < palavrasChaves.length; i++) {
            hash.insere(palavrasChaves[i]);
            arvore.insere(palavrasChaves[i]);
        }

        BufferedReader reader = new BufferedReader(new FileReader(caminhoTexto));
        String linha;
        int numeroLinha = 1;
        while ((linha = reader.readLine()) != null) {
            String[] textoConteudo = linha.split("[\\s.,;:]+");
            for (int i = 0;i < textoConteudo.length;i++){
                if (hash.contem(textoConteudo[i])) {
                    arvore.adicionarRemissivo(textoConteudo[i],numeroLinha);
                }
            }
            numeroLinha++;
        }
        arvore.imprimirRemissivo(caminhoCriar);
    }
}
