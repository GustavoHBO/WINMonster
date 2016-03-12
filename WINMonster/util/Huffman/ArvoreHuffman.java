package util.Huffman;

public class ArvoreHuffman {

	protected ArvoreHuffman(){}
	
	private int frequencia;

	public int getFrequencia() {
		return frequencia;
	}

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
