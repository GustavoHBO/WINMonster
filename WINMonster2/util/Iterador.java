package util;

/**
 * Classe Iterador - Percorre a lista e retorna os objetos na posição atual e verifica se existe mais nó's a frente.
 * @author Gustavo Henrique.
 * @since 26 de Dezembro de 2015.
 */
public class Iterador implements IIterador {

	private No atual;// Nó referencia para percorrer a lista e retornar o objeto.

	/**
	 * Construtor, define de onde deve começar a percorrer a lista.
	 * @param inicio - Ponto de inicio.
	 */
	public Iterador(No inicio){// Para criar um iterador é necessário definir o inicio.
		atual = inicio;
	}
	/**
	 * Verifica se existe algum nó a frente.
	 * @return True - Caso exista mais nó's, False - Caso seja o fim da lista.
	 */
	@Override
	public boolean temProximo() {
		return atual != null;// Retorna true caso exista mais nó's ou false caso não.
	}

	/**
	 * Retorna o objeto do nó atual e passa para o próximo nó, caso exista.
	 * @return objeto - Objeto do nó, null - Caso não exista mais algo a retornar.
	 */
	@Override
	public Object obterProximo() {
		if(temProximo()){// Verifica se existe mais nó's.
			Object objeto = atual.getObject();// Guarda o objeto do nó atual.
			atual = atual.getNext();// atual percorre a lista.
			return objeto;// Retorna o objeto guardado.
		}
		return null;// Retorna null caso esteja no fim da lista.
	}
}
