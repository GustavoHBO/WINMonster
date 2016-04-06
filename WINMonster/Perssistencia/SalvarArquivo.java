package Perssistencia;

public interface SalvarArquivo {

	/**
	 * Método static para poder gravar o arquivo sem ter que instanciar um objeto da classe que implementa a interface.
	 * @param arg0 - Dicionário do arquivo lido.
	 * @param arg2 - Array com os códigos dos arquivos.
	 * @param caminho - Caminho até o arquivo a ser gravado.
	 */
	public static void escreverArquivo(String arg0, int[]arg2, String caminho) {
	}
	
	/**
	 * Método responsável por gravar o arquivo recebido.
	 * @param texto - Texto a ser gravado.
	 * @param caminho - Caminho até o arquivo.
	 */
	public static void escreverArquivo(String texto, String caminho){
		
	}
}
