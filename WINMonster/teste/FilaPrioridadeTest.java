package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import util.FilaPrioridade;
import util.IFilaPrioridade;

public class FilaPrioridadeTest {

	IFilaPrioridade fila;
	@Before
	public void setUp() throws Exception {
		fila = new FilaPrioridade();
	}

	@Test
	public void InserirTest() {
		String str1 = new String("Cocada");
		String str2 = new String("Polenta");
		String str3 = new String("Caxias");
		
		fila.inserir(2, str1);
		fila.inserir(0, str2);
		fila.inserir(1, str3);
		
		String str4;
		str4 = (String)fila.removerInicio();
		assertEquals("Cocada", str4);
		str4 = (String)fila.removerInicio();
		assertEquals("Caxias", str4);
		str4 = (String)fila.removerInicio();
		assertEquals("Polenta", str4);
		assertTrue(fila.estaVazia());
	}

}
