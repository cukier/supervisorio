package cuki.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Dialog1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int tamanho;

	public Dialog1(String[] s) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea textArea = new JTextArea();
			contentPanel.add(textArea);
			textArea.setEditable(false);
			tamanho = 0;
			for (String row : s) {
				if (row.length() > tamanho)
					tamanho = row.length();
				textArea.append(row);
				textArea.append("\n");
				System.out.println(row.length() + " " + row);
			}
			repaint();
		}
		setBounds(250, 100, tamanho * 7, 300);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

	public static void main(String[] args) {
		try {
			String[] s = { "bad", "ass", "mother", "fucker" };
			Dialog1 dialog = new Dialog1(s);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
