package model;

import Perssistencia.Fachada;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
import util.FilaPrioridade;
import util.Huffman.ArvoreHuffman;
import util.Huffman.FolhaHuffman;

public class Compactador {

	private final static int NUM = 65536;

	/*------------------------------------------------------------------------------------------------*/
	/**
	 * M�todo responsável pela compressão do arquivo escolhido.
	 * @param caminhoArquivo - Localização do arquivo a ser comprimido.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo compactado n�o possa ser criado.
	 * @throws ArquivoNaoLidoException - Caso o arquivo escolhido pelo usuário não possa ser lido.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo escolhido pelo usu�rio n�o seja encontrado.
	 */

	public static void comprimirArquivo(String caminhoArquivo) throws ArquivoNaoCriadoException, ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		String dadosArquivo = Fachada.lerArquivo(caminhoArquivo);// Aqui é feita a leitura do arquivo.
		int[] frequenciaCaractere = calcularFrequencia(dadosArquivo);// Aqui a calculada a frequência de cada caractere do texto lido.
		FilaPrioridade filaFrequencia = criarFilaComFrequencias(frequenciaCaractere);// Cria a fila de prioridade onde a frequência � considerada a chave.
		ArvoreHuffman arvoreHuffman = filaFrequencia.gerarArvoreHuffman();// Cria a �rvore de Huffman.
		String[] dicionario = gerarCodigoHuffman(arvoreHuffman);// Aqui � criado o dicionário para cada caractere do texto lido.

		String extensao = caminhoArquivo.substring(caminhoArquivo.lastIndexOf('.'));

		String dadosCodificados = codificaCaractere(dicionario, dadosArquivo.toCharArray());// A partir do dicionário � criado o arquivo codificado.
		int[] dadosCodificadosSubstituidos = substituirCaractere(dadosCodificados);// Aqui o c�digo � transformado em um array de inteiro para ocupar pouco espa�o.
		String dicionarioCodificado = codificarDicionario(dicionario);
		Fachada.escreverArquivo(extensao + dicionarioCodificado, dadosCodificadosSubstituidos, caminhoArquivo);

	}
	/*-----------------------------------------------------------------------------------------------------*/
	private static String codificarDicionario(String[] dicionario) {
		/*
		 * Abaixo � criado a String que armazenar� o dicionário e o arquivo codificado para escrita.
		 * O tamanho � definido como a soma do tamanho da array de dados + o tamanho do dicionário + dois
		 * caracteres que servir�o para definir o inicio e o fim do dicionário.
		 */
		StringBuffer dicionarioCodificado = new StringBuffer();

		dicionarioCodificado.append("{{");// Define o inicio do dicionário.

		for(int i = 0; i < dicionario.length; i++){
			if(dicionario[i] != null){
				if(dicionarioCodificado.length() != 2){// S� ir� adicionar o '-' quando n�o tiver apenas o "{{".
					dicionarioCodificado.append('-');//Define uma separa��o entre um c�digo e o caractere.
				}
				dicionarioCodificado.append(dicionario[i]);//Aqui � armazenado o c�digo do byte correspondente ao caractere.
				dicionarioCodificado.append('-');//Define uma separa��o entre um c�digo e o caractere.
				dicionarioCodificado.append((char)i);// Aqui � escrito o caractere para poder reescrever o arquivo novamente.

			}
		}

		dicionarioCodificado.append("}}");// Define o fim do dicionário.
		return dicionarioCodificado.toString();
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
	/**
	 * Método que recebe os dados lidos do arquivo e o dicionário e cria o array com os caracteres codificados.
	 * @param dicionario - Dicionário com os códigos.
	 * @param dadosArquivo - Os dados lidos do arquivo.
	 * @return dadosArquivoCodificado - String com a substitui��o dos caracteres.
	 */
	public static String codificaCaractere(String[] dicionario, char[] dadosArquivo){

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
	public static int[] substituirCaractere(String dadosCodificados){
		int[] codigo;
		int tamanho = (dadosCodificados.length() / 8);
		char[] dadosArray = dadosCodificados.toCharArray();
		StringBuffer temp;
		if(dadosCodificados.length() % 8 == 0){// Caso o arquivo lido seja divis�vel por 8 n�o haver� sobra de bits.
			codigo = new int[tamanho];
			for (int i = 0; i < tamanho ; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
			}
		}
		else{//Caso n�o seja divis�vel por 8 haver� sobra de bits, isto tem que ser tratado de forma diferente.
			int i = 0;
			codigo = new int[++tamanho];
			for (i = 0; i < tamanho - 1; i++){
				temp = new StringBuffer();
				for(int j = i * 8; j < i * 8 + 8; j++){
					temp.append(dadosArray[j]);
				}
				codigo[i] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
			}
			temp = new StringBuffer();
			int j = i * 8;
			for(; j < dadosArray.length; j++){// Aqui � copiado o restante do arquivo.
				temp.append(dadosArray[j]);
			}
			codigo[tamanho - 1] = Integer.parseInt(temp.toString(), 2);// Aqui transformo a String de bin�rio para um valor inteiro.
		}
		return codigo;
	}
}
