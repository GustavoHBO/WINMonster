package view;

import java.io.File;

import javax.swing.JFileChooser;

public class EscolherArquivo {

	public File escolhe() {
		JFileChooser chooser = new JFileChooser();
		int retorno = chooser.showOpenDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}
}
