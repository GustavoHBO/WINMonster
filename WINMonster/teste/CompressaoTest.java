package teste;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class CompressaoTest {

	private Controller controller = null;
	@Before
	public void setUp() throws Exception {
		Controller.zerarSingleton();
		controller = Controller.getInstance();
	}
	@Test
	public void test() {

		File arquivo = null;
		FileWriter fileWrite = null;
		BufferedWriter buff = null;

		arquivo = new File("Teste De Compressao.txt");
		try {
			arquivo.createNewFile();
			fileWrite = new FileWriter(arquivo);
			buff = new BufferedWriter(fileWrite);

			buff.write("a fast runner need never be afraid of the dark");

			buff.close();
			fileWrite.close();
		} catch (IOException e) {
			fail();
		}

		controller.comprimirArquivo("Teste De Compressao.txt");

		/*	System.out.println(dicionario[' '] + " ");
		System.out.println(dicionario['a'] + "a");
		System.out.println(dicionario['r'] + "r");
		System.out.println(dicionario['e'] + "e");
		System.out.println(dicionario['d'] + "d");
		System.out.println(dicionario['f'] + "f");
		System.out.println(dicionario['n'] + "n");
		System.out.println(dicionario['b'] + "b");
		System.out.println(dicionario['h'] + "h");
		System.out.println(dicionario['t'] + "t");
		System.out.println(dicionario['i'] + "i");
		System.out.println(dicionario['k'] + "k");
		System.out.println(dicionario['o'] + "o");
		System.out.println(dicionario['s'] + "s");
		System.out.println(dicionario['u'] + "u");
		System.out.println(dicionario['v'] + "v");*/
	}

}
