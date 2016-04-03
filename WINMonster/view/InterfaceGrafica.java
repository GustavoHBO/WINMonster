package view;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class InterfaceGrafica {



	public static void main(String[] args){

		/*JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

		File file = chooser.getSelectedFile();

		FileInputStream fileInput;
		DataInputStream dataInput*/;


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
		
		
		System.out.println(Integer.parseInt("110", 2));
	}
}
