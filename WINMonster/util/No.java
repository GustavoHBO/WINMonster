package util;

public class No {

	private No after;// Refer�ncia para o n� anterior.
	private No next;// Refer�ncia para o pr�ximo n�.
	private Object object;// Objeto do n�.
	private int key = 0;// Chave do n�.
	
	/**
	 * Construtor da classe.
	 */
	public No(){
	}
	/**
	 * Construtor da classe.
	 * @param obj - Objeto do n�.
	 * @param key - Chave do n�.
	 */
	public No(int key,Object obj){
		object = obj;
		this.key = key;
	}
	/**
	 * Construtor da classe.
	 * @param obj - Objeto do n�.
	 */
	public No(Object obj){
		object = obj;
	}
	/**
	 * M�todo que retorna o n� anterior.
	 * @return after - N� anterior.
	 */
	public No getAfter() {
		return after;
	}
	/**
	 * M�todo que altera o n� anterior.
	 * @param after - Novo n� anterior.
	 */
	public void setAfter(No after) {
		this.after = after;
	}
	/**
	 * M�todo que retorna o pr�ximo n�.
	 * @return next - N� anterior.
	 */
	public No getNext() {
		return next;
	}
	/**
	 * M�todo que altera o pr�ximo n�.
	 * @param next - Pr�ximo n�.
	 */
	public void setNext(No next) {
		this.next = next;
	}
	/**
	 * Retorna a chave do n�.
	 * @return key - Chave do n�.
	 */
	public int getKey() {
		return key;
	}
	/**
	 * Altera a chave do n�.
	 * @param key - Nova chave do n�.
	 */
	public void setKey(int key) {
		this.key = key;
	}
	/**
	 * Retorna o objeto do n�.
	 * @return object - Objeto do n�.
	 */
	public Object getObject() {
		return object;
	}
	/**
	 * Altera o objeto do n�.
	 * @param object - Novo objeto do n�.
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
