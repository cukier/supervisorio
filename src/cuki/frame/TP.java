package cuki.frame;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import java.awt.Font;

@SuppressWarnings("serial")
public class TP extends JPanel {

	/**
	 * Create the panel.
	 */
	public TP() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("bottom:default:grow"), }));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel, "2, 2, center, default");

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel_1, "2, 4, center, default");

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel_2, "2, 6, center, default");

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel_3, "2, 8, center, default");

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel_4, "2, 10, center, default");

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(lblNewLabel_5, "2, 12, center, default");

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		add(btnNewButton, "2, 14, center, default");

		setPreferredSize(new Dimension(350, 300));

	}
}
