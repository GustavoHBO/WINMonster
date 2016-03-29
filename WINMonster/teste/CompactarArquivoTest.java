package teste;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class CompactarArquivoTest {
	Controller controller = null;
	
	@Before
	public void setUp() throws Exception {
		Controller.zerarSingleton();
		controller = Controller.getInstance();
	}
	
	@Test
	public void compactarSucesso(){
		String texto = "De boa na lagoa, mar� bate na rocha e man� cai na proa";
		
		Controller.compactarTexto(texto);
	}
}
