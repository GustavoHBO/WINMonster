package teste;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class DescompressaoTest {
	private Controller controller = null;
	@Before
	public void setUp() throws Exception {
		Controller.zerarSingleton();
		controller = Controller.getInstance();
	}
	@Test
	public void test() {

		/*File arquivo = null;
		FileWriter fileWrite = null;
		BufferedWriter buff = null;
		
				
		/*arquivo = new File("Teste De Descompressao.txt");
		try {
			arquivo.createNewFile();
			fileWrite = new FileWriter(arquivo);
			buff = new BufferedWriter(fileWrite);

			buff.write("");
			buff.close();
			fileWrite.close();
		} catch (IOException e) {
			fail();
		}*/

		controller.descomprimirArquivo("Teste De Compressao.monster");

	}
}
