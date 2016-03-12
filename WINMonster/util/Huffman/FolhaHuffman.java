package util.Huffman;

/**
 * Folha de Huffman, responsável por armazenar o caractere.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 */
public class FolhaHuffman extends ArvoreHuffman{

	private char info;

	/**
	 * Método responsável por retornar o caractere armazenado na FolhaHuffman.
	 * @return info - Caractere armazenado.
	 */
	public char getInfo() {
		return info;
	}

	/**
	 * Altera o caractere armazenado na FolhaHuffman.
	 * @param info - Novo caractere a ser armazenado.
	 */
	public void setInfo(char info) {
		this.info = info;
	}
	
}
