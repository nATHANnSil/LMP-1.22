/**
 * Exce��o: Stream n�o encontrado
 */
public class StreamNaoEncontradoException extends Exception {
    /**
     * Esta exce��o � lan�ada caso haja tentativa de procura de pel�cula e a mesma n�o for encontrada.
     * @param mensagem
     */
    public StreamNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}