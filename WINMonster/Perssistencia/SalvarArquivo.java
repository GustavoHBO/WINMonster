package Perssistencia;

public interface SalvarArquivo {

	/**
	 * M�todo static para poder gravar o arquivo sem ter que instanciar um objeto da classe que implementa a interface.
	 * @param arg0 - Dicion�rio do arquivo lido.
	 * @param arg2 - Array com os c�digos dos arquivos.
	 * @param caminho - Caminho at� o arquivo a ser gravado.
	 */
	public static void escreverArquivo(String arg0, int[]arg2, String caminho) {
	}
	
	/**
	 * M�todo respons�vel por gravar o arquivo recebido.
	 * @param texto - Texto a ser gravado.
	 * @param caminho - Caminho at� o arquivo.
	 */
	public static void escreverArquivo(String texto, String caminho){
		
	}
}
