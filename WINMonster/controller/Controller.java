package controller;

import exceptions.FilaPrioridadeNulaException;
import util.FilaPrioridade;
import util.No;

/**
 * Classe controller, responsável pelo gerenciamento do programa.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 * @since 10 de março de 2016.
 *
 */
public class Controller {

	/**
	 * Método responsável pela criação da árvore binária.
	 * @param filaPrioridade - Fila de Prioridade.
	 * @throws FilaPrioridadeNulaException - Caso a fila de prioridade seja nula.
	 */
	public void criarArvore(FilaPrioridade filaPrioridade) throws FilaPrioridadeNulaException{
		
		if(filaPrioridade == null){
			throw new FilaPrioridadeNulaException();
		}
		else{
			No no1, no2;
			No pai = new No();
			while(filaPrioridade.obterTamanho() != 1){
				no1 = (No)filaPrioridade.removerInicio();
				no2 = (No)filaPrioridade.removerInicio();
				pai.setKey(no1.getKey() + no2.getKey());
				if(no1.getKey() > no2.getKey()){
					pai.setAfter(no1);
					pai.setNext(no2);
				}
				else{
					pai.setAfter(no1);
					pai.setNext(no2);
				}
				filaPrioridade.inserir(pai.getKey(), pai);
			}
		}
	}
}
