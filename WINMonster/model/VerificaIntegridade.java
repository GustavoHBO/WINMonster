package model;

/**
 * Classe que � respons�vel pela cria��o do m�todo que cria o c�digo de integridade.
 * @author Gustavo Henrique && Leonardo Melo.
 *
 */
public class VerificaIntegridade {

	/**
	 * M�todo que cria um c�digo a partir da string recebida.
	 * @param dados - String que ser� gerado o c�digo.
	 * @return codigo - C�digo para verificar a integridade.
	 */
	public static double criarCodigo(String dados){
		
		double codigo = 0;
		for(int i = 0; i < dados.length(); i++){// Soma o valor de todos caracteres do texto.
			codigo += (int)dados.toCharArray()[i];
		}
		codigo %= dados.length();// Recebe o resto de divis�o da soma de todos os caracteres pela quantidade de caracteres da strinh.
		return codigo;
	}
}
