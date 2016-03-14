package controller;




import util.FilaPrioridade;
import util.Huffman.ArvoreHuffman;
import util.Huffman.FolhaHuffman;


/**
 * Classe controller, responsável pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de março de 2016.
 *
 */
public class Controller {
/*---------------------------------------------------------------------------------*/
	private static final int NUM = 256;
	private static Controller instance = new Controller();
	
	private Controller(){}
	
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		
		return instance;
	}
	
	public static void zerarSingleton(){
		instance = new Controller();
	}
/*---------------------------------------------------------------------------------*/
	
	public static int[] calcularFrequencia(String texto){
		int[] frequencias = new int[NUM];
		char[] caracteres = texto.toCharArray();
		
		for (int i = 0; i < caracteres.length; i++)
            frequencias[caracteres[i]]++;
		
		return frequencias;
	}
/*---------------------------------------------------------------------------------*/
	
	public static FilaPrioridade criarFilaComFrequencias(int[] frequencias){
		FilaPrioridade fila = new FilaPrioridade();
		
		for(int i = 0; i < frequencias.length; i++){
			if(frequencias[i] > 0){
				FolhaHuffman folha = new FolhaHuffman();
				folha.setFrequencia(frequencias[i]);
				folha.setInfo((char)i);
				
				fila.inserir(frequencias[i], folha);
			}
		}
		return fila;
	}
/*---------------------------------------------------------------------------------*/
	
	public static String[] gerarCodigoHuffman(ArvoreHuffman arvore){
		String[] dicionario = new String[NUM];
		
		ArvoreHuffman.construirCodigo(dicionario, arvore, "");
		
		return dicionario;
	}
	
}
