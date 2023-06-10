/**
 * Exce��o: Lista vazia
 */
public class ListaVaziaException extends Exception{
    /**
     * Esta exce��o � lan�ada caso haja tentativa de visualizar uma lista que esteja vazia.
     * @param mensagem
     */
    public ListaVaziaException(String mensagem){
        super(mensagem);
    }
}