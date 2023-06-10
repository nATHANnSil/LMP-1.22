/**
 * Exce��o: Pel�cula j� existente
 */
public class PeliculaJaExistenteException extends Exception {
    /**
     * Esta exce��o � lan�ada caso haja uma tentativa de adicionar uma pel�cula j� existente em uma lista.
     * @param mensagem
     */
    public PeliculaJaExistenteException(String mensagem){
        super("A m�dia j� existe na lista: " + mensagem);
    }
}