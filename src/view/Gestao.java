package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.DBController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Gestao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DBController userController;


	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Gestao(DBController controller) throws ParseException {
		
		this.userController = controller;
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Digite o CPF do usuário:");
		userLabel.setBounds(10, 11, 214, 18);
		userLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(userLabel);

		final JFormattedTextField cpfField = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		cpfField.setBounds(10, 40, 214, 20);
		contentPane.add(cpfField);
		
		JButton btnAlter = new JButton("Alterar usuário");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpf = cpfField.getText().replaceAll("[^0-9]", "");
				
				if(userController.verificarUsuario(cpf)){
					try {
						new Alterar(userController, userController.obterUsuario(cpf)).setVisible(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Erro ao buscar usuário. Verifique o CPF e tente novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        btnAlter.setBorderPainted(false);
        btnAlter.setFocusPainted(false);
        btnAlter.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnAlter.setRolloverEnabled(false);
        btnAlter.setForeground(new Color(128, 128, 0));
        btnAlter.setBackground(new Color(240,240,240));
		btnAlter.setBounds(10, 227, 115, 23);
		contentPane.add(btnAlter);
		
		JButton btnDelete = new JButton("Apagar usuário");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cpf = cpfField.getText().replaceAll("[^0-9]", "");
				
				if(userController.removerPessoa(cpf)) {
					JOptionPane.showMessageDialog(contentPane, "Usuário deletado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Erro ao apagar usuário. Verifique o CPF e tente novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDelete.setRolloverEnabled(false);
        btnDelete.setForeground(new Color(128, 128, 0));
        btnDelete.setBackground(new Color(240,240,240));
		btnDelete.setBounds(309, 227, 115, 23);
		contentPane.add(btnDelete);
	}
}
