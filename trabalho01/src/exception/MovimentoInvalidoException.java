package exception;

public class MovimentoInvalidoException extends Exception{
    
    public MovimentoInvalidoException(String mensagemExcecao){
        super(mensagemExcecao);
    }
}
