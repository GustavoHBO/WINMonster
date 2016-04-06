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
import javax.swing.JTable;
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
			JOptionPane.showMessageDialog(null, "A splashScreen parou de funcionar","ERROR", JOptionPane.ERROR_MESSAGE);
		}
		/*Alguns erros a baixo nunca vão ocorrer*/
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Não foi encontrada a aparencia da aplicação","ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel definir a aparencia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel definir a aparencia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "A aparencia definida não é suportada", "ERROR", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "Compactação Concluida!", "Arquivo Compactado", JOptionPane.INFORMATION_MESSAGE);
					} catch (ArquivoNaoCriadoException e1) {
						JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoEncontradoException e1) {
						JOptionPane.showMessageDialog(null, "O arquivo escolhido não foi encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoLidoException e1) {
						JOptionPane.showMessageDialog(null, "O arquivo escolhido não pode ser lido(O arquivo pode esta protegido)", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
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
						JOptionPane.showMessageDialog(null, "Descompactação Concluida!", "Arquivo Descompactado", JOptionPane.INFORMATION_MESSAGE);
					} catch (ArquivoNaoEncontradoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoLidoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoCriadoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo descompactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
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
		
		JMenuItem menuAtalho = new JMenuItem("Atalhos");
		menuAtalho.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				preparaJLabelAtalho();
			}
		});
		
		menuAjuda.add(menuAtalho);
		menuBar.add(menuAjuda);
	}/*---------------------------------------------------------------------------------------------------------*/
	
	public void preparaJLabelAtalho(){
		JTable table = new JTable();
		JFrame frameAtalho = new JFrame("Atalho");
		frameAtalho.setIconImage(new ImageIcon("resources\\comp.png").getImage());
		frameAtalho.pack();
		frameAtalho.setSize(400, 200);
		JPanel paneAtalho = new JPanel();
		paneAtalho.setBackground(new Color(148, 0, 211));
		table.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][]{},
			new String[]{"Função", "Atalho"}
		));
		
		javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
		dtm.addRow(new Object[]{"Função", "Atalho"});
		dtm.addRow(new Object[]{"Comprimir", "Alt + C"});
		dtm.addRow(new Object[]{"Descompactar", "Alt + D"});
		dtm.addRow(new Object[]{"Verificar Integridade", "Alt + V"});
		dtm.addRow(new Object[]{"Sair", "Alt + S"});
		table.setVisible(true);
		
		paneAtalho.add(table);
		paneAtalho.setVisible(true);
		frameAtalho.add(paneAtalho);
		frameAtalho.setVisible(true);
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
						JOptionPane.showMessageDialog(null, "Compactação Concluida!", "Arquivo Compactado", JOptionPane.INFORMATION_MESSAGE);
					} catch (ArquivoNaoCriadoException e1) {
						JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoEncontradoException e1) {
						JOptionPane.showMessageDialog(null, "O arquivo escolhido não foi encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoLidoException e1) {
						JOptionPane.showMessageDialog(null, "O arquivo escolhido não pode ser lido(O arquivo pode esta protegido)", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
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
						JOptionPane.showMessageDialog(null, "Descompactação Concluida!", "Arquivo Descompactado", JOptionPane.INFORMATION_MESSAGE);
					} catch (ArquivoNaoEncontradoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoLidoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo compactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (ArquivoNaoCriadoException e) {
						JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo descompactado!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
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
