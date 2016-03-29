package view;

public class InterfaceGrafica {



	public static void main(String[] args){

		/*JOptionPane.showMessageDialog(null, "Minha mensagem!");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		fileChooser.setCurrentDirectory(new java.io.File(""));
		fileChooser.setDialogTitle("Selecionador de Arquivos");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.showOpenDialog(null);

		System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
		JButton botaoCarregar = new JButton("Carregar XML");
		JButton botaoSair = new JButton("Sair");

		JPanel painel = new JPanel();
		painel.add(botaoCarregar);
		painel.add(botaoSair);

		JFrame janela = new JFrame("Argentum");
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		 */

		/*try {
			FileOutputStream file = new FileOutputStream("meuArquivo.dat");
			DataOutputStream ods = new DataOutputStream(file);
			Boolean[] boo = {true, true, true, true, true, true, true, true};
			ods.writeBoolean(true);
			ods.writeBoolean(true);
			ods.writeBoolean(false);
			ods.writeByte(0);
			ods.writeByte(1);
			ods.writeByte(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String str = "0123456789";
		//int valor2 = valorT(2, 8);
		//System.out.println(valor2);
//		Integer a = 256;
//		Integer b = -56;
//		System.out.println(Integer.toBinaryString(b));
//		System.out.println(Integer.parseInt(str, 2));
//		System.out.println(Integer.toBinaryString(255));
//		
//		System.out.println((char)97);
////		System.out.println(Integer.parseInt("00000000", 2));
		byte a = (byte)'Ç';
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a + 128 + 127));
		System.out.println(Integer.parseInt(Integer.toBinaryString(a + 128 + 127), 2));
		System.out.println(Integer.toBinaryString(128));
		System.out.println(Integer.parseInt(Integer.toBinaryString(128), 2));
		System.out.println((char)a);
		
	}

}
