package model;

/**
 * Classe que é responsável pela criação do método que cria o código de integridade.
 * @author Gustavo Henrique && Leonardo Melo.
 *
 */
public class VerificaIntegridade {

	/**
	 * Método que cria um código a partir da string recebida.
	 * @param dados - String que será gerado o código.
	 * @return codigo - Código para verificar a integridade.
	 */
	public static double criarCodigo(String dados){
		
		double codigo = 0;
		for(int i = 0; i < dados.length(); i++){// Soma o valor de todos caracteres do texto.
			codigo += (int)dados.toCharArray()[i];
		}
		codigo %= dados.length();// Recebe o resto de divisão da soma de todos os caracteres pela quantidade de caracteres da strinh.
		return codigo;
	}
}
