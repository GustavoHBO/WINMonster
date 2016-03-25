package teste;

import util.FilaPrioridade;

public class testeConsole {

	static FilaPrioridade fila = new FilaPrioridade();
	public static void main(String args []){
		/*String str1 = new String("Cocada");
		String str2 = new String("Polenta");
		String str3 = new String("Caxias");
		
		fila.inserir(0, str1);
		fila.inserir(1, str2);
		fila.inserir(2, str3);
		
		while(!fila.estaVazia()){
			System.out.println(fila.removerInicio());
		}*/
		
		String nome = "\\Gustavo.Henrique";
		System.out.println(nome.substring(0, nome.lastIndexOf('.')));
		System.out.println(nome.substring(nome.lastIndexOf('\\'), nome.lastIndexOf('.')));

	}
}
