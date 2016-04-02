package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;

public class WinMonsterIterface {

	private Controller controller =  Controller.getInstance();
	public static void main(String args[]){
		JOptionPane.showMessageDialog(null, "Bem - Vindo ao WinMonster!");
		new WinMonsterIterface().montarTela();
	}

	private JFrame framePrincipal;
	private JPanel painelPrincipal;

	private void montarTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotaoCompactar();
		preparaBotaoSair();
		mostraJanela();

	}

	private void preparaJanela() {
		framePrincipal = new JFrame("WinMonster");
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.pack();
		framePrincipal.setVisible(true);

	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		framePrincipal.add(painelPrincipal);

	}

	private void preparaBotaoCompactar() {
		JButton botaoCompactar = new JButton("Compactar");
		botaoCompactar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				File arquivo = new EscolherArquivo().escolhe();
				if(arquivo != null){
					controller.comprimirArquivo(arquivo.getAbsolutePath());
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);;
				}

			}
		});
		botaoCompactar.setToolTipText("Escolha o arquivo a ser compactado!");
		painelPrincipal.add(botaoCompactar);
	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		ActionListener sairListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}
		};
		botaoSair.addActionListener(sairListener);
		botaoSair.setToolTipText("Sai da aplicação!");
		painelPrincipal.add(botaoSair);
	}

	private void mostraJanela() {
		framePrincipal.setResizable(false);
		framePrincipal.pack();
		framePrincipal.setSize(600, 540);
		framePrincipal.setVisible(true);
	}










}
