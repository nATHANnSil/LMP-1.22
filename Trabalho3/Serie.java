import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*** - PARTE COMENTADA ESTÁ DESATUALIZADA CONFORME ALTERAÇÕES DO CÓDIGO DOS OUTROS INTEGRANTES DO GRUPO

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
	
***/
	
	//Método para salvar serie no arquivo POO_Series.csv

	public void salvar() {
	    String nomeArquivo =  "POO_Series.csv"; //recebe o nome do arquivo
	    try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) { //escrever no arquivo 
	        String dados = id_Serie + "," + nome + "," + DataDeLancamento;
	        writer.println(dados);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	// Método para verificar se o ID já está em uso. Se estiver, não salva
	private boolean idEmUso(int id) {
	    File diretorio = new File(".");
	    File[] arquivos = diretorio.listFiles();

	    for (File arquivo : arquivos) {
	        if (arquivo.isFile() && arquivo.getName().startsWith("serie_")) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
	                String linha;
	                while ((linha = reader.readLine()) != null) {
	                    if (linha.startsWith(String.valueOf(id))) {
	                        return true;
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return false;
	}

    // Método para verificar se o nome já está em uso, se estiver não salva.
	private boolean nomeEmUso(String nome) {
        File diretorio = new File(".");
        File[] arquivos = diretorio.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().startsWith("serie_")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split("\t");
                        if (dados.length >= 2 && dados[1].trim().equalsIgnoreCase(nome)) {
                            return true;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
