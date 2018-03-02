package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class FileFinder extends JFrame {

	private JPanel contentPane;
	private JComboBox selectPath;
	private JEditorPane file1;
	private JEditorPane file2;
	private JEditorPane file3;
	private boolean second = false;
	private boolean first = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFinder frame = new FileFinder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FileFinder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		selectPath = new JComboBox();
		selectPath.setModel(
				new DefaultComboBoxModel(new String[] { "file:///C:\\Users\\sergio.arranz\\Downloads\\jndiprueba" }));

		JLabel lblPath = new JLabel("Seleccione ruta:");

		JLabel lblFile1 = new JLabel("Archivo 1");

		file1 = new JEditorPane();

		JLabel lblFile2 = new JLabel("Archivo 2");

		file2 = new JEditorPane();

		JLabel lblFile3 = new JLabel("Archivo 3");

		file3 = new JEditorPane();

		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					findFile();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(34)
								.addComponent(lblFile1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addGap(77).addComponent(lblFile2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(19)
								.addComponent(file1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnSearch)
										.addComponent(file2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_contentPane.createSequentialGroup()
												.addComponent(lblFile3, GroupLayout.PREFERRED_SIZE, 78,
														GroupLayout.PREFERRED_SIZE)
												.addGap(31))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(file3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(23))))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(102, Short.MAX_VALUE)
						.addComponent(selectPath, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
						.addGap(94))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(169).addComponent(lblPath)
						.addContainerGap(172, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(15)
						.addComponent(lblPath, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(selectPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblFile1)
								.addComponent(lblFile2).addComponent(lblFile3))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(file1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(file2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(file3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(26).addComponent(btnSearch).addContainerGap(89, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	protected void findFile() throws NamingException {
		String[] arrayFiles;
		arrayFiles = new String[4];

		arrayFiles[0] = selectPath.getSelectedItem().toString();
		arrayFiles[1] = file1.getText().toString();
		arrayFiles[2] = file2.getText().toString();
		arrayFiles[3] = file3.getText().toString();

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		p.put(Context.PROVIDER_URL, arrayFiles[0]);
		Context ctx = new InitialContext(p);

		if (file1.getText().toString().equals("") || file2.getText().toString().equals("")
				|| file3.getText().toString().equals("")) {

			JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenos.");
			this.second = true;
			this.first = false;
			FileFinder frame = new FileFinder();

		} else {
			first = true;
		}
		if (first) {
			for (int i = 1; i < arrayFiles.length; i++) {

				try {
					if (file1.getText().toString() != null) {
						ctx.lookup(arrayFiles[i]);
						System.out.println(arrayFiles[i] + " Archivo encontrado.");
					} else if (file2.getText().toString() != null) {
						ctx.lookup(arrayFiles[i]);
						System.out.println(arrayFiles[i] + " Archivo encontrado.");
					} else if (file3.getText().toString() != null) {
						ctx.lookup(arrayFiles[i]);
						System.out.println(arrayFiles[i] + " Archivo encontrado.");
					}

				} catch (NamingException ex) {
					System.out.println(arrayFiles[i] + " El archivo no existe.");
				}
			}
		}

	}

}
