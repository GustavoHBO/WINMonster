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
		
		String dadosArquivo = "ab";
		byte[] a;
		a = controller.substituirCaractere(str, dadosArquivo.toCharArray());
		
		
		
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}

}
