package util;

/**
 * Classe fila de prioridade, onde � levada em considera��o a chave de cada n� a ser inserido.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 *
 */
public class Fila implements IFila {

	private int qNo = 0;// Quantidade de n�'s da fila.
	private No first;//Inicio da fila.
	private No last;//Ultimo n� fila.
	
	@Override
	/**
	 * Verifica se a lista esta vazia.
	 * @see util.IFila#estaVazia()
	 * @return true - Caso vazia, false - Caso n�o.
	 */
	public boolean estaVazia() {
		return first == null;// Verifica se o primeiro n� existe.
	}

	/** M�todo que retorna o tamanho da fila.
	 * @see util.IFila#obterTamanho()
	 * @return int - Tamanho da fila.
	 */
	@Override
	public int obterTamanho() {
		return qNo;// Retorna o tamanho do n�.
	}

	/** M�todo que insere o objeto no final da fila.
	 * @see util.IFila#inserirFinal(java.lang.Object)
	 * @param o - Objeto a ser inserido no final da fila.
	 */
	@Override
	public void inserirFinal(Object o) {
		No novo = new No(o);
		if(estaVazia()){// Caso n�o exista objetos na fila.
			last = novo;// Insere no inicio e no final, pois � o primeiro e ultimo objeto.
			first = novo;
			qNo++;// Incrementa a quantidade de n�'s da fila.
		}
		else{// Caso exista objetos na lista.
			last.setNext(novo);// Insere o objeto no final.
			last = novo;// E ultimo passa a ser o novo objeto.
			qNo++;// Incrementa a quantidade de no's da fila.
		}

	}

	/** M�todo que remove um objeto no inicio da fila.
	 * @see util.IFila#removerInicio().
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
				last = null;
			}
			qNo--;
		}
		return objeto;
	}

	/** M�todo que retorna o primeiro objeto da fila.
	 * @see util.IFila#recuperarInicio()
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