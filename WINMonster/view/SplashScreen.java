package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import exceptions.SplashScreenInterrompidaException;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow{

	private int duracao;

	public SplashScreen(int duracao){
		this.duracao = duracao;
	}

	/**
	 * Método responsável por exibir o Splash Screen.
	 * @throws SplashScreenInterrompidaException
	 */
	public void showSplash() throws SplashScreenInterrompidaException {        
		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		// Configura a posição e o tamanho da janela
		int width = 450;
		int height = 215;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		JLabel label = new JLabel(new ImageIcon("resources\\Comprimir.jpg"));// Escolho a imagem para ficar no splash screen
		JLabel copyrt = new JLabel
				("Gustavo Henrique && Leonardo Melo, MI - Programação", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		content.setBorder(BorderFactory.createLineBorder(new Color(148, 0, 211), 15));        
		
		setVisible(true);// Torno visível para o usuário.

		try {// Caso o splash screen pare.
			Thread.sleep(duracao);
		} catch (InterruptedException e) {
			throw new SplashScreenInterrompidaException();
		} 

		setVisible(false);        
	}

}
