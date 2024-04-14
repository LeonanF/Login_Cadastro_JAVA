package controller;

import model.User;
import model.UserDAO;

public class DBController {
	private UserDAO userDAO;
	
	
	public DBController() {
		this.userDAO = new UserDAO();
	}
	
	public boolean inserirPessoa(User user) {
		return userDAO.inserirPessoa(user);
	}
	
	public boolean alterarPessoa(User user) {
		return userDAO.alterarPessoa(user);
	}
	
	public boolean verificarLogin(String username, String password) {
		return userDAO.verificarLogin(username, password);
	}
	
	public boolean verificarUsuario(String cpf) {
		return userDAO.verificarUsuario(cpf);
	}
	
	public boolean removerPessoa(String cpf) {
		return userDAO.removerPessoa(cpf);
	}
	
	public boolean alterarSenha(String cpf, String password) {
		return userDAO.alterarSenha(cpf, password);
	}
	
	public User obterUsuario(String cpf) {
		return userDAO.obterUsuario(cpf);
	}
	
}
