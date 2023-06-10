import java.util.List;

/**
 * Projetos 3, 4 e 5 (PARTE 4)
 * 
 * Grupo 08 do laborat�rio da disciplina LPM 1/2023 PUC Minas - Pra�a da
 * Liberdade
 * 
 * @author Bernardo Cavanellas Biondini
 * @author Jo�o Vitor Bessa Lacerda
 * @author Nathan Gon�alves de Oliveira
 * 
 *         Professor: Jo�o Caram Santos de Oliveira
 */
public class Aplicacao {

    /**
     * M�todo para carregar s�ries � plataforma
     * 
     * @param plataforma
     */
    public static void carregarDadosS(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Series.csv");

        String dadosS = file.ler();
        dadosS = file.ler(); // pula linha

        String[] dadosSeparadosS;
        // ID;Nome;Data

        do {
            dadosSeparadosS = dadosS.split(";");
            Stream novaSerie = new Serie(Integer.parseInt(dadosSeparadosS[0]), dadosSeparadosS[1], dadosSeparadosS[2]);
            plataforma.adicionarColecao(novaSerie);
            dadosS = file.ler();

        } while (dadosS != null);

        file.fecharArquivo();
    }

    /**
     * M�todo para carregar filmes � plataforma
     * 
     * @param plataforma
     */
    public static void carregarDadosF(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Filmes.csv");

        String dadosF = file.ler();
        dadosF = file.ler(); // pula linha

        String[] dadosSeparadosF;
        // IdFilme;Nome;DataDeLan�amento;Dura��o(min)

        do {
            dadosSeparadosF = dadosF.split(";");
            Stream novoFilme = new Filme(Integer.parseInt(dadosSeparadosF[0]), dadosSeparadosF[1], dadosSeparadosF[2],
                    Float.parseFloat(dadosSeparadosF[3]));
            plataforma.adicionarColecao(novoFilme);
            dadosF = file.ler();

        } while (dadosF != null);

        file.fecharArquivo();
    }

    /**
     * M�todo para carregar dados de audi�ncia de s�rie a clientes cadastrados na
     * plataforma
     * 
     * @param plataforma
     */
    public static void carregarDadosA(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Audiencia.csv");

        String dadosA = file.ler();
        dadosA = file.ler(); // pula linha

        String[] dadosSeparadosA;
        // Login;F/A;IdSerie

        do {
            if (dadosA == null)
                break;
            dadosSeparadosA = dadosA.split(";");

            plataforma.login(dadosSeparadosA[0]);
            Stream stream = plataforma.encontraStreamPorId(Integer.parseInt(dadosSeparadosA[2]));
            if (plataforma.getClienteAtual() != null && stream != null) {
                if (dadosSeparadosA[1].equals("F")) {
                    try {
                        plataforma.adicionarNaListaParaVer(stream);
                    } catch (PeliculaJaExistenteException e) {
                        // TODO Auto-generated catch block
                        // e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                } else /* dadosSeparadosA[1] == "A" */ {
                    plataforma.registrarAudiencia(stream);
                }
            }

            dadosA = file.ler();
        } while (dadosA != null);

        file.fecharArquivo();
    }

    /**
     * M�todo para criar perfis de cliente na plataforma
     * 
     * @param plataforma
     */
    public static void carregarDadosE(PlataformaStreaming plataforma) {
        ArquivoTextoLeitura file = new ArquivoTextoLeitura("codigo/src/POO_Espectadores.csv");

        String dadosE = file.ler();
        dadosE = file.ler(); // pula linha

        String[] dadosSeparadosE;

        do {
            // Nome;Login;Senha
            dadosSeparadosE = dadosE.split(";");
            Cliente novoCliente = new Cliente(dadosSeparadosE[0], dadosSeparadosE[1], dadosSeparadosE[2]);
            plataforma.adicionarCliente(novoCliente);
            dadosE = file.ler();

        } while (dadosE != null);

        file.fecharArquivo();
    }

    public static void main(String[] args) {

        // PLATAFORMA
        // Criando plataforma de streaming "Amaze"
        PlataformaStreaming Amaze = new PlataformaStreaming("Amaze");

        // ESPECTADORES
        // Carregando dados do arquivo "POO_Espectadores.csv" para o vetor de Series
        carregarDadosE(Amaze);

        // SERIE
        // Carregando dados do arquivo "POO_Series.csv" para o vetor de colecao
        carregarDadosS(Amaze);

        // AUDIENCIA (SERIES)
        // Carregando dados do arquivo "POO_Audiencia.csv"
        carregarDadosA(Amaze);

        // FILME
        // Carregando dados do arquivo "POO_Filmes.csv" para o vetor de Series
        carregarDadosF(Amaze);

        // REALIZAR LOGIN
        boolean acesso = false;
        while (acesso != true) {
            System.out.println("=-Realizar Login-=");
            System.out.print("Login>> ");
            String login = MyIO.readString(); // "Sha176581"
            System.out.print("Senha>> ");
            String senha = MyIO.readString(); // "SOrg05341"
            acesso = (Amaze.loginPlataforma(login, senha) == true ? true : false);
        }

        // MENU
        int op;
        do {
            System.out.printf("=-=-=-" + Amaze.getNome() + "-=-=-=\n");
            System.out.printf("Ol� " + Amaze.getNomeClienteAtual() + "!\n");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.println("Digite uma das op��es abaixo:");
            System.out.println("[1]Cat�logo"); // Pesquisar series e filmes -> Aicionar "para assistir" ou "j� assistido"
            System.out.println("[2]Perfil"); // Marcar series "j� assistidas" e retornar "lista de series ja assistidas"
            System.out.println("[3]Adicionar s�rie ou filme ao cat�logo");
            System.out.println("[4]Entrar em outra conta");
            System.out.println("[0]Finalizar programa");
            System.out.println("=-=-=-=-=-=-=-=-=");
            System.out.print(">> ");
            op = MyIO.readInt();

            switch (op) {
                case 1:
                    System.out.println("-Pesquisar Series e Filmes-");
                    System.out.println("Filtrar por:");
                    System.out.println("[1]Nome");
                    System.out.println("[2]G�nero");
                    System.out.println("[3]Idioma");
                    System.out.print(">> ");
                    int op1 = MyIO.readInt();

                    Stream opcaoEncontrada = null;

                    switch (op1) {
                        case 1:

                            System.out.print("Digite o nome: ");
                            String nome = MyIO.readLine();// Pink is the new White
                            try {
                                opcaoEncontrada = Amaze.filtrarPorNome(nome);
                            } catch (StreamNaoEncontradoException e) {
                                System.out.println("Pel�cula n�o encontrada: " + e.getMessage());
                            }
                            break;
                        case 2:
                       
                            System.out.print("Digite o g�nero: ");
                            String genero = MyIO.readLine();
                            try {
                                List<Stream> midias = Amaze.filtrarPorGenero(genero);
                                Amaze.mostrarLista(midias);
                            } catch (StreamNaoEncontradoException e) {
                                System.out.println("Pel�cula n�o encontrada: " + e.getMessage());
                            }
                            break;
                        case 3:

                            System.out.print("Digite o idioma: ");
                            String idioma = MyIO.readLine();
                            try {
                                List<Stream> midias = Amaze.filtrarPorIdioma(idioma);
                                Amaze.mostrarLista(midias);
                            } catch (StreamNaoEncontradoException e) {
                                System.out.println("Pel�cula n�o encontrada: " + e.getMessage());
                            }

                            break;
                        default:
                            System.out.println("Op��o inv�lida. Tente novamente.");
                    }
                    if (opcaoEncontrada != null) {
                        System.out.println("Pel�cula encontrada com sucesso!");
                        System.out.println("=-=-=-=-=-=-=-=-=");
                        System.out.println("[1]Marcar como op��o como: j� visto(a)");
                        System.out.println("[2]Adicionar op��o � lista: assistir futuramente");
                        System.out.println("[0]Sair");
                        System.out.print(">> ");
                        int op1p1 = MyIO.readInt();

                        switch (op1p1) {
                            case 1:

                                try {
                                    Amaze.getClienteAtual().adicionarNaListaJaVisto(opcaoEncontrada);
                                    System.out.println("Pel�cula adicionada com sucesso � lista *J� Visto*!");
                                } catch (PeliculaJaExistenteException e) {
                                    System.out.println("Pel�cula j� existente na lista:" + e.getMessage());
                                }

                                break;
                            case 2:

                                try {
                                    Amaze.getClienteAtual().adicionarNaListaParaVer(opcaoEncontrada);
                                    System.out.println("Pel�cula adicionada com sucesso � lista *Ver Futuramente*!");
                                } catch (PeliculaJaExistenteException e) {
                                    System.out.println("Pel�cula j� existente na lista:" + e.getMessage());
                                }

                                break;
                            case 0:

                                System.out.println("Finalizando programa.");
                                break;
                            default:

                                System.out.println("Op��o inv�lida. Tente novamente.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Selecione uma das op��es abaixo: ");
                    System.out.println("[1]Mostrar lista \"PARA ASSISTIR\" de s�ries e filmes");
                    System.out.println("[2]Mostrar lista \"J� ASSISTIDO\" de s�ries e filmes");
                    System.out.print(">> ");
                    int op2 = MyIO.readInt();

                    switch (op2) {
                        case 1:
                            try {
                                List<Stream> lista = Amaze.mostrarListaParaAssistir();
                                Amaze.mostrarLista(lista);
                            } catch (ListaVaziaException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            try {
                                List<Stream> lista = Amaze.mostrarListaJaVista();
                                Amaze.mostrarLista(lista);
                            } catch (ListaVaziaException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            System.out.println("[1]Avaliar m�dia");
                            System.out.println("[0]Sair");
                            int op2p2 = MyIO.readInt();

                            switch (op2p2) {
                                case 1:

                                    while (true) {
                                        System.out.print("Digite o ID da m�dia: ");
                                        int inserirId = MyIO.readInt();

                                        System.out.print("Insira a nota (entre 1 e 5): ");
                                        float inserirNota = MyIO.readFloat();

                                        while (inserirNota < 1 && inserirNota > 5) {
                                            if (inserirNota < 1 && inserirNota > 5) {
                                                System.out.println("Digite uma nota v�lida!");
                                                inserirNota = MyIO.readFloat();
                                            }
                                        }

                                        try {
                                            Amaze.avaliar(inserirId, inserirNota);
                                            System.out.println("Avalia��o registrada com sucesso!");
                                        } catch (PeliculaJaAvaliadaException e) {
                                            System.out.println(e.getMessage());
                                        }

                                        break;
                                    }

                                case 0:

                                    System.out.println("Finalizando programa.");
                                    break;
                                default:

                                    System.out.println("Op��o inv�lida. Tente novamente.");
                            }
                            break;
                        default:
                            System.out.println("Op��o inv�lida. Tente novamente.");
                    }
                    break;
                case 3:
                    System.out.println("Selecione uma das op��es abaixo: ");
                    System.out.println("[1]Cadastrar S�rie");
                    System.out.println("[2]Cadastrar Filme");
                    System.out.print(">> ");
                    int op3 = MyIO.readInt();

                    String nomeColecao;
                    String generoColecao;
                    String idiomaColecao;
                    String dataLancamentoColecao;

                    int novoId = 0;

                    ArquivoTextoEscrita escrita = null;

                    switch (op3) {
                        case 1:
                            System.out.print("Digite o nome da s�rie: ");
                            nomeColecao = MyIO.readLine();

                            System.out.print("Digite o g�nero da s�rie: ");
                            generoColecao = MyIO.readLine();

                            System.out.print("Digite o idioma da s�rie: ");
                            idiomaColecao = MyIO.readLine();

                            System.out.print("Digite a data de lan�amento da s�rie: ");
                            dataLancamentoColecao = MyIO.readLine();

                            System.out.print("Digite o numero de epis�dios da s�rie: ");
                            int numEpisodios = MyIO.readInt();

                            novoId = Stream.contId++;

                            Serie novaSerie = new Serie(novoId, nomeColecao, generoColecao, idiomaColecao,
                                    dataLancamentoColecao, numEpisodios);
                            Amaze.adicionarColecao(novaSerie);

                            String escreverSerie = Integer.toString(novoId) + ";" + nomeColecao + ";"
                                    + dataLancamentoColecao;
                            escrita = new ArquivoTextoEscrita("POO_Filmes.csv");
                            escrita.escrever(escreverSerie);
                            escrita.fecharArquivo();

                            break;
                        case 2:
                            System.out.print("Digite o nome do filme: ");
                            nomeColecao = MyIO.readLine();

                            System.out.print("Digite o g�nero do filme: ");
                            generoColecao = MyIO.readLine();

                            System.out.print("Digite o idioma do filme: ");
                            idiomaColecao = MyIO.readLine();

                            System.out.print("Digite a data de lan�amento do filme: ");
                            dataLancamentoColecao = MyIO.readLine();

                            System.out.print("Digite o numero de epis�dios do filme: ");
                            float duracao = MyIO.readFloat();

                            novoId = Stream.contId++;

                            Filme novoFilme = new Filme(novoId, nomeColecao, generoColecao, idiomaColecao,
                                    dataLancamentoColecao, duracao);
                            Amaze.adicionarColecao(novoFilme);

                            String escreverFilme = Integer.toString(novoId) + ";" + nomeColecao + ";"
                                    + dataLancamentoColecao + ";" + Float.toString(duracao);
                            escrita = new ArquivoTextoEscrita("POO_Filmes.csv");
                            escrita.escrever(escreverFilme);
                            escrita.fecharArquivo();

                            break;
                        case 0:
                            System.out.println("\n\nFinalizando programa.\n\n");
                            break;
                        default:
                            System.out.println("Op��o inv�lida. Tente novamente.");
                    }
                    break;
                case 4:
                    boolean novoAcesso = false;
                    while (novoAcesso != true) {
                        System.out.println("=-Realizar Login-=");
                        System.out.print("Login>> ");
                        String login = MyIO.readString(); // "Rog165837"
                        System.out.print("Senha>> ");
                        String senha = MyIO.readString(); // "RMor07441"
                        novoAcesso = (Amaze.loginPlataforma(login, senha) == true ? true : false);
                    }
            }

        } while (op != 0);
    }
}