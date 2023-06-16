//CLASSE STREAMAVALIAVEL MODIFICADA!!!

package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relatorio {
    private PlataformaStreaming plataforma;
    private String clienteComMaisMidias;
    private int maiorNumeroDeMidias;
    private String clienteComMaisAvaliacoes;
    private int maiorNumeroDeAvaliacoes;
    private double porcentagemClientesComPeloMenos15Avaliacoes;
    private List<StreamAvaliavel> top10Midias;
    private List<StreamAvaliavel> top10MidiasComMaisVisualizacoes;

    public Relatorio(PlataformaStreaming plataforma) {
        this.plataforma = plataforma;
        this.clienteComMaisMidias = "";
        this.maiorNumeroDeMidias = 0;
        this.clienteComMaisAvaliacoes = "";
        this.maiorNumeroDeAvaliacoes = 0;
        this.plataforma = plataforma;
        this.porcentagemClientesComPeloMenos15Avaliacoes = 0.0;
        this.top10Midias = new ArrayList<>();
        this.top10MidiasComMaisVisualizacoes = new ArrayList<>();

    }

    public String getClienteComMaisMidias() {
        return clienteComMaisMidias;
    }

    public int getMaiorNumeroDeMidias() {
        return maiorNumeroDeMidias;
    }

    public String getClienteComMaisAvaliacoes() {
        return clienteComMaisAvaliacoes;
    }

    public int getMaiorNumeroDeAvaliacoes() {
        return maiorNumeroDeAvaliacoes;
    }

    public double getPorcentagemClientesComPeloMenos15Avaliacoes() {
        return porcentagemClientesComPeloMenos15Avaliacoes;
    }

    public List<StreamAvaliavel> getTop10Midias() {
        return top10Midias;
    }

    public List<StreamAvaliavel> getTop10MidiasComMaisVisualizacoes() {
        return top10MidiasComMaisVisualizacoes;
    }

    public void gerarRelatorioClienteMaisMidias() {
        HashMap<String, Cliente> clientes = plataforma.getClientes();

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeMidias = cliente.listaJaVistas.size();
            if (numeroDeMidias > maiorNumeroDeMidias) {
                maiorNumeroDeMidias = numeroDeMidias;
                clienteComMaisMidias = cliente.getNomeDeUsuario();
            }
        }
    }

    public void gerarRelatorioClienteMaisAvaliacoes() {
        HashMap<String, Cliente> clientes = plataforma.getClientes();

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeAvaliacoes = 0;
            for (AvaliacaoStream avaliacao : cliente.listaJaVistas) {
                if (avaliacao.isAvaliado()) {
                    numeroDeAvaliacoes++;
                }
            }
            if (numeroDeAvaliacoes > maiorNumeroDeAvaliacoes) {
                maiorNumeroDeAvaliacoes = numeroDeAvaliacoes;
                clienteComMaisAvaliacoes = cliente.getNomeDeUsuario();
            }
        }
    }

    public void gerarRelatorioPorcentagemClientesComPeloMenos15Avaliacoes() {

        HashMap<String, Cliente> clientes = plataforma.getClientes();
        int totalClientes = clientes.size();
        int totalClientesComPeloMenos15Avaliacoes = 0;

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            int numeroDeAvaliacoes = 0;
            for (AvaliacaoStream avaliacao : cliente.listaJaVistas) {
                if (avaliacao.isAvaliado()) {
                    numeroDeAvaliacoes++;
                }
            }
            if (numeroDeAvaliacoes >= 15) {
                totalClientesComPeloMenos15Avaliacoes++;
            }
        }
    }

    public void gerarRelatorioTop10Midias() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        List<StreamAvaliavel> midiasComPeloMenos100Avaliacoes = new ArrayList<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                if (streamAvaliavel.getNumeroDeAvaliacoes() >= 100) {
                    midiasComPeloMenos100Avaliacoes.add(streamAvaliavel);
                }
            }
        }

        midiasComPeloMenos100Avaliacoes.sort(new Comparator<StreamAvaliavel>() {
            @Override
            public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                return Double.compare(o2.getMediaDeAvaliacoes(), o1.getMediaDeAvaliacoes());
            }
        });

        top10Midias = midiasComPeloMenos100Avaliacoes.subList(0, Math.min(10, midiasComPeloMenos100Avaliacoes.size()));
    }

    public void gerarRelatorioTop10MidiasComMaisVisualizacoes() {
        HashMap<Integer, Stream> colecao = plataforma.getColecao();
        List<StreamAvaliavel> midias = new ArrayList<>();

        for (Map.Entry<Integer, Stream> entry : colecao.entrySet()) {
            Stream stream = entry.getValue();
            if (stream instanceof StreamAvaliavel) {
                StreamAvaliavel streamAvaliavel = (StreamAvaliavel) stream;
                midias.add(streamAvaliavel);
            }
        }

        midias.sort(new Comparator<StreamAvaliavel>() {
            @Override
            public int compare(StreamAvaliavel o1, StreamAvaliavel o2) {
                return Integer.compare(o2.getNumeroDeVisualizacoes(), o1.getNumeroDeVisualizacoes());
            }
        });

        top10MidiasComMaisVisualizacoes = midias.subList(0, Math.min(10, midias.size()));
    }
}
