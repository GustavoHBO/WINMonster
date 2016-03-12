package util.Huffman;

/**
 * C�lula de Huffman, respons�vel por mostrar a soma de repeti��es das folhas de Huffman.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 */

public class CelulaHuffman extends ArvoreHuffman{

	private ArvoreHuffman esquerda;
	private ArvoreHuffman direita;
	
	/**
	 * Retorna a refer�ncia do objeto a esquerda, pode ser uma c�lula ou uma folha de Huffman.
	 * @return esquerda - Referencia para objeto a esquerda.
	 */
	public ArvoreHuffman getEsquerda() {
		return esquerda;
	}
	
	/**
	 * Altera a refer�ncia do objeto a esquerda, pode ser uma c�lula ou uma folha de Huffman.
	 * @param esquerda - Referencia para objeto a esquerda.
	 */
	public void setEsquerda(ArvoreHuffman esquerda) {
		this.esquerda = esquerda;
	}
	
	/**
	 * Retorna a refer�ncia do objeto a direita, pode ser uma c�lula ou uma folha de Huffman.
	 * @return direita - Referencia para objeto a direita.
	 */
	public ArvoreHuffman getDireita() {
		return direita;
	}
	
	/**
	 * Altera a refer�ncia do objeto a direita, pode ser uma c�lula ou uma folha de Huffman.
	 * @param direita - Referencia para objeto a direita.
	 */
	public void setDireita(ArvoreHuffman direita) {
		this.direita = direita;
	}
	
}
