package Perssistencia;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class Fachada implements SalvarArquivo, LerArquivo {

	
	/*--------------------------------------------------------------------------------------------------------------*/
	/**
	 * Método responsável pela escrita dos arquivos.
	 * @param dicionario - Dicionário para tradução do arquivo.
	 * @param dadosArquivoCodificado - Dados do arquivo codificado.
	 * @param caminhoArquivo - Caminho ao qual o arquivo será armazenado.
	 * @throws ArquivoNaoCriadoException - Caso o arquivo não possa ser criado.
	 */
	public static void escreverArquivo(String dicionario, int[] dadosArquivoCodificado,  String caminhoArquivo) throws ArquivoNaoCriadoException{

		FileOutputStream writeStream = null;
		DataOutputStream writeDataStream = null;
		// Aqui é especificado o caminho e o nome do arquivo.
		String nomeCaminho = caminhoArquivo.substring(0, caminhoArquivo.lastIndexOf('.'));
		nomeCaminho += ".monster";
		File arquivo = new File(nomeCaminho);//Instância do arquivo.


		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			writeStream = new FileOutputStream(arquivo);
			writeDataStream = new DataOutputStream(writeStream);

			for(char a : dicionario.toCharArray())
				writeDataStream.write(a);

			for(int a : dadosArquivoCodificado){
				writeDataStream.write(a);
			}
			writeDataStream.close();
			writeStream.close();
		} catch (IOException e) {
			throw new ArquivoNaoCriadoException();// Caso arquivo não tenha sido criado.
		}
	}
	/*------------------------------------------------------------------------------------------------------------------*/
	/**
	 * Método responsável pela leitura do arquivo e retorna uma String com os dados lidos.
	 * @param arquivo - Diretório do arquivo a ser lido.
	 * @return dados - String com os caracteres lidos no arquivo.
	 * @throws ArquivoNaoEncontradoException - Caso o arquivo a ser lido não exista.
	 * @throws ArquivoNaoLidoException - Caso o arquivo não possa ser lido.
	 */

	public static String lerArquivo(String arquivo) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		StringBuffer dados = new StringBuffer();// String onde será armazenada as informações lidas.
		FileReader arq = null;// Instância do arquivo.
		BufferedReader buffer = null;// Instância do leitor do arquivo.

		try {// Ver como será tratado esse erro.
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);


			while (buffer.ready()){//Irá ser valido até encontrar o fim do arquivo.
				dados.append(buffer.readLine());// Lê linha por linha no arquivo e concatena no final da string dados.
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
