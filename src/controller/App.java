package controller;

import view.Login;

public class App {
	public static void main(String[] args) {
		
		DBController userController = new DBController();
		Login telaInicial = new Login(userController);
		telaInicial.setVisible(true);
	
	}
}
