package controller;

import java.util.BitSet;

import util.FilaPrioridade;
import util.Huffman.ArvoreHuffman;
import util.Huffman.FolhaHuffman;

public class ControllerCompactar {
	
	private static final int NUM = 256;
	private static ControllerCompactar instance = new ControllerCompactar();

	private ControllerCompactar(){}

	public static ControllerCompactar getInstance(){
		if(instance == null)
			instance = new ControllerCompactar();

		return instance;
	}

	public static void zerarSingleton(){
		instance = new ControllerCompactar();
	}
	/*---------------------------------------------------------------------------------*/
	private static int[] calcularFrequencia(String texto){
		int[] frequencias = new int[NUM*NUM];
		char[] caracteres = texto.toCharArray();

		for (int i = 0; i < caracteres.length; i++)
			frequencias[caracteres[i]]++;

		return frequencias;
	}
	/*---------------------------------------------------------------------------------*/
	private static FilaPrioridade criarFilaComFrequencias(int[] frequencias){
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
	private static String[] gerarCodigoHuffman(ArvoreHuffman arvore){
		String[] dicionario = new String[NUM*NUM];

		ArvoreHuffman.construirCodigo(dicionario, arvore, "");
		
		return dicionario;
	}
	/*---------------------------------------------------------------------------------*/
	private static byte[] escreverCodigo(String[] dicionario, String texto){
		String txtCompact = "";
		String dicioCompact = "";
		long[] aux;
		
		for(int i =0; i < texto.length(); i++){
			txtCompact += dicionario[texto.charAt(i)];
		}
		System.out.println("AQUI O TEXTO:" + txtCompact);
		
		for(int i =0; i < dicionario.length ; i++){
			if(dicionario[i] != null){
				
				dicioCompact += (char)i;
				aux = transformarEmBits(dicionario[i]).toLongArray();
				
				dicioCompact += (char)aux[0];
				System.out.println(aux[0]);
				dicioCompact += (char)0b1;
				
			}
		}
		System.out.println(dicioCompact);
		dicioCompact += ")))";
		
		byte[] txtBits = transformarEmBits(txtCompact).toByteArray();
		byte[] dicioBits = dicioCompact.getBytes();;
		
		byte[] txtTotal = new byte[txtBits.length + dicioBits.length];
		
		for(int i = 0; i< dicioBits.length; i++){
			txtTotal[i] = dicioBits[i];
		}
		for(int i = dicioBits.length; i< dicioBits.length + txtBits.length; i++){
			txtTotal[i] = txtBits[i - dicioBits.length];
		}
		
		return txtTotal;		

	}
	/*---------------------------------------------------------------------------------*/
	private static BitSet transformarEmBits(String txt) {
		
		BitSet sequenciaBits = new BitSet(txt.length()+1);
		sequenciaBits.clear();
		
		for(int i = 0; i < txt.length(); i++){
			
			if(txt.charAt(i) == '1'){
				sequenciaBits.set(txt.length() - i - 1);
			}else if(txt.charAt(i) == '0'){
				sequenciaBits.set(txt.length() - i - 1, false);
			}
		}
		
		sequenciaBits.set(txt.length());
		
		return sequenciaBits;
	}
	/*---------------------------------------------------------------------------------*/
	public static void comprimirArquivo(String caminhoArquivo, String novoArquivo){

		String dadosArquivo = ControllerArquivo.lerArquivo(caminhoArquivo);
		System.out.println(dadosArquivo);
		int[] frequenciaCaractere = calcularFrequencia(dadosArquivo);
		FilaPrioridade filaFrequencia = criarFilaComFrequencias(frequenciaCaractere);
		ArvoreHuffman arvoreHuffman = filaFrequencia.gerarArvoreHuffman();
		String[] dicionario = gerarCodigoHuffman(arvoreHuffman);

		/*
		 * Abaixo é criado a String que armazenará o dicionário e o arquivo codificado para escrita.
		 * O tamanho é definido como a soma do tamanho da array de dados + o tamanho do dicionario + dois
		 * caracteres que servirão para definir o inicio e o fim do dicionário.
		 */
		ControllerArquivo.escreverArquivo(escreverCodigo(dicionario, dadosArquivo), novoArquivo);

	}
	/*---------------------------------------------------------------------------------*/
}
