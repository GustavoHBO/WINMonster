package view;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
		String str = "11111111";
		//int valor2 = valorT(2, 8);
		//System.out.println(valor2);
		int valor = criarByte(str);
		unsi byte valor2 = (byte) 255;
		System.out.println(valor2);
		System.out.println(valor);
	}

	public static int valorT(int valor, int vezes){

		if (vezes == 1 || vezes == 0){
			return valor;
		}
		else{
			valor *= valorT(valor, vezes-1);
		}
		return valor;
	}
	public static int criarByte(String string){
		char[] str = string.toCharArray();
		int exp = string.length() - 1;
		int valor = 0;
		for(int i = 0; i < string.length(); i++){
			if(str[i] == '1'){
				valor += valorT(2, exp - i);
			}
		}
		return valor;
	}


}
