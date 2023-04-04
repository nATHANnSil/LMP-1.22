import java.io.*;

public class ArquivoLeitura {

	private BufferedReader entrada;

	ArquivoLeitura(String nomeArquivo) {
		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado");
		}
	}

	public void fecharArquivo() {
		try {
			entrada.close();
		} catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
		}
	}

	@SuppressWarnings("finally")
	public String ler() {
		String textoEntrada = null;
		try {
			textoEntrada = entrada.readLine();
		} catch (EOFException excecao) {
			textoEntrada = null;
		} catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			textoEntrada = null;
		} finally {
			return textoEntrada;
		}
	}

	public static void main(String[] args) {
		String nomeArquivo = "exemplo.txt"; // nome do arquivo a ser lido
		String linha;

		ArquivoLeitura arquivo = new ArquivoLeitura(nomeArquivo);

		// leitura do arquivo de texto
		System.out.println("Lendo arquivo texto...");
		linha = arquivo.ler();
		while (linha != null) {
			System.out.println(linha);
			linha = arquivo.ler();
		}

		// leitura do arquivo CSV
		nomeArquivo = "exemplo.csv";
		arquivo = new ArquivoLeitura(nomeArquivo);
		System.out.println("Lendo arquivo CSV...");
		linha = arquivo.ler();
		while (linha != null) {
			String[] dados = linha.split(";");
			for (String dado : dados) {
				System.out.print(dado + " ");
			}
			System.out.println();
			linha = arquivo.ler();
		}

		// leitura do arquivo JSON
		nomeArquivo = "exemplo.json";
		arquivo = new ArquivoLeitura(nomeArquivo);
		System.out.println("Lendo arquivo JSON...");
		linha = arquivo.ler();
		while (linha != null) {
			System.out.println(linha);
			linha = arquivo.ler();
		}

		arquivo.fecharArquivo();
	}
}
