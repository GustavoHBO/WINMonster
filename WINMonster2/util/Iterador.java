package util;

/**
 * Classe Iterador - Percorre a lista e retorna os objetos na posi��o atual e verifica se existe mais n�'s a frente.
 * @author Gustavo Henrique.
 * @since 26 de Dezembro de 2015.
 */
public class Iterador implements IIterador {

	private No atual;// N� referencia para percorrer a lista e retornar o objeto.

	/**
	 * Construtor, define de onde deve come�ar a percorrer a lista.
	 * @param inicio - Ponto de inicio.
	 */
	public Iterador(No inicio){// Para criar um iterador � necess�rio definir o inicio.
		atual = inicio;
	}
	/**
	 * Verifica se existe algum n� a frente.
	 * @return True - Caso exista mais n�'s, False - Caso seja o fim da lista.
	 */
	@Override
	public boolean temProximo() {
		return atual != null;// Retorna true caso exista mais n�'s ou false caso n�o.
	}

	/**
	 * Retorna o objeto do n� atual e passa para o pr�ximo n�, caso exista.
	 * @return objeto - Objeto do n�, null - Caso n�o exista mais algo a retornar.
	 */
	@Override
	public Object obterProximo() {
		if(temProximo()){// Verifica se existe mais n�'s.
			Object objeto = atual.getObject();// Guarda o objeto do n� atual.
			atual = atual.getNext();// atual percorre a lista.
			return objeto;// Retorna o objeto guardado.
		}
		return null;// Retorna null caso esteja no fim da lista.
	}
}
