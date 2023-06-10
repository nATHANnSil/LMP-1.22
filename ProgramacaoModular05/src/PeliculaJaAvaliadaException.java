/**
 * Exce��o: Pel�cula j� avaliada
 */
public class PeliculaJaAvaliadaException extends Exception{
    /**
     * Esta exce��o � lan�ada caso haja tentativa de avaliar uma pel�cula cuja j� tenha sido avaliada previamente.
     * @param mensagem
     */
    public PeliculaJaAvaliadaException(String mensagem){
        super(mensagem);
    }
}