package util;

import util.Huffman.ArvoreHuffman;
import util.Huffman.CelulaHuffman;

/**
 * Classe fila de prioridade, onde � levada em considera��o a chave de cada n� a ser inserido.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 *
 */
public class FilaPrioridade implements IFilaPrioridade {

	/**
	 * M�todo de inserir com prioridade, onde a chave do n�.
	 * @param key - Chave do n�.
	 * @param object - Objeto a ser armazenado na fila.
	 */

	private int quantidadeNo = 0;// Quantidade de n�'s da fila.
	private No first;//Inicio da fila.

	@Override
	/**
	 * Verifica se a lista esta vazia.
	 * @see util.IFilaPrioridade#estaVazia()
	 * @return true - Caso vazia, false - Caso n�o.
	 */
	public boolean estaVazia() {
		return first == null;// Verifica se o primeiro n� existe.
	}

	/** M�todo que retorna o tamanho da fila.
	 * @see util.IFilaPrioridade#obterTamanho()
	 * @return int - Tamanho da fila.
	 */
	@Override
	public int obterTamanho() {
		return quantidadeNo;// Retorna o tamanho do n�.
	}

	/**
	 * M�todo de inserir com prioridade, onde a chave do n�.
	 * @see util.IFilaPrioridade#inserir(java.lang.Object)
	 * @param key - Chave do n�.
	 * @param object - Objeto a ser armazenado na fila.
	 */
	@Override
	public void inserir(int key, Object o) {
		No novo = new No(key, o);
		if(estaVazia()){// Caso n�o exista objetos na fila.
			first = novo;
			quantidadeNo++;// Incrementa a quantidade de n�'s da fila.
		}
		else{// Vou inserir os n�'s de maiores chaves na frente.
			No aux = first;// Recebo a refer�ncia para o inicio da fila.
			No aux2 = null;
			while(aux != null){
				if(aux.getKey() > key){
					if(aux == first){
						novo.setNext(first);
						first = novo;
					}
					else{
						aux2.setNext(novo);
						novo.setNext(aux);
					}
					quantidadeNo++;
					return;
				}
				else if (aux.getNext() == null){
					aux.setNext(novo);
					quantidadeNo++;
					return;
				}
				aux2 = aux;
				aux = aux.getNext();
			}
		}
	}

	/** M�todo que remove um objeto no inicio da fila.
	 * @see util.IFilaPrioridade#removerInicio().
	 * @return objeto - Objeto removido do inicio.
	 */
	@Override
	public Object removerInicio() {
		Object objeto = null;
		if(estaVazia()){
			return null;
		}
		else{
			objeto = first.getObject();
			first = first.getNext();
			if(quantidadeNo == 1){
				first = null;
			}
			quantidadeNo--;
		}
		return objeto;
	}

	/** M�todo que retorna o primeiro objeto da fila.
	 * @see util.IFilaPrioridade#recuperarInicio()
	 * @return objeto - Primeiro objeto da fila.
	 */
	@Override
	public Object recuperarInicio() {
		Object objeto = first.getObject();// Cria o objeto e recebe a referencia do primeiro.
		return objeto;// Retorna o primeiro objeto da fila.
	}

	/** M�todo que gera e retorna uma �rvore de Huffman.
	 * @return primeiro - Primeiro objeto da fila que cont�m a �rvore de Huffman.
	 */
	public ArvoreHuffman gerarArvoreHuffman(){
		
		ArvoreHuffman primeiro;
		ArvoreHuffman segundo;
		
		while(quantidadeNo >1){
			primeiro = (ArvoreHuffman)removerInicio();
			segundo  = (ArvoreHuffman)removerInicio();
			
			CelulaHuffman celula = new CelulaHuffman();
			celula.setEsquerda(primeiro);
			celula.setDireita(segundo);
			celula.setFrequencia(primeiro.getFrequencia() + segundo.getFrequencia());
			inserir(celula.getFrequencia(), celula);
			
		}
		primeiro = (ArvoreHuffman)removerInicio();
		return primeiro;
	}
	
	/**
	 * Retorna o iterador da fila.
	 * @return iterador - Iterador da fila.
	 */
	public Iterador iterador(){
		Iterador iterador = new Iterador(first);
		return iterador;
	}
}
