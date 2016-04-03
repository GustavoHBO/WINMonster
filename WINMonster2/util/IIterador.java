package util;

/**
 * Interface do Iterador
 * @author Fernanda Castelo.
 *
 */

public interface IIterador {// Alterei o nome da interface para não dar conflito com a classe.
	
	/**
	 * Verifica se tem próximo objeto na lista.
	 * @return true - Caso exista mais objetos, false - Caso não exista mais objetos na lista.
	 */
	public boolean temProximo();

	/**
	 * Retorna o objeto atual e passa para o próximo objeto da lista.
	 * @return objeto - Retorna o atual objeto na lista.
	 */
	public Object obterProximo();

}
