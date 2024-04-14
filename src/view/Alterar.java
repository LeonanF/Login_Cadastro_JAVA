package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.DBController;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Alterar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DBController userController;
	private JTextField addressField;
	private JTextField nameField;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Alterar(DBController controller, User user) throws ParseException {
		
		this.userController = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane_1);
		
		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel addressLabel = new JLabel("Endereço");
		addressLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel phoneLabel = new JLabel("Telefone");
		phoneLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel courseLabel = new JLabel("Curso");
		courseLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JLabel emergencyPhoneLabel = new JLabel("Telefone de emergência");
		emergencyPhoneLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		

		JLabel bloodTypeLabel = new JLabel("Tipo Sanguíneo");
		bloodTypeLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JComboBox<Object> bloodtypeBox = new JComboBox<Object>();
		bloodtypeBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "A", "B", "AB", "O"}));
		bloodtypeBox.setSelectedItem(user.getBloodtype());
		
		JLabel rhFactorLabel = new JLabel("Fator RH");
		rhFactorLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		JComboBox<Object> rhFactorBox = new JComboBox<Object>();
		rhFactorBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "+", "-"}));
		rhFactorBox.setSelectedItem(user.getRhFactor());
		
		JComboBox<Object> courseBox = new JComboBox<Object>();
		courseBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Ciência da Computação", "Engenharia de Software"}));
		courseBox.setSelectedItem(user.getCourse());
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setText(user.getAddress());
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setText(user.getName());
		
		final JFormattedTextField emergencyPhoneField = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		emergencyPhoneField.setText(user.getEmergencyPhone());

		final JFormattedTextField phoneField = new JFormattedTextField(new MaskFormatter("(##) #####-####"));		
		phoneField.setText(user.getPhone());
		
		JButton confirmButton = new JButton("Confirmar");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String phone = phoneField.getText().replaceAll("[^0-9]", "");
				String emergencyPhone = emergencyPhoneField.getText().replaceAll("[^0-9]", "");
				
				if (nameField.getText().isBlank() || addressField.getText().isBlank() || phoneField.getText().isBlank() || emergencyPhoneField.getText().isBlank()) {
				    JOptionPane.showMessageDialog(contentPane, "Todos os campos devem ser preenchidos.", "Alerta", JOptionPane.WARNING_MESSAGE);
				    return;
				}
				
				user.setName(nameField.getText());
				user.setAddress(addressField.getText());
				user.setBloodtype((String) bloodtypeBox.getSelectedItem());
				user.setCourse((String) courseBox.getSelectedItem());
				user.setEmergencyPhone(emergencyPhone);
				user.setPhone(phone);
				user.setRhFactor((String)rhFactorBox.getSelectedItem());
				
				if(userController.alterarPessoa(user)) {
					new Login(userController).setVisible(true);
					dispose();
				}	else {
					JOptionPane.showMessageDialog(contentPane, "Falha ao alterar usuário.", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		confirmButton.setHorizontalAlignment(SwingConstants.LEFT);
		confirmButton.setForeground(new Color(128, 128, 0));
		confirmButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		confirmButton.setFocusPainted(false);
		confirmButton.setBorderPainted(false);
		confirmButton.setBackground(UIManager.getColor("Button.background"));
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Gestao(userController).setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		cancelButton.setForeground(new Color(128, 128, 0));
		cancelButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		cancelButton.setFocusPainted(false);
		cancelButton.setBorderPainted(false);
		cancelButton.setBackground(UIManager.getColor("Button.background"));
		
		GroupLayout gl_contentPane_1 = new GroupLayout(contentPane_1);
		gl_contentPane_1.setHorizontalGroup(
			gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(addressLabel)
								.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(confirmButton))
							.addGap(69)
							.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(phoneField)
								.addComponent(addressField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addGroup(gl_contentPane_1.createSequentialGroup()
									.addComponent(bloodtypeBox, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rhFactorLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rhFactorBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(courseBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(emergencyPhoneField)
								.addComponent(cancelButton, Alignment.TRAILING)))
						.addComponent(bloodTypeLabel)
						.addComponent(emergencyPhoneLabel))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_contentPane_1.setVerticalGroup(
			gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bloodTypeLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(bloodtypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rhFactorLabel)
						.addComponent(rhFactorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(emergencyPhoneLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(emergencyPhoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmButton))
					.addGap(140))
		);
		contentPane_1.setLayout(gl_contentPane_1);
	}

}
