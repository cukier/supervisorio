package cuki.frame;

import java.awt.EventQueue;

import cuki.bin.OpcConn;
import cuki.frame.Dialog1;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.HostException;
import javafish.clients.opc.exception.NotFoundServersException;

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
	private String[] servidores;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ListaVariaveis() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 150, 143);
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
					servidores = OpcConn.connect();
				} catch (CoInitializeException e1) {
					e1.printStackTrace();
				} catch (HostException e2) {
					e2.printStackTrace();
				} catch (NotFoundServersException e3) {
					e3.printStackTrace();
				}
			}
		});

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(10, 45, 120, 23);
		contentPane.add(btnDesconectar);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Desconectar");
				new ListaVariaveis();
			}
		});

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(10, 79, 120, 23);
		contentPane.add(btnListar);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Listar");
			}
		});
	}
}
