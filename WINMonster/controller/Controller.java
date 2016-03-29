package controller;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	public void escreverArquivo(String dadosCaractere, byte[]dadosByte, String caminhoArquivo){

		FileOutputStream writeStream = null;
		DataOutputStream writeDataStream = null;
		// Aqui é especificado o caminho e o nome do arquivo.

		String nomeCaminho = caminhoArquivo.substring(0, caminhoArquivo.indexOf('.'));
		nomeCaminho += ".monster";
		File arquivo = new File(nomeCaminho);//Instância do arquivo.
		
		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			writeStream = new FileOutputStream(arquivo);
			writeDataStream = new DataOutputStream(writeStream);
			
			writeDataStream.writeBytes(dadosCaractere);
			writeDataStream.write(dadosByte);
			
			writeDataStream.close();
			writeStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método responsável pela compressão do arquivo escolhido.
	 * @param caminhoArquivo - Localização do arquivo.
	 */

	public void comprimirArquivo(String caminhoArquivo){

		String dadosArquivo = lerArquivo(caminhoArquivo);
		int[] frequenciaCaractere = calcularFrequencia(dadosArquivo);
		FilaPrioridade filaFrequencia = criarFilaComFrequencias(frequenciaCaractere);
		ArvoreHuffman arvoreHuffman = filaFrequencia.gerarArvoreHuffman();
		String[] dicionario = gerarCodigoHuffman(arvoreHuffman);

		/*
		 * Abaixo é criado a String que armazenará o dicionário e o arquivo codificado para escrita.
		 * O tamanho é definido como a soma do tamanho da array de dados + o tamanho do dicionario + dois
		 * caracteres que servirão para definir o inicio e o fim do dicionário.
		 */
		String dadosArquivoCodificado;

		dadosArquivoCodificado = "{";// Define o inicio do dicionário.

		for(int i = 0; i < dicionario.length; i++){
			if(dicionario[i] != null){
				
				dadosArquivoCodificado += dicionario[i];
				dadosArquivoCodificado += (char)i;
			}
		}

		dadosArquivoCodificado += "}";// Define o fim do dicionário.

		//dadosArquivoCodificado += (substituirCaractere(dicionario, dadosArquivo.toCharArray()));

		escreverArquivo(dadosArquivoCodificado, substituirCaractere(dicionario, dadosArquivo.toCharArray()), caminhoArquivo);

	}

	/**
	 * Método que recebe os dados lidos do arquivo e o dicionário e cria o array com os caracteres codificados.
	 * @param dicionario - Dicionário com os códigos.
	 * @param dadosArquivo - Os dados lidos do arquivo.
	 * @return dadosArquivoCodificado - String com a substituição dos caracteres.
	 */
	public byte[] substituirCaractere(String[] dicionario, char[] dadosArquivo){

		String dadosArquivoCodificado = new String();
		for(int i = 0; i < dadosArquivo.length; i++){
			if(dadosArquivo[i] != '\0')
				dadosArquivoCodificado += dicionario[dadosArquivo[i]];
		}
		int posI = 0, posF = 0, pos = 0;
		int rep = (dadosArquivoCodificado.length()/8);
		byte[] dados = new byte [rep + 1];
		for(int i = 0; dadosArquivoCodificado.length() - i > 8; i+=8){
			posI = i;
			posF = i + 8;
			dados[pos]= (byte) Integer.parseInt(dadosArquivoCodificado.substring(posI, posF), 2);
			pos++;
		}
		dados[rep] = (byte) Integer.parseInt(dadosArquivoCodificado.substring(posF), 2);

		return dados;
	}
}
