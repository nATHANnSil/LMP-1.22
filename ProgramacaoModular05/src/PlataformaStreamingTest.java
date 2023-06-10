import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class PlataformaStreamingTest {

    @Test
    public void testarPlataformaStreaming() {
        // Cria��o da plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("Streaming 1");

        // Verifica o nome da plataforma
        Assertions.assertEquals("Streaming 1", plataforma.getNome());

        // Verifica a cole��o de streams vazia
        Assertions.assertTrue(plataforma.getColecao().isEmpty());

        // Verifica a lista de clientes vazia
        Assertions.assertTrue(plataforma.getClientes().isEmpty());

        // Adiciona um cliente � plataforma
        Cliente cliente1 = new Cliente("cliente1", "senha1", "Cliente 1");
        plataforma.adicionarCliente(cliente1);

        // Verifica se o cliente foi adicionado corretamente
        Assertions.assertEquals(1, plataforma.getClientes().size());
        Assertions.assertNotNull(plataforma.getClientes().get(cliente1.getLogin()));

        // Faz login do cliente na plataforma
        plataforma.loginPlataforma("cliente1", "senha1");

        // Verifica o cliente atual na plataforma
        Assertions.assertEquals(cliente1, plataforma.getClienteAtual());

        // Adiciona uma s�rie � cole��o da plataforma
        Serie serie = new Serie(1, "Friends", "Com�dia", "Ingl�s", "1994-09-22", 236);
        plataforma.adicionarColecao(serie);

        // Verifica se a s�rie foi adicionada corretamente � cole��o da plataforma
        Assertions.assertEquals(1, plataforma.getColecao().size());
        Assertions.assertNotNull(plataforma.getColecao().get(serie.getId()));

        // Filtra s�ries por g�nero
        List<Stream> resultadoGenero = null;
		try {
			resultadoGenero = plataforma.filtrarPorGenero("Drama");
		} catch (StreamNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assertions.assertEquals(serie, resultadoGenero);

        // Filtra s�ries por idioma
        List<Stream> resultadoIdioma = null;
		try {
			resultadoIdioma = plataforma.filtrarPorIdioma("Ingl�s");
		} catch (StreamNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assertions.assertEquals(serie, resultadoIdioma);

        // Filtra s�ries por nome
        Stream resultadoNome = null;
		try {
			resultadoNome = plataforma.filtrarPorNome("Friends");
		} catch (StreamNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assertions.assertEquals(serie, resultadoNome);

        // Registra audi�ncia de uma s�rie pelo cliente atual
        plataforma.registrarAudiencia(serie);

        // Verifica se a audi�ncia foi registrada corretamente na s�rie e no cliente
        Assertions.assertEquals(1, serie.getAudiencia());

        // Encontra uma stream por ID
        Stream streamEncontrada = plataforma.encontraStreamPorId(1);
        Assertions.assertEquals(serie, streamEncontrada);
    }
}