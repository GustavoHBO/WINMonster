package teste;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controller.ControllerArquivo;
import controller.ControllerCompactar;

public class CompressaoTest {

	@Before
	public void setUp() throws Exception {
		ControllerArquivo.zerarSingleton();
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

			buff.write("T� acabando carai!!!!");
			buff.close();
			fileWrite.close();
		} catch (IOException e) {
			fail();
		}

		ControllerCompactar.comprimirArquivo("Teste De Compressao.txt", "Teste De Compressao.monster");

	}

}
