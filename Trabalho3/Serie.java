import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


@SuppressWarnings("unused")
public class Serie {
	private String[] GENEROS;
	private int id_Serie;
	private String nome;
	private String genero;
	private String idioma;
	private int quantidadeEpisodios;
	private int audiencia;
	private String DataDeLancamento;

	public Serie() {
	}

	public Serie(String nome, int id_Serie, String DataDeLancamento, String genero, String idioma,
			int quantidadeEpisodios, int audiencia) {
		setNome(nome);
		setGenero(genero);
		setIdioma(idioma);
		setQuantidadeEpisodios(quantidadeEpisodios);
		setAudiencia(audiencia);
	}

	public void registrarAudiencia(int qnt) {
		setAudiencia(qnt);
	}

	public String[] getGENEROS() {
		return GENEROS;
	}

	public void setGENEROS(String[] gENEROS) {
		GENEROS = gENEROS;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getid_Serie() {
		return id_Serie;
	}

	public void setid_Serie(int id_Serie) {
		this.id_Serie = id_Serie;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getQuantidadeEpisodios() {
		return quantidadeEpisodios;
	}

	public void setQuantidadeEpisodios(int quantidadeEpisodios) {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

	public int getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(int audiencia) {
		this.audiencia = audiencia;
	}

	public String getDataDeLancamento() {
		return DataDeLancamento;

	}

	public void setDataDeLancamento(String DataDeLancamento) {
		this.DataDeLancamento = DataDeLancamento;

	}

	boolean idEmUso(int id) {
	    String nomeArquivo = "POO_Series.csv";
	    try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
	        while (scanner.hasNextLine()) {
	            String linha = scanner.nextLine();
	            String[] dados = linha.split(";");
	            if (dados.length > 0 && dados[0].equals(Integer.toString(id))) {
	                return true;
	            }
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@SuppressWarnings("deprecation")
	public void salvar() {
	    Scanner scanner = new Scanner(System.in);
	    String nomeArquivo = "POO_Series.csv";

	    while (true) {
	        System.out.println("Digite o ID da série (ou 'PARE' para finalizar):");
	        String idStr = scanner.nextLine();

	        if (idStr.equalsIgnoreCase("pare")) {
	            break;
	        }

	        try {
	            int id = Integer.parseInt(idStr);

	            if (idEmUso(id)) {
	                System.out.println("ID já em uso. Não é possível salvar a série.");
	            } else {
	                System.out.println("Digite o nome da série:");
	                String nome = scanner.nextLine();

	                System.out.println("Digite a data de lançamento da série:");
	                String dataDeLancamento = scanner.nextLine();

	                try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo, true))) {
	                    writer.println(id + ";" + nome + ";" + dataDeLancamento);
	                    System.out.println("Série salva com sucesso!");
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ID inválido!");
	        }
	    }

	    scanner.close();
	}
}
