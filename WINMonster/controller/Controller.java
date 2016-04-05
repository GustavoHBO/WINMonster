package controller;

import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
import model.Compactador;
import model.Descompactador;

/**
 * Classe controller, responsÃ¡vel pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de marï¿½o de 2016.
 *
 */
public class Controller {
	
	private static Controller instance = new Controller();
	/*----------------------------------------------------------------------------------------------------------*/
	
	private Controller(){}
	
	/*----------------------------------------------------------------------------------------------------------*/
	/**
	 * SingleTon, retorna uma instância do controller.
	 */
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}
	
	/*----------------------------------------------------------------------------------------------------------*/
	/**
	 * Zera a instância existente do controller.
	 */
	public static void zerarSingleton(){
		instance = new Controller();
	}

	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * Método responsável por compactar o arquivo escolhido.
	 * @param caminho - Caminho até o arquivo a ser compactado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo não possa ser criado.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido não seja encontrado.
	 * @throws ArquivoNaoLidoException - Caso não tenha sido possível ler o arquivo escolhido.
	 */
	public static void compactar(String caminho) throws ArquivoNaoCriadoException, ArquivoNaoEncontradoException, ArquivoNaoLidoException{
		Compactador.comprimirArquivo(caminho);
	}
	
	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * Método responsável por descompactar o arquivo escolhido.
	 * @param caminho - Caminho até o arquivo a ser descompactado.
	 * @throws ArquivoNaoLidoException - Caso o arquivo não possa ser lido.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido não seja encontrado.
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
