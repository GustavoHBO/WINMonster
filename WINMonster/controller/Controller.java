package controller;

import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
import model.Compactador;
import model.Descompactador;

/**
 * Classe controller, responsável pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de mar�o de 2016.
 *
 */
public class Controller {
	
	private static Controller instance = new Controller();
	/*----------------------------------------------------------------------------------------------------------*/
	
	private Controller(){}
	
	/*----------------------------------------------------------------------------------------------------------*/
	/**
	 * SingleTon, retorna uma inst�ncia do controller.
	 */
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}
	
	/*----------------------------------------------------------------------------------------------------------*/
	/**
	 * Zera a inst�ncia existente do controller.
	 */
	public static void zerarSingleton(){
		instance = new Controller();
	}

	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * M�todo respons�vel por compactar o arquivo escolhido.
	 * @param caminho - Caminho at� o arquivo a ser compactado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo n�o possa ser criado.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido n�o seja encontrado.
	 * @throws ArquivoNaoLidoException - Caso n�o tenha sido poss�vel ler o arquivo escolhido.
	 */
	public static void compactar(String caminho) throws ArquivoNaoCriadoException, ArquivoNaoEncontradoException, ArquivoNaoLidoException{
		Compactador.comprimirArquivo(caminho);
	}
	
	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * M�todo respons�vel por descompactar o arquivo escolhido.
	 * @param caminho - Caminho at� o arquivo a ser descompactado.
	 * @throws ArquivoNaoLidoException - Caso o arquivo n�o possa ser lido.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido n�o seja encontrado.
	 */
	public static void descompactar(String caminho) throws ArquivoNaoLidoException, ArquivoNaoEncontradoException{
		
		Descompactador.descompactar(caminho);
		
	}
	
	private static int funcaoHash(String codigo){
		int numero = 0;
		for(int i = 0; i< codigo.length(); i++){
			if(codigo.charAt(i) == '1'){
				numero += Math.pow(2,i);
			}
		}
		return numero;
	}
	
	

	

	/*public static int converterBinario(String stringBinario){

		int valor = 0;
		char[] array = stringBinario.toCharArray();
		
		for(int i = 7; i != -1; i--){
			if(array[i] == '1')
				valor += Math.pow(2, 7 - i);
		}
		return valor;
	}*/
}
