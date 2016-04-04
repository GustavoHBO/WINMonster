package controller;

import java.util.BitSet;
import java.util.Scanner;

public class ControllerDescompactar {
	
	private static final int NUM = 256;
	private static ControllerDescompactar instance = new ControllerDescompactar();

	private ControllerDescompactar(){}

	public static ControllerDescompactar getInstance(){
		if(instance == null)
			instance = new ControllerDescompactar();

		return instance;
	}

	public static void zerarSingleton(){
		instance = new ControllerDescompactar();
	}
	/*---------------------------------------------------------------------------------*/
	public static void descomprimirArquivo(String caminhoArquivo, String novoArquivo){
		String dadosArquivo = ControllerArquivo.lerArquivo(caminhoArquivo);
		System.out.println(dadosArquivo);
		
		String traducao = traduzirCodigo(dadosArquivo);
		ControllerArquivo.escreverArquivo(traducao.getBytes(), novoArquivo);
	}
	/*---------------------------------------------------------------------------------*/
	private static String traduzirCodigo(String dadosArquivo) {
		char[] dicionario = recuperarDicionario(dadosArquivo);
		
		String texto = recuperarTexto(dadosArquivo);
		//System.out.println("Tamanho do arquivo: " + texto.getBytes().length);
		
		
				
		BitSet textoBits = BitSet.valueOf(texto.getBytes());
		String textoCodigo = transformarBitsEmString(textoBits);
		String traducao = tarduzirTexto(dicionario, textoCodigo);
		
		return traducao;
	}
	/*---------------------------------------------------------------------------------*/
	private static String recuperarTexto(String dadosArquivo) {
		StringBuffer buff = new StringBuffer(dadosArquivo);
		
		buff.replace(0 ,buff.indexOf(")))")+3, "");
		
		
		return buff.toString();
	}
	/*---------------------------------------------------------------------------------*/
	private static String tarduzirTexto(char[] dicionario, String textoCodigo) {
		String traducao = "";
		StringBuffer buff = new StringBuffer(textoCodigo);
		String aux;
		boolean flag;
		buff.deleteCharAt(0);
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
		System.out.println("Código: " + textoCodigo);
		System.out.println("Tradução: " + traducao);
		return traducao;
	}
	/*---------------------------------------------------------------------------------*/
	private static char[] recuperarDicionario(String dadosArquivo) {
		char[] dicionario = new char[NUM*NUM*2];
		
		StringBuffer buff = new StringBuffer(dadosArquivo);
		
		System.out.println(buff.toString());
		buff.replace(buff.indexOf(")))"), buff.length(), "");
		
		Scanner scan = new Scanner(buff.toString());
		
		scan.useDelimiter("\\" + (char)0b1);
		String aux = "";
		String codigo = "";
		//String aux2 = "";
		long[] aux2 = new long[1];//TESTE
		while(scan.hasNext()){
			aux = scan.next();
						
			
			aux2[0] = aux.charAt(1); //TESTE
			
			codigo = transformarBitsEmString(BitSet.valueOf(aux2));//TESTE
			dicionario[funcaoHash(codigo)] = aux.charAt(0);
			
			
			System.out.println(dicionario [funcaoHash(codigo)] +" "+ codigo +" "+ aux2[0]);
		}
		scan.close(); 
		return dicionario;
		
	}
	/*---------------------------------------------------------------------------------*/
	private static String transformarBitsEmString(BitSet bits) {
		String stringCod = "";
		for(int i = 0; i < bits.length(); i++){
		
			if(bits.get(i)){
				
				stringCod += '1';
			}else{
				stringCod += '0';
			}
			
		}
		StringBuffer buff = new StringBuffer(stringCod);
		return buff.reverse().toString();
	}
	/*---------------------------------------------------------------------------------*/
	private static int funcaoHash(String codigo){
		
		int numero = 0;
		for(int i = codigo.length(); i> 0; i--){
			if(codigo.charAt(codigo.length() - i) == '1'){
				numero += Math.pow(2,i);
			}
		}
		return numero;
	}
	/*---------------------------------------------------------------------------------*/
}
