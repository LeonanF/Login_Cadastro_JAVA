package view;

import controller.DBController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Ajuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DBController userController;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Ajuda(DBController controller) throws ParseException {
		
		this.userController = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cpfLabel = new JLabel("Digite o CPF do usuário");
		cpfLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		cpfLabel.setBounds(10, 38, 168, 14);
		contentPane.add(cpfLabel);
		
		JLabel passwordLabel = new JLabel("Digite a nova senha");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordLabel.setBounds(10, 100, 168, 14);
		contentPane.add(passwordLabel);

		final JFormattedTextField cpfField = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		cpfField.setBounds(10, 69, 168, 20);
		contentPane.add(cpfField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 125, 168, 20);
		contentPane.add(passwordField);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpf = cpfField.getText().replaceAll("[^0-9]", "");
				String password = new String(passwordField.getPassword());
				
				if(userController.alterarSenha(cpf, password)){
					new Login(userController).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Erro ao alterar usuário. Verifique o CPF e tente novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
        btnConfirm.setBorderPainted(false);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnConfirm.setRolloverEnabled(false);
        btnConfirm.setForeground(new Color(128, 128, 0));
        btnConfirm.setBackground(new Color(240,240,240));
		btnConfirm.setBounds(179, 227, 89, 23);
		contentPane.add(btnConfirm);
	}

}
