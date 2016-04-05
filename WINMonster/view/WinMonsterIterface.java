package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
import exceptions.SplashScreenInterrompidaException;
/**
 * Interface Gráfica da aplicação WinMonster.
 * @author Gustavo Henrique & Leonardo Melo.
 * @since 21/03/2016.
 */
public class WinMonsterIterface {

	private JFrame framePrincipal;//Frame principal.
	private JPanel painelPrincipal;//Painel principal.
	private JMenuBar menuBar;//Menu da aplicação.
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Executa toda a interface.
	 * @param args
	 */
	public static void main(String args[]){
		try {
			new SplashScreen(1800).showSplash();// O parâmetro é o tempo do Splash Screen.
		} catch (SplashScreenInterrompidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new WinMonsterIterface().montarTela();
	}

	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Monta todos os componentes da interface.
	 */
	private void montarTela(){
		preparaJanela();
		preparaMenu();
		preparaPainelPrincipal();
		preparaBotaoCompactar();
		preparaBotaoDescompactar();
		preparaBotaoVerificarIntegridade();
		preparaBotaoSair();
		mostraJanela();

	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o JFrame.
	 */
	private void preparaJanela() {
		framePrincipal = new JFrame("WinMonster");
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setIconImage(new ImageIcon("resources\\comp.png").getImage());// Define a imagem do icone da aplicação.
		framePrincipal.pack();// Faz com que os componentes do frame ocupem o menor lugar possível.
		framePrincipal.setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o JPanel.
	 */
	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(148, 0, 211));// Define a cor roxa.
		framePrincipal.add(painelPrincipal);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Método que prepara todo o menu, adicionando as opções e os ActionListener.
	 */
	private void preparaMenu(){

		menuBar = new JMenuBar();
		menuBar.setVisible(true);
		preparaMenuArquivo();
		preparaMenuOpcoes();
		preparaMenuAjuda();
		framePrincipal.setJMenuBar(menuBar);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o menu Arquivo da barra de menu.
	 */
	private void preparaMenuArquivo(){
		JMenu menuArquivo;
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		JMenuItem MIACompactar, MIADescompactar, MIAVerificarIntegridade, MIASair;
		MIACompactar = new JMenuItem("Compactar");
		MIACompactar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						Controller.compactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoCriadoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ArquivoNaoEncontradoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ArquivoNaoLidoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Compactação Concluida!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);;
				}
			}

		});
		menuArquivo.add(MIACompactar);
		MIADescompactar = new JMenuItem("Descompactar");
		MIADescompactar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						Controller.descompactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoEncontradoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ArquivoNaoLidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Descompactação Concluida!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);;
				}
			}

		});
		menuArquivo.add(MIADescompactar);
		menuArquivo.addSeparator();
		// TODO Auto-generated catch block
		MIAVerificarIntegridade = new JMenuItem("Verificar Integridade");
		menuArquivo.add(MIAVerificarIntegridade);
		menuArquivo.addSeparator();
		MIASair = new JMenuItem("Sair");
		MIASair.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}

		});
		menuArquivo.add(MIASair);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o menu Opções da barra de menu.
	 */
	private void preparaMenuOpcoes(){
		JMenu menuOpcoes = new JMenu("Opções"); 
		menuBar.add(menuOpcoes);
		JMenu MIOMudarCor;
		/*Crio as opções de cores*/
		JMenuItem corPreto, corAzul, corRoxo, corRosa, corVerde;
		corPreto = new JMenuItem("Preto");
		corAzul = new JMenuItem("Azul");
		corRoxo = new JMenuItem("Roxo");
		corRosa = new JMenuItem("Rosa");
		corVerde = new JMenuItem("Verde");
		MIOMudarCor = new JMenu("Mudar Cor");

		/*Cria um ActionListener para todas as opções de cores*/
		ActionListener listenerMenuItem = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == corPreto)
					painelPrincipal.setBackground(Color.BLACK);
				if(arg0.getSource() == corAzul)
					painelPrincipal.setBackground(Color.BLUE);
				if(arg0.getSource() == corRoxo)
					painelPrincipal.setBackground(new Color(148, 0, 211));
				if(arg0.getSource() == corRosa)
					painelPrincipal.setBackground(Color.PINK);
				if(arg0.getSource() == corVerde)
					painelPrincipal.setBackground(Color.GREEN);
			}
		};
		/*Adiciona o Listener para poder realizar a troca de cores*/
		corPreto.addActionListener(listenerMenuItem);
		corAzul.addActionListener(listenerMenuItem);
		corRoxo.addActionListener(listenerMenuItem);
		corRosa.addActionListener(listenerMenuItem);
		corVerde.addActionListener(listenerMenuItem);
		/*Adiciona as opções de cores ao JMenu*/
		MIOMudarCor.add(corPreto);
		MIOMudarCor.add(corAzul);
		MIOMudarCor.add(corRoxo);
		MIOMudarCor.add(corRosa);
		MIOMudarCor.add(corVerde);
		menuOpcoes.add(MIOMudarCor);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o menu Ajuda da barra de menu.
	 */
	private void preparaMenuAjuda(){
		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o botão Compactar do JPanel.
	 */
	private void preparaBotaoCompactar() {
		JButton botaoCompactar = new JButton("Compactar");
		botaoCompactar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						Controller.compactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoCriadoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ArquivoNaoEncontradoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ArquivoNaoLidoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Compactação Concluida!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		botaoCompactar.setMnemonic('c');// Define o atalho alt + c.
		botaoCompactar.setToolTipText("Escolha o arquivo a ser compactado! (Alt + C)");
		painelPrincipal.add(botaoCompactar);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o botão Descompactar do JPanel.
	 */
	private void preparaBotaoDescompactar(){
		JButton botaoDescompactar = new JButton("Descompactar");
		botaoDescompactar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						Controller.descompactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoEncontradoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ArquivoNaoLidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Descompactação Concluida!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		botaoDescompactar.setMnemonic('d');// Define o atalho alt + d.
		botaoDescompactar.setToolTipText("Descompacte arquivos .monster (Alt + D)");
		painelPrincipal.add(botaoDescompactar);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o botão Verificar Integridade do JPanel.
	 */
	private void preparaBotaoVerificarIntegridade(){

		JButton botaoVerificarIntegridade = new JButton("Verificar Integridade");
		botaoVerificarIntegridade.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		botaoVerificarIntegridade.setMnemonic('v');// Define o atalho alt + v.
		botaoVerificarIntegridade.setToolTipText("Verifica se o arquivo não está corrompido (Alt + V)");
		painelPrincipal.add(botaoVerificarIntegridade);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o botão Sair do JPanel.
	 */
	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}
		});
		botaoSair.setMnemonic('s');// Define o atalho alt + s.
		botaoSair.setToolTipText("Sai da aplicação! (Alt + S)");
		painelPrincipal.add(botaoSair);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Mostra a janela ao usuário.
	 */
	private void mostraJanela() {
		framePrincipal.setResizable(false);
		framePrincipal.pack();
		framePrincipal.setSize(600, 400);
		framePrincipal.setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------*/

}
