import java.io.*;

public class Arquivo {

    public String lerArquivo(String caminho) throws IOException {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString().toLowerCase();
    }

    public void criarArquivo(String caminho, String conteudo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));
        writer.write(conteudo);
    }
}