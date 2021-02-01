package main;

import db.Conexion;
import db.Metodos;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class Main extends javax.swing.JFrame {
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Server name:");
	private final JTextField txtServer = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("Port:");
	private final JTextField textPort = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("Usuario:");
	private final JTextField txtUsr = new JTextField();
	private final JLabel label = new JLabel("Password:");
	private final JPasswordField pwd = new JPasswordField();
	private final JLabel lblNewLabel_3 = new JLabel("Database:");
	private final JTextField textDB = new JTextField();
	private final JButton btnRun = new JButton("Run");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textArea = new JTextArea();
	private final JButton btnSave = new JButton("Save");
	public Main() {
		textDB.setColumns(10);
		txtUsr.setText("sa");
		txtUsr.setColumns(10);
		textPort.setText("1433");
		textPort.setColumns(10);
		txtServer.setText("LAPTOP-CFLF3PUK");
		txtServer.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setTitle("SQLServerDataChecker by Jorge Trujillo");
		setSize(new Dimension(552, 276));
		setPreferredSize(new Dimension(100, 100));
		getContentPane().setMinimumSize(new Dimension(100, 100));
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(60, 50));
		panel.setMinimumSize(new Dimension(100, 100));
		panel.setSize(new Dimension(100, 100));
		
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 8, 0, 0));
		
		panel.add(lblNewLabel);
		
		panel.add(txtServer);
		
		panel.add(lblNewLabel_1);
		
		panel.add(textPort);
		
		panel.add(lblNewLabel_2);
		
		panel.add(txtUsr);
		
		panel.add(label);
		pwd.setText("AuditoriaDBA");
		
		panel.add(pwd);
		
		panel.add(lblNewLabel_3);
		
		panel.add(textDB);
		
		panel.add(btnRun);
		
		panel.add(btnSave);
		btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRun(evt);
            }
        });
		btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewAction(evt);
            }
        });
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(textArea);
	}
	
	private void jButtonRun(java.awt.event.ActionEvent evt) {
		Conexion con = new Conexion(txtServer.getText(), textPort.getText(), txtUsr.getText(), pwd.getText(), textDB.getText());
		Metodos dbMethod = new Metodos(con);
		textArea.setText(dbMethod.searchNulls());
		
	}
	
	public void saveChooser(Component parent) { 
    	String ruta = "";
    	JFileChooser jFileCh = new JFileChooser();
    	int userSelection = jFileCh.showSaveDialog(parent);
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    		try {
                ruta = jFileCh.getSelectedFile().getAbsolutePath()+".txt";
                FileWriter fw = new FileWriter(ruta);
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    	}
    }
	
    private void jMenuNewAction(java.awt.event.ActionEvent evt) {
    	saveChooser(this);
		
    }

	public static void main(String[] args) {
		
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
	}

}
