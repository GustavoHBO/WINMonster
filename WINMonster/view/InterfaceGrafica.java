package view;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Perssistencia.Fachada;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;

public class InterfaceGrafica {



	public static void main(String[] args) throws ArquivoNaoEncontradoException, ArquivoNaoLidoException{

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Images", "monster", "png", "gif", "bmp"));
		chooser.showOpenDialog(null);

		File file = chooser.getSelectedFile();

		FileInputStream fileInput;
		DataInputStream dataInput;


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
		
		String pa = Fachada.lerArquivo(file.getAbsolutePath());
		System.out.println(pa);
		System.out.println(pa.length());
		
	}
}
