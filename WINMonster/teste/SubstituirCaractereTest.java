package teste;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class SubstituirCaractereTest {

	private Controller controller;

	@Before
	public void setUp() throws Exception {
		controller = Controller.getInstance();
	}

	@Test
	public void test() {
		String[] str = new String[256];
		str[97] = "00";
		str[98] = "111";

		String dadosArquivo = "abb";
		
		String codigo = "11011100111111111000";
		
		int[] array = controller.substituirCaractere(codigo);
		
		for(int a : array){
			System.out.println(a);
		}

		
	}

}
