package util;

public class No {

	private No after;// Referência para o nó anterior.
	private No next;// Referência para o próximo nó.
	private Object object;// Objeto do nó.
	private int key = 0;// Chave do nó.
	
	/**
	 * Construtor da classe.
	 */
	public No(){
	}
	/**
	 * Construtor da classe.
	 * @param obj - Objeto do nó.
	 * @param key - Chave do nó.
	 */
	public No(int key,Object obj){
		object = obj;
		this.key = key;
	}
	/**
	 * Construtor da classe.
	 * @param obj - Objeto do nó.
	 */
	public No(Object obj){
		object = obj;
	}
	/**
	 * Método que retorna o nó anterior.
	 * @return after - Nó anterior.
	 */
	public No getAfter() {
		return after;
	}
	/**
	 * Método que altera o nó anterior.
	 * @param after - Novo nó anterior.
	 */
	public void setAfter(No after) {
		this.after = after;
	}
	/**
	 * Método que retorna o próximo nó.
	 * @return next - Nó anterior.
	 */
	public No getNext() {
		return next;
	}
	/**
	 * Método que altera o próximo nó.
	 * @param next - Próximo nó.
	 */
	public void setNext(No next) {
		this.next = next;
	}
	/**
	 * Retorna a chave do nó.
	 * @return key - Chave do nó.
	 */
	public int getKey() {
		return key;
	}
	/**
	 * Altera a chave do nó.
	 * @param key - Nova chave do nó.
	 */
	public void setKey(int key) {
		this.key = key;
	}
	/**
	 * Retorna o objeto do nó.
	 * @return object - Objeto do nó.
	 */
	public Object getObject() {
		return object;
	}
	/**
	 * Altera o objeto do nó.
	 * @param object - Novo objeto do nó.
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
