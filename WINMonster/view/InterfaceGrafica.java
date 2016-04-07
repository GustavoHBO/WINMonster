package view;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class InterfaceGrafica {



	public static void main(String[] args) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		//		JFileChooser chooser = new JFileChooser();
		//		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//		chooser.setFileFilter(new FileNameExtensionFilter("Images", "monster", "png", "gif", "bmp"));
		//		chooser.showOpenDialog(null);
		//
		//		File file = chooser.getSelectedFile();
		//
		//		FileInputStream fileInput;
		//		DataInputStream dataInput;


		/*try {
			fileInput = new FileInputStream(file);
			dataInput = new DataInputStream(fileInput);

			int a = 0;


			while(dataInput.available() != 0){
				a = dataInput.read();
				System.out.print((char)a);
			}
			fileInput.close();
			dataInput.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*FileReader fileReader;
		BufferedReader bufferReader;


		try {
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			int a;
			int b = 0;
			while(bufferReader.ready()){
				a = bufferReader.read();
				System.out.println(a);
				if(a > 255){
					System.out.println("Achei");
					b++;
				}
			}
			System.out.println(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//		String a = "1111000-a";

		//		String[] b = a.split("-");
		//		System.out.println(b.length);
		//		for(String c : b){
		//			System.out.println(c);
		//		}
		//		String b = a.replaceAll("000", "Ai bicha");
		//		System.out.println(b);
		//		int a = Integer.parseInt("10111010", 2);
		//		System.out.println(a);
		//		System.out.println((char)a);
		//		
		//		int b = Integer.parseInt("01110001", 2);
		//		System.out.println(b);
		//		System.out.println((char)b);
		//		
		//		System.out.println((char)1);
		//
				JFrame frame = new JFrame();
				frame.setSize(300, 400);
				
				JPanel panel = new JPanel();
				
				JTable table = new JTable();
				
				JButton botao = new JButton("oi");
				botao.setBounds(100, 100, JButton.CENTER, JButton.CENTER);
				
				table.setModel(new javax.swing.table.DefaultTableModel(
					new Object[][]{}, 
					new String[]{
							"Função", "Atalho"
					}
				));
				javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
				String[] ca = {"Coisa"};
				dtm.addRow(ca);
				dtm.addRow(new Object[]{"Comprimir", "Alt + C"});
				table.setVisible(true);

				panel.setVisible(true);
				panel.setLayout(new GridBagLayout());
				panel.add(table).setLocation(10, 20);;
				panel.add(botao);
				frame.setVisible(true);
				frame.add(panel);



	}
	public static String traduzirCodigo(String dadosCodificados){

		char[] dados = dadosCodificados.toCharArray();
		StringBuffer dadosTraduzidos = new StringBuffer();

		StringBuffer temp = null;
		for(int i = 0; i < dados.length; i++){
			temp = new StringBuffer(Integer.toBinaryString(dados[i]));
			for(int j = 0; j < 8 - temp.length(); j++){
				dadosTraduzidos.append("0");
			}
			dadosTraduzidos.append(temp.toString());
		}
		System.out.println(dadosTraduzidos.toString());

		return null;
	}
	public static int converterBinario(String stringBinario){

		int valor = 0;
		char[] array = stringBinario.toCharArray();

		for(int i = 7; i != -1; i--){
			if(array[i] == '1')
				valor += Math.pow(2, 7 - i);
		}
		return valor;
	}
}
