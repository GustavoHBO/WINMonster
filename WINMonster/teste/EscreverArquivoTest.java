package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controller.ControllerArquivo;

public class EscreverArquivoTest {

	ControllerArquivo controller = null;	
	@Before
	public void setUp() throws Exception {
		ControllerArquivo.zerarSingleton();
		controller = ControllerArquivo.getInstance();
	}

	@Test
	public void escreverArquivoSucesso() {
		
		String arrayChar = "0001110";
		String nomeArquivo = "ArquivoTeste";
		String caminhoArquivo = "";
		String read = "";
		
		ControllerArquivo.escreverArquivo(arrayChar.getBytes(), caminhoArquivo);
		
		FileReader arquivo = null;
		BufferedReader buffer = null;
		
		try{
			arquivo = new FileReader(nomeArquivo + ".monster");
			buffer = new BufferedReader(arquivo);
			
			while(buffer.ready()){
				read = read + buffer.readLine();
			}
			assertEquals(read, arrayChar);
			
			buffer.close();
			arquivo.close();
			File file = new File(nomeArquivo + ".monster");
			file.delete();
			
		}catch (IOException e){
			fail();
		}
		
	}

}
