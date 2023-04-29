import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Aplicacao {
    public static void main(String[] args) {
        Map<Integer, String> idsEmUso = new HashMap<>(); // Mapa para rastrear IDs em uso
        Serie serie = new Serie();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Digite o ID da série (ou 'PARE' para finalizar):");
            String idStr = reader.readLine();
            while (!idStr.equalsIgnoreCase("PARE")) {
                int id = Integer.parseInt(idStr);
                if (idsEmUso.containsKey(id)) {
                    System.out.println("ID já em uso. Série não será salva.");
                } else {
                    serie.setid_Serie(id);
                    idsEmUso.put(id, serie.getNome()); // Adiciona ID e nome da série ao mapa de IDs em uso

                    System.out.println("Digite o nome da série:");
                    String nome = reader.readLine();
                    if (idsEmUso.containsValue(nome)) {
                        System.out.println("Nome de série já em uso. Série não será salva.");
                    } else {
                        serie.setNome(nome);

                        System.out.println("Digite a data de lançamento da série:");
                        String dataLancamento = reader.readLine();
                        serie.setDataDeLancamento(dataLancamento);

                        // Salva a série no arquivo
                        String nomeArquivo = "serie_" + id + ".csv"; // Nome do arquivo baseado no ID da série
                        serie.salvar();

                        System.out.println("Série salva com sucesso!");
                    }
                }

                System.out.println("Digite o ID da próxima série (ou 'PARE' para finalizar):");
                idStr = reader.readLine();
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
