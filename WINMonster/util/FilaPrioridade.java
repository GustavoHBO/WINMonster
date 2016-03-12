package util;

/**
 * Classe fila de prioridade, onde é levada em consideração a chave de cada nó a ser inserido.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 *
 */
public class FilaPrioridade implements IFilaPrioridade {

	/**
	 * Método de inserir com prioridade, onde a chave do nó.
	 * @param key - Chave do nó.
	 * @param object - Objeto a ser armazenado na fila.
	 */

	private int qNo = 0;// Quantidade de nó's da fila.
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
		return qNo;// Retorna o tamanho do nó.
	}

	/**
	 * Método de inserir com prioridade, onde a chave do nó.
	 * @see util.IFilaPrioridade#inserir(java.lang.Object)
	 * @param key - Chave do nó.
	 * @param object - Objeto a ser armazenado na fila.
	 */
	@Override
	public void inserir(int key, Object o) {
		No novo = new No(key, o);
		if(estaVazia()){// Caso não exista objetos na fila.
			first = novo;
			qNo++;// Incrementa a quantidade de nó's da fila.
		}
		else{// Vou inserir os nó's de maiores chaves na frente.
			No aux = first;// Recebo a referência para o inicio da fila.
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
					qNo++;
					return;
				}
				else if (aux.getNext() == null){
					aux.setNext(novo);
					qNo++;
					return;
				}
				aux2 = aux;
				aux = aux.getNext();
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
			if(qNo == 1){
				first = null;
			}
			qNo--;
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

	/**
	 * Retorna o iterador da fila.
	 * @return iterador - Iterador da fila.
	 */

	public Iterador iterador(){
		Iterador iterador = new Iterador(first);
		return iterador;
	}
}
