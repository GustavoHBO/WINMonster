package util;

public interface IFilaPrioridade {

	public boolean estaVazia();// Verifica se a fila esta vazia.

    public int obterTamanho();// Retorna o tamanho da fila.

    public void inserir(int key,Object o);// Insere o objeto recebido na posição determinada pela chave..

    public Object removerInicio();// Remove o primeiro objeto da fila e o retorna.

    public Object recuperarInicio();// Retorna o primeiro objeto da fila.
}
