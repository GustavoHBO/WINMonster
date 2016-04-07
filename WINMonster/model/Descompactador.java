package model;

import Perssistencia.Fachada;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class Descompactador {



	/*-----------------------------------------------------------------------------------------------------*/

	/**
	 * M�todo respons�vel por realizar a descompacta��o dos arquivos
	 * @param caminhoArquivo - Caminho para o arquivo escolhido.
	 * @throws ArquivoNaoLidoException - Caso não seja possível ler o arquivo.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo selecionado n�o seja encontrado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo a ser descompactado n�o possa ser criado.
	 */

	public static void descompactar(String caminhoArquivo) throws ArquivoNaoLidoException, ArquivoNaoEncontradoException, ArquivoNaoCriadoException{
		String dadosCodificados = Fachada.lerArquivo(caminhoArquivo);
		/*
		 * Aqui � pego o c�digo do caractere e o caractere que corresponde a este c�digo, criando assim o dicionário.
		 */
		String extensao = dadosCodificados.substring(0, dadosCodificados.indexOf('.') + 4);
		
		String[] dicionario = dadosCodificados.substring(dadosCodificados.indexOf("{{") + 2, dadosCodificados.indexOf("}}")).split("-");
		dadosCodificados = dadosCodificados.substring(dadosCodificados.indexOf("}}") + 2);// Agora pego apenas o arquivo a ser traduzido.

		StringBuffer textoDecodificado = new StringBuffer();// Onde ir� ser armazenado o texto decodificado.
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
		}
		temp = dadosTraduzidos.substring(0, dadosTraduzidos.lastIndexOf("1"));// O fim do arquivo � encontrado e � retirado tudo ap�s ele.
		return temp;
	}
}
