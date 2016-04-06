package Perssistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class Fachada implements SalvarArquivo, LerArquivo {


	/*--------------------------------------------------------------------------------------------------------------*/
	/**
	 * M�todo respons�vel pela escrita dos arquivos.
	 * @param dicionario - Dicionário para tradução do arquivo.
	 * @param dadosArquivoCodificado - Dados do arquivo codificado.
	 * @param caminhoArquivo - Caminho ao qual o arquivo ser� armazenado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo n�o possa ser criado.
	 */
	public static void escreverArquivo(String dicionario, int[] dadosArquivoCodificado,  String caminhoArquivo) throws ArquivoNaoCriadoException{

		FileWriter fileWrite = null;
		BufferedWriter bufferWrite = null;
		
		// Aqui é especificado o caminho e o nome do arquivo.
		String nomeCaminho = caminhoArquivo.substring(0, caminhoArquivo.lastIndexOf('.'));
		nomeCaminho += ".monster";
		File arquivo = new File(nomeCaminho);//Instância do arquivo.


		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			fileWrite = new FileWriter(arquivo);
			bufferWrite = new BufferedWriter(fileWrite);
			
			for(char a : dicionario.toCharArray())
				bufferWrite.write(a);

			for(int a : dadosArquivoCodificado){
				bufferWrite.write(a);
			}
			
			bufferWrite.close();
			fileWrite.close();
		} catch (IOException e) {
			throw new ArquivoNaoCriadoException();// Caso arquivo não tenha sido criado.
		}
	}
	/*------------------------------------------------------------------------------------------------------------------*/
	
	/**
	 * Metodo respons�vel pela escrita de arquivos em um determinado arquivo.
	 * @param dados - Dados a serem gravados.
	 * @param caminho - Diret�rio at� o arquivo a ser gravado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo a ser gravado n�o possa ser criado.
	 */
	public static void escreverArquivo(String dados, String caminho) throws ArquivoNaoCriadoException{
		FileWriter fileWrite = null;
		BufferedWriter bufferWrite = null;
		
		// Aqui é especificado o caminho e o nome do arquivo.
		File arquivo = new File(caminho);//Instância do arquivo.


		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			fileWrite = new FileWriter(arquivo);
			bufferWrite = new BufferedWriter(fileWrite);

			for(int a : dados.toCharArray()){
				bufferWrite.write(a);
			}
			
			bufferWrite.close();
			fileWrite.close();
		} catch (IOException e) {
			throw new ArquivoNaoCriadoException();// Caso arquivo não tenha sido criado.
		}
	}
	/*------------------------------------------------------------------------------------------------------------------*/
	/**
	 * M�todo respons�vel pela leitura do arquivo e retorna uma String com os dados lidos.
	 * @param arquivo - Diret�rio do arquivo a ser lido.
	 * @return dados - String com os caracteres lidos no arquivo.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo a ser lido n�o exista.
	 * @throws ArquivoNaoLidoException - Caso o arquivo n�o possa ser lido.
	 */

	public static String lerArquivo(String arquivo) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		StringBuffer dados = new StringBuffer();// String onde ser� armazenada as informa��es lidas.
		FileReader arq = null;// Inst�ncia do arquivo.
		BufferedReader buffer = null;// Inst�ncia do leitor do arquivo.

		try {// Ver como ser� tratado esse erro.
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);

			while (buffer.ready()){//Ir� ser valido at� encontrar o fim do arquivo.
				dados.append((char)buffer.read());// L� linha por linha no arquivo e concatena no final da string dados.
			}
			buffer.close();// Finalizo o leitor do arquivo.
			arq.close();// Finalizo o arquivo.
		} catch (FileNotFoundException e) {
			throw new ArquivoNaoEncontradoException();
		} catch (IOException e) {
			throw new ArquivoNaoLidoException();
		}
		return dados.toString();
	}

	public static String lerArquivoComprimido(String arquivo) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{
		StringBuffer dados = new StringBuffer();// String onde ser� armazenada as informa��es lidas.
		FileReader arq = null;// Inst�ncia do arquivo.
		BufferedReader buffer = null;// Inst�ncia do leitor do arquivo.

		try {// Ver como ser� tratado esse erro.
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);

			while(buffer.ready()){
				dados.append(buffer.readLine());
				if(buffer.ready())
					dados.append('\n');
			}

			buffer.close();// Finalizo o leitor do arquivo.
			arq.close();// Finalizo o arquivo.
		} catch (FileNotFoundException e) {
			throw new ArquivoNaoEncontradoException();
		} catch (IOException e) {
			throw new ArquivoNaoLidoException();
		}
		return dados.toString();
	}
}
