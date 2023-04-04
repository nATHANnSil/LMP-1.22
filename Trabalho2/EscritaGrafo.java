import java.io.*;

public class ArquivoTextoEscrita {

private BufferedWriter saida;

ArquivoTextoEscrita(String nomeArquivo) {

    try {
        // Extrai a extensão do nome do arquivo de entrada
        String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);

        // Cria o nome do arquivo de saída com a mesma extensão do arquivo de entrada
        String nomeArquivoSaida = "saida." + extensao;

        saida = new BufferedWriter(new FileWriter(nomeArquivoSaida));
    } catch (FileNotFoundException excecao) {
        System.out.println("Arquivo não encontrado");
    } catch (IOException excecao) {
        System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
    }
}

public void fecharArquivo() {

    try {
        saida.close();
    } catch (IOException excecao) {
        System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);
    }
}

public void escrever(String textoEntrada) {

    try {
        saida.write(textoEntrada);
        saida.newLine();
    } catch (IOException excecao) {
        System.out.println("Erro de entrada/saída " + excecao);
    }
}
