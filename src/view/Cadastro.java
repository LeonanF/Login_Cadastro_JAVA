package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.DBController;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DBController userController;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Cadastro(DBController controller) throws ParseException {
		
		this.userController = controller;
		
		setResizable(false);
		setFont(new Font("Calibri", Font.PLAIN, 20));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel addressLabel = new JLabel("Endereço");
		addressLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel phoneLabel = new JLabel("Telefone");
		phoneLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel cpfLabel = new JLabel("CPF");
		cpfLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel bloodTypeLabel = new JLabel("Tipo Sanguíneo");
		bloodTypeLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel courseLabel = new JLabel("Curso");
		courseLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel emergencyPhoneLabel = new JLabel("Telefone de emergência");
		emergencyPhoneLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setColumns(10);
				
		JComboBox<Object> courseBox = new JComboBox<Object>();
		courseBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Ciência da Computação", "Engenharia de Software"}));
		
		JLabel rhFactorLabel = new JLabel("Fator RH");
		rhFactorLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JComboBox<Object> bloodtypeBox = new JComboBox<Object>();
		bloodtypeBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "A", "B", "AB", "O"}));
		
		JComboBox<Object> rhFactorBox = new JComboBox<Object>();
		rhFactorBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "+", "-"}));
	

		final JFormattedTextField emergencyPhoneField = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		
		final JFormattedTextField cpfField = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		
		
		final JFormattedTextField phoneField = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		
		JLabel usernameLabel = new JLabel("Nome de usuário");
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();

		JButton sendButton = new JButton("Enviar");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String phone = phoneField.getText().replaceAll("[^0-9]", "");
				String cpf = cpfField.getText().replaceAll("[^0-9]", "");
				String emergencyPhone = emergencyPhoneField.getText().replaceAll("[^0-9]", "");
				
				if (nameField.getText().isBlank() || addressField.getText().isBlank() || phoneField.getText().isBlank() || cpfField.getText().isBlank() || emergencyPhoneField.getText().isBlank() || usernameField.getText().isBlank() || new String(passwordField.getPassword()).isBlank()) {
				    JOptionPane.showMessageDialog(contentPane, "Todos os campos devem ser preenchidos.", "Alerta", JOptionPane.WARNING_MESSAGE);
				    return;
				}
				
				User user = new User(nameField.getText(),
									addressField.getText(),
									phone,
									cpf,
									(String) bloodtypeBox.getSelectedItem(),
									(String) rhFactorBox.getSelectedItem(),
									(String) courseBox.getSelectedItem(),
									emergencyPhone,
									usernameField.getText(),
									new String(passwordField.getPassword()));
				
				if(userController.inserirPessoa(user)) {
					new Login(userController).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Falha ao adicionar usuário.", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login(userController).setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Calibri", Font.PLAIN, 15));
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cancelButton.setForeground(new Color(128, 128, 0));
		cancelButton.setBackground(new Color(240,240,240));
		
		sendButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sendButton.setForeground(new Color(128, 128, 0));
		sendButton.setBackground(new Color(240,240,240));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cancelButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(addressLabel)
								.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(cpfLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(bloodTypeLabel)
								.addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(emergencyPhoneLabel)
								.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addComponent(phoneField)
								.addComponent(cpfField)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(bloodtypeBox, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rhFactorLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rhFactorBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(courseBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(addressField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(emergencyPhoneField)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
					.addGap(79))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpfLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(cpfField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bloodTypeLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(bloodtypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rhFactorLabel)
						.addComponent(rhFactorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(emergencyPhoneLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(emergencyPhoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(sendButton)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
