package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/**
 * Classe controller, responsável pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de março de 2016.
 *
 */
public class ControllerArquivo {
	/*---------------------------------------------------------------------------------*/
	private static ControllerArquivo instance = new ControllerArquivo();

	private ControllerArquivo(){}

	public static ControllerArquivo getInstance(){
		if(instance == null)
			instance = new ControllerArquivo();

		return instance;
	}

	public static void zerarSingleton(){
		instance = new ControllerArquivo();
		
		
	}
	/*---------------------------------------------------------------------------------*/
	/**
	 * Método responsável pela leitura do arquivo e retorna um array dos dados lidos.
	 * @param arquivo - Nome do arquivo a ser lido.
	 * @return dados - Array dos caracteres lidos no arquivo.
	 */

	public static String lerArquivo(String arquivo){

		StringBuffer dados = new StringBuffer();// String onde será armazenada as informações lidas.
		
		FileReader arq = null;// Instância do arquivo.
		BufferedReader buffer = null;// Instância do leitor do arquivo.

		
		try {// Ver como será tratado esse erro.
			arq = new FileReader(arquivo);
			
			buffer = new BufferedReader(arq);

			
			while (buffer.ready()){//Irá ser valido até encontrar o fim do arquivo.
				
				dados.append((char)(buffer.read()));
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
	 * Método responsável pela escrita dos arquivos seguindo o algoritmo de Huffman.
	 * @param arrayCaractere - Array com os códigos dos caracteres.
	 * @param nomeArquivo - Nome do arquivo a ser gravado.
	 * @param caminhoArquivo - Caminho ao qual o arquivo será armazenado.
	 */
	public static void escreverArquivo(byte[] texto,  String caminhoArquivo){

		FileOutputStream writeStream = null;
		DataOutputStream writeDataStream = null;
		File arquivo = new File(caminhoArquivo);//Instância do arquivo.
		
		try {// Ver como vai ser tratado esse tipo de erro.
			arquivo.createNewFile();//Crio o arquivo no diretório escolhido.
			
			writeStream = new FileOutputStream(arquivo);
			
			writeDataStream = new DataOutputStream(writeStream);
			
			writeDataStream.write(texto);
			
			writeDataStream.flush();
			writeDataStream.close();
			writeStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*---------------------------------------------------------------------------------*/
	
}
