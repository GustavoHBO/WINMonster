package util.Huffman;

import java.io.Serializable;

/**
 * �rvore de Huffman, respons�vel pelo armazenamento dos caracteres e defini��o dos c�digos.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 *
 */
public class ArvoreHuffman implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ArvoreHuffman(){}

	private int frequencia;// A freq��ncia � estabelecida pela soma das quantidades de repeti��es das folhas.

	/**
	 * Retorna a soma das ocorr�ncias dos caracteres das folhas de Huffman.
	 * @return frequencia - Freq��ncia dos caracteres folhas.
	 */
	public int getFrequencia() {
		return frequencia;
	}

	/**
	 * Altera a soma das ocorr�ncias dos caracteres das folhas de Huffman.
	 * @param frequencia - Freq��ncia dos caracteres folhas.
	 */
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public static void construirCodigo(String[] dicionario, ArvoreHuffman arvore, String codigo) {
		if (arvore instanceof CelulaHuffman) {
			CelulaHuffman celula = (CelulaHuffman)arvore;
			construirCodigo(dicionario, celula.getEsquerda(),  codigo + '0');
			construirCodigo(dicionario, celula.getDireita(),  codigo + '1');
		}
		else if(arvore instanceof FolhaHuffman){
			FolhaHuffman folha = (FolhaHuffman)arvore;
			dicionario[folha.getInfo()] = codigo;
		}
	}
}
