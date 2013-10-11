package cuki.frame;

import java.awt.EventQueue;

import cuki.frame.Dialog1;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListaVariaveis extends JFrame {

	private JPanel contentPane;
	private Dialog1 dg;
	private String[] servidores = new String[100];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaVariaveis frame = new ListaVariaveis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListaVariaveis() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 150, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(10, 11, 120, 23);
		contentPane.add(btnConectar);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// OpcConn.connect();
					System.out.println("Ok");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(10, 45, 120, 23);
		contentPane.add(btnDesconectar);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// OpcConn.disconnect();
			}
		});

		JButton btnListarGrupos = new JButton("Listar Grupos");
		btnListarGrupos.setBounds(10, 79, 120, 23);
		contentPane.add(btnListarGrupos);
		btnListarGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dg != null) {
					dg.dispose();
					dg = null;
				}
				try {
					// servidores = OpcConn.listarItens();
					dg = new Dialog1(servidores);
					dg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dg.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnListarItens = new JButton("Listar Itens");
		btnListarItens.setBounds(10, 113, 120, 23);
		contentPane.add(btnListarItens);
		btnListarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
