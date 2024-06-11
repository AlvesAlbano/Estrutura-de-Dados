import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Arquivo {

    public String lerArquivo(String caminho) throws FileNotFoundException {

        File arquivo = new File(caminho);
        Scanner scanner = new Scanner(arquivo);
        StringBuilder conteudo = new StringBuilder();

        while (scanner.hasNextLine()){
            conteudo.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return conteudo.toString().toLowerCase();
    }

    public void criarArquivo(){
        // Implementar lógica para criar arquivo
    }
}
