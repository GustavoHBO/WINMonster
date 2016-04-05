package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import exceptions.ArquivoNaoCriadoException;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.ArquivoNaoLidoException;
/**
 * Interface Gr�fica da aplica��o WinMonster.
 * @author Gustavo Henrique & Leonardo Melo.
 * @since 21/03/2016.
 */
public class WinMonsterIterface {

	private Controller controller =  Controller.getInstance();//Controller ir� possibilitar a comunica��o da interface com o programa.
	private JFrame framePrincipal;//Frame principal.
	private JPanel painelPrincipal;//Painel principal.
	private JMenuBar menuBar;//Menu da aplica��o.
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Executa toda a interface.
	 * @param args
	 */
	public static void main(String args[]){
		JOptionPane.showMessageDialog(null, "Bem - Vindo ao WinMonster!");
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
	 * M�todo respons�vel por preparar a splash screen.
	 */
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o JFrame.
	 */
	private void preparaJanela() {
		framePrincipal = new JFrame("WinMonster");
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.pack();
		framePrincipal.setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o JPanel.
	 */
	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		framePrincipal.add(painelPrincipal);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * M�todo que prepara todo o menu, adicionando as op��es e os ActionListener.
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
						controller.comprimirArquivo(arquivo.getAbsolutePath());
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
					JOptionPane.showMessageDialog(null, "Compacta��o Concluida!");
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
						controller.descompactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoEncontradoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ArquivoNaoLidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Descompacta��o Concluida!");
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
	 * Prepara o menu Op��es da barra de menu.
	 */
	private void preparaMenuOpcoes(){
		JMenu menuOpcoes = new JMenu("Op��es"); 
		menuBar.add(menuOpcoes);
		JMenuItem MIOMudarCor;
		MIOMudarCor = new JMenuItem("Mudar Cor");
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
	 * Prepara o bot�o Compactar do JPanel.
	 */
	private void preparaBotaoCompactar() {
		JButton botaoCompactar = new JButton("Compactar");
		botaoCompactar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						controller.comprimirArquivo(arquivo.getAbsolutePath());
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
					JOptionPane.showMessageDialog(null, "Compacta��o Concluida!");
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
	 * Prepara o bot�o Descompactar do JPanel.
	 */
	private void preparaBotaoDescompactar(){
		JButton botaoDescompactar = new JButton("Descompactar");
		botaoDescompactar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					try {
						controller.descompactar(arquivo.getAbsolutePath());
					} catch (ArquivoNaoEncontradoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ArquivoNaoLidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Descompacta��o Concluida!");
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
	 * Prepara o bot�o Verificar Integridade do JPanel.
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
		botaoVerificarIntegridade.setToolTipText("Verifica se o arquivo n�o est� corrompido (Alt + V)");
		painelPrincipal.add(botaoVerificarIntegridade);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Prepara o bot�o Sair do JPanel.
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
		botaoSair.setToolTipText("Sai da aplica��o! (Alt + S)");
		painelPrincipal.add(botaoSair);
	}
	/*---------------------------------------------------------------------------------------------------------*/
	/**
	 * Mostra a janela ao usu�rio.
	 */
	private void mostraJanela() {
		framePrincipal.setResizable(false);
		framePrincipal.pack();
		framePrincipal.setSize(600, 400);
		framePrincipal.setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------*/

}
