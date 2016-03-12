package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerCriarArvoreTest {

	FilaPrioridadeTest filaPrioridade = null;
	@Before
	public void setUp() throws Exception {
		filaPrioridade = new FilaPrioridadeTest();
	}

	@Test
	public void CriarArvoreTest() {
		String str1 = new String("Cocada");
		String str2 = new String("Polenta");
		String str3 = new String("Caxias");
		
		filaPrioridade.inserir(2, str1);
		filaPrioridade.inserir(0, str2);
		filaPrioridade.inserir(1, str3);
	}

}
