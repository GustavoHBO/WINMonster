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
 * Classe controller, respons�vel pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de mar�o de 2016.
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

		for (int i = 0; i < caracteres.length; i++){
			frequencias[caracteres[i]]++;
		}
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

		for(int i = 0; i < texto.length(); i++){
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
		//escreverArquivo(compac, "CodigoCompactado", "");

	}
	/*---------------------------------------------------------------------------------*/
	private static int funcaoHash(String codigo){
		int numero = 0;
		for(int i = 0; i< codigo.length(); i++){
			if(codigo.charAt(i) == '1'){
				numero += Math.pow(2,i);
			}
		}
		return numero;
	}
	/*---------------------------------------------------------------------------------*/
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
	/*---------------------------------------------------------------------------------*/
	/**
	 * M�todo respons�vel pela leitura do arquivo e retorna um array dos dados lidos.
	 * @param arquivo - Nome do arquivo a ser lido.
	 * @return dados - Array dos caracteres lidos no arquivo.
	 */

	public String lerArquivo(String arquivo){

		StringBuffer dados = new StringBuffer();// String onde ser� armazenada as informa��es lidas.
		FileReader arq = null;// Inst�ncia do arquivo.
		BufferedReader buffer = null;// Inst�ncia do leitor do arquivo.


		try {// Ver como ser� tratado esse erro.
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);


			while (buffer.ready()){//Ir� ser valido at� encontrar o fim do arquivo.
				dados.append(buffer.readLine());// L� linha por linha no arquivo e concatena no final da string dados.
			}
			buffer.close();// Finalizo o leitor do arquivo.
			arq.close();// Finalizo o arquivo.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
	/*---------------------------------------------------------------------------------*/
	/**
	 * M�todo respons�vel pela escrita dos arquivos seguindo o algoritmo de Huffman.
	 * @param arrayCaractere - Array com os c�digos dos caracteres.
	 * @param nomeArquivo - Nome do arquivo a ser gravado.
	 * @param caminhoArquivo - Caminho ao qual o arquivo ser� armazenado.
	 */
	public static void escreverArquivo(String dicionario, int[] dadosArquivoCodificado,  String caminhoArquivo){

		FileOutputStream writeStream = null;
		DataOutputStream writeDataStream = null;
		BufferedWriter bufferWrite = null;
		FileWriter fileWrite = null;
		// Aqui � especificado o caminho e o nome do arquivo.
		String nomeCaminho = caminhoArquivo.substring(0, caminhoArquivo.indexOf('.'));
		nomeCaminho += ".monster";
		File arquivo = new File(nomeCaminho);//Inst�ncia do arquivo.


		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diret�rio escolhido.
			writeStream = new FileOutputStream(arquivo);
			writeDataStream = new DataOutputStream(writeStream);
			fileWrite = new FileWriter(arquivo);
			bufferWrite = new BufferedWriter(fileWrite);

			for(char a : dicionario.toCharArray())
				writeDataStream.write(a);

			for(int a : dadosArquivoCodificado){
				writeDataStream.write(a);
			}

			bufferWrite.close();
			fileWrite.close();
			writeDataStream.close();
			writeStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*---------------------------------------------------------------------------------*/
	/**
	 * M�todo respons�vel pela compress�o do arquivo escolhido.
	 * @param caminhoArquivo - Localiza��o do arquivo.
	 */

	public void comprimirArquivo(String caminhoArquivo){

		String dadosArquivo = lerArquivo(caminhoArquivo);
		int[] frequenciaCaractere = calcularFrequencia(dadosArquivo);
		FilaPrioridade filaFrequencia = criarFilaComFrequencias(frequenciaCaractere);
		ArvoreHuffman arvoreHuffman = filaFrequencia.gerarArvoreHuffman();
		String[] dicionario = gerarCodigoHuffman(arvoreHuffman);
		String dadosCodificados = codificaCaractere(dicionario, dadosArquivo.toCharArray());
		int[] dadosCodificadosSubstituidos = substituirCaractere(dadosCodificados);

		/*
		 * Abaixo � criado a String que armazenar� o dicion�rio e o arquivo codificado para escrita.
		 * O tamanho � definido como a soma do tamanho da array de dados + o tamanho do dicionario + dois
		 * caracteres que servir�o para definir o inicio e o fim do dicion�rio.
		 */
		StringBuffer dicionarioCodificado = new StringBuffer();

		dicionarioCodificado.append("{{");// Define o inicio do dicion�rio.

		for(int i = 0; i < dicionario.length; i++){
			if(dicionario[i] != null){

				dicionarioCodificado.append(dicionario[i]);
				dicionarioCodificado.append((char)i);
			}
		}

		dicionarioCodificado.append("}}");// Define o fim do dicion�rio.

		escreverArquivo(dicionarioCodificado.toString(), dadosCodificadosSubstituidos, caminhoArquivo);

	}
	/*---------------------------------------------------------------------------------*/
	/**
	 * M�todo que recebe os dados lidos do arquivo e o dicion�rio e cria o array com os caracteres codificados.
	 * @param dicionario - Dicion�rio com os c�digos.
	 * @param dadosArquivo - Os dados lidos do arquivo.
	 * @return dadosArquivoCodificado - String com a substitui��o dos caracteres.
	 */
	public String codificaCaractere(String[] dicionario, char[] dadosArquivo){

		StringBuffer dadosArquivoCodificado = new StringBuffer();
		for(int i = 0; i < dadosArquivo.length; i++){
			dadosArquivoCodificado.append(dicionario[dadosArquivo[i]]);
		}
		return dadosArquivoCodificado.toString();
	}
	/*---------------------------------------------------------------------------------*/
	public int[] substituirCaractere(String dadosCodificados){
		int[] codigo;
		int tamanho = dadosCodificados.length() / 8, posF = 0;
		char[] dadosArray = dadosCodificados.toCharArray();
		StringBuffer temp;
		if(dadosCodificados.length() % 8 == 0){
			codigo = new int[tamanho];
			for (int i = 0; i < tamanho; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);
			}
		}
		else{
			int i = 0;
			codigo = new int[++tamanho];
			for (i = 0; i < tamanho - 1; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);
			}
			temp = new StringBuffer();
			for(i *= 8; i < dadosArray.length; i++){
				temp.append(dadosArray[i]);
			}
			temp.append("1"); // O n�mero 1 define o fim do documento.
			codigo[tamanho - 1] = Integer.parseInt(temp.toString(), 2);
		}
		return codigo;
	}
	/*---------------------------------------------------------------------------------*/
}
