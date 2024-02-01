package robo;

import java.util.Random;

import exception.MovimentoInvalidoException;

public class RoboInteligente extends Robo {
	boolean movimentoValido = true;
	public RoboInteligente(String cor) {
		super(cor);
	}
	
	@Override
	public void mover(int inteiro) throws MovimentoInvalidoException, InterruptedException{
		while(true) {
			try {
				super.mover(inteiro);
				break;
			} catch (MovimentoInvalidoException e) {
				System.out.println("Movimento inválido, tentando novamente...");
				Thread.sleep(1000);
				Random aleatorio = new Random();
				inteiro = aleatorio.nextInt(4) + 1;
				System.out.println();
			}
		}
	}
	
	
}
	
