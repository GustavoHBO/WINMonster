package util;

/**
 * Interface do Iterador
 * @author Fernanda Castelo.
 *
 */

public interface IIterador {// Alterei o nome da interface para n�o dar conflito com a classe.
	
	/**
	 * Verifica se tem pr�ximo objeto na lista.
	 * @return true - Caso exista mais objetos, false - Caso n�o exista mais objetos na lista.
	 */
	public boolean temProximo();

	/**
	 * Retorna o objeto atual e passa para o pr�ximo objeto da lista.
	 * @return objeto - Retorna o atual objeto na lista.
	 */
	public Object obterProximo();

}
