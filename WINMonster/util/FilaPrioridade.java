package util;

import util.Huffman.ArvoreHuffman;
import util.Huffman.CelulaHuffman;

/**
 * Classe fila de prioridade, onde é levada em consideração a chave de cada nó a ser inserido.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 *
 */
public class FilaPrioridade implements IFilaPrioridade {

	private int quantidadeNo = 0;// Quantidade de nó's da fila.
	private No first;//Inicio da fila.

	@Override
	/**
	 * Verifica se a lista esta vazia.
	 * @see util.IFilaPrioridade#estaVazia()
	 * @return true - Caso vazia, false - Caso não.
	 */
	public boolean estaVazia() {
		return first == null;// Verifica se o primeiro nó existe.
	}

	/** Método que retorna o tamanho da fila.
	 * @see util.IFilaPrioridade#obterTamanho()
	 * @return int - Tamanho da fila.
	 */
	@Override
	public int obterTamanho() {
		return quantidadeNo;// Retorna o tamanho do nó.
	}

	/**
	 * M�todo de inserir com prioridade.
	 * @see util.IFilaPrioridade#inserir(java.lang.Object)
	 * @param key - Chave do nó.
	 * @param object - Objeto a ser armazenado na fila.
	 */
	@Override
	public void inserir(int key, Object o) {
		No novo = new No(key, o);
		if(estaVazia()){// Caso não exista objetos na fila.
			first = novo;
			quantidadeNo++;// Incrementa a quantidade de nó's da fila.
		}
		else{// Vou inserir os nó's de maiores chaves na frente.
			No aux = first;// Recebo a referência para o inicio da fila.
			No aux2 = null;
			while(aux != null){
				if(aux.getKey() > key){// Verifica se a chave do nó é maior que a chave recebida.
					if(aux == first){// Verifica se o nó auxiliar é o primeiro.
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
				else if (aux.getNext() == null){//Verifica se foi atingido o final da fila, para poder adicionar o novo nó.
					aux.setNext(novo);
					quantidadeNo++;
					return;
				}
				aux2 = aux;// Salva a referência de aux.
				aux = aux.getNext();//aux caminha a fila.
			}
		}
	}

	/** Método que remove um objeto no inicio da fila.
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

	/** Método que retorna o primeiro objeto da fila.
	 * @see util.IFilaPrioridade#recuperarInicio()
	 * @return objeto - Primeiro objeto da fila.
	 */
	@Override
	public Object recuperarInicio() {
		Object objeto = first.getObject();// Cria o objeto e recebe a referencia do primeiro.
		return objeto;// Retorna o primeiro objeto da fila.
	}

	/** Método que gera e retorna uma árvore de Huffman.
	 * @return primeiro - Primeiro objeto da fila que contém a árvore de Huffman.
	 */
	public ArvoreHuffman gerarArvoreHuffman(){
		
		ArvoreHuffman primeiro;
		ArvoreHuffman segundo;
		
		while(quantidadeNo > 1){
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
