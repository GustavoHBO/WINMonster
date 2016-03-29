package controller;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	public static void compactarTexto(String texto){
		int[] frequencias = Controller.calcularFrequencia(texto);
		FilaPrioridade fila = Controller.criarFilaComFrequencias(frequencias);
		ArvoreHuffman arvore = fila.gerarArvoreHuffman();
		String[] dicionario = Controller.gerarCodigoHuffman(arvore);
		Controller.escreverCodigo(dicionario, texto);
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
	/*---------------------------------------------------------------------------------*/
	
	public static void escreverCodigo(String[] dicionario, String texto){
		String txtCompact = "";
		
		for(int i =0; i < texto.length(); i++){
			txtCompact += dicionario[texto.charAt(i)];
			System.out.println(texto.charAt(i) + " - " + dicionario[texto.charAt(i)] + " - " + funcaoHash(dicionario[texto.charAt(i)]));
		}
		//System.out.println(txtCompact);
		char[] tabela = criarTabelaHash(dicionario);
		int i=0;
		String stringTabela = " %";
		for(char c : tabela){
			if(c != 0){
				stringTabela += "/" + i + " - " + c;
			}
			i++;
		}
		String compac = txtCompact + stringTabela;
		escreverArquivo(compac, "CodigoCompactado", "");
		
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
	
	private static char[] criarTabelaHash(String[] dicionario) {
		int i =0;
		char[] tabelaHash = new char[NUM];
		for(String codigo :dicionario){
			if(codigo != null){
				tabelaHash[funcaoHash(codigo)] = (char) i;
			}
			i++;
		}
		return tabelaHash;
	}

	/**
	 * Método responsável pela leitura do arquivo e retorna um array dos dados lidos.
	 * @param arquivo - Nome do arquivo a ser lido.
	 * @return dados - Array dos caracteres lidos no arquivo.
	 */

	public String lerArquivo(String arquivo){

		String dados = new String();// String onde será armazenada as informações lidas.
		FileReader arq = null;// Instância do arquivo.
		BufferedReader buffer = null;// Instância do leitor do arquivo.
		
		
		try {// Ver como será tratado esse erro.
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);
			
			
			while (buffer.ready()){//Irá ser valido até encontrar o fim do arquivo.
				dados = dados + buffer.readLine();// Lê linha por linha no arquivo e concatena no final da string dados.
			}
			buffer.close();// Finalizo o leitor do arquivo.
			arq.close();// Finalizo o arquivo.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dados;
	}

	/**
	 * Método responsável pela escrita dos arquivos seguindo o algoritmo de Huffman.
	 * @param arrayCaractere - Array com os códigos dos caracteres.
	 * @param nomeArquivo - Nome do arquivo a ser gravado.
	 * @param caminhoArquivo - Caminho ao qual o arquivo será armazenado.
	 */
	public static void escreverArquivo(String arrayCaractere, String nomeArquivo, String caminhoArquivo){

		FileWriter fileWrite = null;
		BufferedWriter buffWrite = null;
		String nomeCaminho = caminhoArquivo + nomeArquivo + ".monster";// Aqui é especificado o caminho e o nome do arquivo.
		File arquivo = new File(nomeCaminho);//Instância do arquivo.
		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			fileWrite = new FileWriter(arquivo);// Defino o arquivo ao qual irá ser escrito.
			buffWrite = new BufferedWriter(fileWrite);// Defino como irá escrever.
			
			buffWrite.write(arrayCaractere);// Escreve o conteúdo da array no arquivo.

			buffWrite.close();// Fecho o BufferedWrite.
			fileWrite.close();// Fecho o FileWrite.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
