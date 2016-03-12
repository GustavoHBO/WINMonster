package util.Huffman;

/**
 * Célula de Huffman, responsável por mostrar a soma de repetições das folhas de Huffman.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 */

public class CelulaHuffman extends ArvoreHuffman{

	private ArvoreHuffman esquerda;
	private ArvoreHuffman direita;
	
	/**
	 * Retorna a referência do objeto a esquerda, pode ser uma célula ou uma folha de Huffman.
	 * @return esquerda - Referencia para objeto a esquerda.
	 */
	public ArvoreHuffman getEsquerda() {
		return esquerda;
	}
	
	/**
	 * Altera a referência do objeto a esquerda, pode ser uma célula ou uma folha de Huffman.
	 * @param esquerda - Referencia para objeto a esquerda.
	 */
	public void setEsquerda(ArvoreHuffman esquerda) {
		this.esquerda = esquerda;
	}
	
	/**
	 * Retorna a referência do objeto a direita, pode ser uma célula ou uma folha de Huffman.
	 * @return direita - Referencia para objeto a direita.
	 */
	public ArvoreHuffman getDireita() {
		return direita;
	}
	
	/**
	 * Altera a referência do objeto a direita, pode ser uma célula ou uma folha de Huffman.
	 * @param direita - Referencia para objeto a direita.
	 */
	public void setDireita(ArvoreHuffman direita) {
		this.direita = direita;
	}
	
}
