package controller;

import Perssistencia.Fachada;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
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
	/*-----------------------------------------------------------------------------------------------------*/
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
	/*-----------------------------------------------------------------------------------------------------*/
	public static int[] calcularFrequencia(String texto){
		int[] frequencias = new int[NUM];
		char[] caracteres = texto.toCharArray();

		for (int i = 0; i < caracteres.length; i++){
			frequencias[caracteres[i]]++;
		}
		return frequencias;
	}
	/*-----------------------------------------------------------------------------------------------------*/

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
	/*-----------------------------------------------------------------------------------------------------*/

	public static String[] gerarCodigoHuffman(ArvoreHuffman arvore){
		String[] dicionario = new String[NUM];

		ArvoreHuffman.construirCodigo(dicionario, arvore, "");

		return dicionario;
	}
	/*-----------------------------------------------------------------------------------------------------*/
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
	 * M�todo respons�vel pela compress�o do arquivo escolhido.
	 * @param caminhoArquivo - Localiza��o do arquivo a ser comprimido.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo compactado n�o possa ser criado.
	 * @throws ArquivoNaoLidoException - Caso o arquivo escolhido pelo usu�rio n�o possa ser lido.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido pelo usu�rio n�o seja encontrado.
	 */

	public void comprimirArquivo(String caminhoArquivo) throws ArquivoNaoCriadoException, ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		String dadosArquivo = Fachada.lerArquivo(caminhoArquivo);// Aqui � feita a leitura do arquivo.
		int[] frequenciaCaractere = calcularFrequencia(dadosArquivo);// Aqui � calculada a frequ�ncia de cada caractere do texto lido.
		FilaPrioridade filaFrequencia = criarFilaComFrequencias(frequenciaCaractere);// Cria a fila de prioridade onde a frequ�ncia � considerada a chave.
		ArvoreHuffman arvoreHuffman = filaFrequencia.gerarArvoreHuffman();// Cria a �rvore de Huffman.
		String[] dicionario = gerarCodigoHuffman(arvoreHuffman);// Aqui � criado o dicion�rio para cada caractere do texto lido.
		String dadosCodificados = codificaCaractere(dicionario, dadosArquivo.toCharArray());// A partir do dicion�rio � criado o arquivo codificado.
		int[] dadosCodificadosSubstituidos = substituirCaractere(dadosCodificados);// Aqui o c�digo � transformado em um array de inteiro para ocupar pouco espa�o.

		/*
		 * Abaixo � criado a String que armazenar� o dicion�rio e o arquivo codificado para escrita.
		 * O tamanho � definido como a soma do tamanho da array de dados + o tamanho do dicion�rio + dois
		 * caracteres que servir�o para definir o inicio e o fim do dicion�rio.
		 */
		StringBuffer dicionarioCodificado = new StringBuffer();

		dicionarioCodificado.append("{{");// Define o inicio do dicion�rio.

		for(int i = 0; i < dicionario.length; i++){
			if(dicionario[i] != null){
				dicionarioCodificado.append(dicionario[i]);//Aqui � armazenado o c�digo do byte correspondente ao caractere.
				dicionarioCodificado.append((char)i);// Aqui � escrito o caractere para poder reescrever o arquivo novamente.
			}
		}

		dicionarioCodificado.append("}}");// Define o fim do dicion�rio.

		Fachada.escreverArquivo(dicionarioCodificado.toString(), dadosCodificadosSubstituidos, caminhoArquivo);

	}
	/*-----------------------------------------------------------------------------------------------------*/
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
	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * M�todo respons�vel por receber os dados codificados e transforma-los em um array de inteiros.
	 * @param dadosCodificados - Dados do arquivo lido codificado.
	 * @return codigo - Array com c�digos para ser escrito no arquivo.
	 */
	public int[] substituirCaractere(String dadosCodificados){
		int[] codigo;
		int tamanho = (dadosCodificados.length() / 8) + 1;
		char[] dadosArray = dadosCodificados.toCharArray();
		StringBuffer temp;
		if(dadosCodificados.length() % 8 == 0){// Caso o arquivo lido seja divis�vel por 8 n�o haver� sobra de bits.
			codigo = new int[tamanho];
			for (int i = 0; i < tamanho - 1; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
			}
			codigo[tamanho-1] = 128;// Adiciono um byte para definir o final do meu arquivo.
		}
		else{//Caso n�o seja divis�vel por 8 haver� sobra de bits, isto tem que ser tratado de forma diferente.
			int i = 0;
			codigo = new int[tamanho];
			for (i = 0; i < tamanho - 1; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
			}
			temp = new StringBuffer();
			for(i *= 8; i < dadosArray.length; i++){// Aqui � copiado o restante do arquivo.
				temp.append(dadosArray[i]);
			}
			temp.append("1"); // O n�mero 1 define o fim do documento.
			codigo[tamanho - 1] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
		}
		return codigo;
	}
	/*-----------------------------------------------------------------------------------------------------*/
	
	/**
	 * M�todo respons�vel por realizar a descompacta��o dos arquivos
	 * @param caminhoArquivo - Caminho para o arquivo escolhido.
	 * @throws ArquivoNaoLidoException - Caso n�o seja possivel ler o arquivo.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo selecionado n�o seja encontrado.
	 */
	
	public void descompactar(String caminhoArquivo) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{
		String dadosCodificados = Fachada.lerArquivo(caminhoArquivo);
		String dicionario = dadosCodificados.substring(dadosCodificados.indexOf("{{") + 1, dadosCodificados.indexOf("}}"));
		
	}
}
