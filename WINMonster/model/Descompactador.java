package model;

import java.util.Scanner;

import Perssistencia.Fachada;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class Descompactador {



	/*-----------------------------------------------------------------------------------------------------*/

	private static final int NUM = 256;
	/**
	 * Mï¿½todo responsï¿½vel por realizar a descompactaÃ§Ã£o dos arquivos
	 * @param caminhoArquivo - Caminho para o arquivo escolhido.
	 * @throws ArquivoNaoLidoException - Caso nÃ£o seja possÃ­vel ler o arquivo.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo selecionado nï¿½o seja encontrado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo a ser descompactado não possa ser criado.
	 */

	public static void descompactar(String caminhoArquivo) throws ArquivoNaoLidoException, ArquivoNaoEncontradoException, ArquivoNaoCriadoException{
		String dadosCodificados = Fachada.lerArquivo(caminhoArquivo);
		/*
		 * Aqui ï¿½ pego o cï¿½digo do caractere e o caractere que corresponde a este cï¿½digo, criando assim o dicionÃ¡rio.
		 */
		String extensao = dadosCodificados.substring(0, dadosCodificados.indexOf('.') + 4);
		String[] dicionario = dadosCodificados.substring(dadosCodificados.indexOf("{{") + 2, dadosCodificados.indexOf("}}")).split("-");
		dadosCodificados = dadosCodificados.substring(dadosCodificados.indexOf("}}") + 2);// Agora pego apenas o arquivo a ser traduzido.

		StringBuffer textoDecodificado = new StringBuffer();// Onde irï¿½ ser armazenado o texto decodificado.
		String dadosStringBinario = converterStringBinario(dadosCodificados);
		char[] dadosTraduzidosArray = dadosStringBinario.toCharArray();

		StringBuffer temp = new StringBuffer();

		for(int i = 0; i < dadosTraduzidosArray.length; i++){
			temp.append(dadosTraduzidosArray[i]);
			for(int j = 0; j < dicionario.length; j += 2){
				if(dicionario[j].equals(temp.toString())){
					textoDecodificado.append(dicionario[j + 1]);
					temp = new StringBuffer();
					break;
				}
			}
		}
		caminhoArquivo = caminhoArquivo.replace(".monster", extensao);
		Fachada.escreverArquivo(textoDecodificado.toString(), caminhoArquivo);
	}
	private static String traduzirCodigo(char[] dicionario, String dadosStringBinario) {
		String traducao = "";
		StringBuffer buff = new StringBuffer(dadosStringBinario);
		String aux;
		boolean flag;
		while(buff.length() > 0){
			aux = "1";
			flag = true;

			while(flag){
				aux += buff.charAt(0);
				buff.deleteCharAt(0);
				if(funcaoHash(aux) > dicionario.length)break;
				if(dicionario[funcaoHash(aux)] != 0){
					traducao += dicionario[funcaoHash(aux)];
					flag = false;
				}
				if(buff.length() == 0)
					flag = false;
			}
		}
		System.out.println("Código: " + dadosStringBinario);
		System.out.println("Tradução: " + traducao);
		return traducao;

	}
	/*------------------------------------------------------------------------------------------------------------*/
	private static char[] recuperarDicionario(String dadosCodificados) {
		char[] dicionario = new char[NUM*NUM*2];

		StringBuffer buff = new StringBuffer(dadosCodificados);

		System.out.println(buff.toString());
		buff.replace(buff.indexOf(")))"), buff.length(), "");

		Scanner scan = new Scanner(buff.toString());

		scan.useDelimiter("\\" + (char)0b1);
		String aux = "";
		String codigo = "";
		//String aux2 = "";
		//long[] aux2 = new long[1];//TESTE
		int aux2 = 0;
		while(scan.hasNext()){
			aux = scan.next();


			aux2 = aux.charAt(1); //TESTE

			//codigo = transformarBitsEmString(BitSet.valueOf(aux2));//TESTE
			codigo = Integer.toBinaryString(aux2);
			dicionario[funcaoHash(codigo)] = aux.charAt(0);


			System.out.println(dicionario [funcaoHash(codigo)] +" "+ codigo +" "+ aux2);
		}
		scan.close(); 
		return dicionario;
	}
	/*------------------------------------------------------------------------------------------------------------*/
	private static int funcaoHash(String codigo) {
		return Integer.parseInt(codigo, 2);
	}
	/*------------------------------------------------------------------------------------------------------------*/
	public static String converterStringBinario(String dadosCodificados){

		char[] dados = dadosCodificados.toCharArray();
		StringBuffer dadosTraduzidos = new StringBuffer();

		String temp = "";
		for(int i = 0; i < dados.length; i++){
			temp = Integer.toBinaryString(dados[i]);
			for(int j = 0; j < 8 - temp.length(); j++){
				dadosTraduzidos.append("0");
			}
			dadosTraduzidos.append(temp);
			if(i + 1 == dados.length){
				System.out.println(temp);
			}
		}
		return dadosTraduzidos.toString();
	}
}
