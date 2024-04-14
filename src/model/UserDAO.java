package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	private DBConnection dbConnection;
	
	public UserDAO() {
		dbConnection = new DBConnection();
	}
	
	public boolean inserirPessoa(User user) {
		Connection con = dbConnection.getConnection();
		try {
			PreparedStatement psInsert = con.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			psInsert.setString(1, user.getName());
			psInsert.setString(2, user.getAddress());
			psInsert.setString(3, user.getPhone());
			psInsert.setString(4, user.getCpf());
			psInsert.setString(5, user.getBloodtype());
			psInsert.setString(6, user.getRhFactor());
			psInsert.setString(7, user.getCourse());
			psInsert.setString(8, user.getEmergencyPhone());
			psInsert.setString(9, user.getUsername());
			psInsert.setString(10, user.getPassword());
			return psInsert.executeUpdate()>0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean alterarPessoa(User user) {
		Connection con = dbConnection.getConnection();
		try {
			PreparedStatement psAlter = con.prepareStatement("UPDATE USERS SET name=?, address=?, phone=?, bloodtype=?, rhFactor=?, course=?, emergencyphone=? WHERE cpf=?;");
			psAlter.setString(1, user.getName());
			psAlter.setString(2, user.getAddress());
			psAlter.setString(3, user.getPhone());
			psAlter.setString(4, user.getBloodtype());
			psAlter.setString(5, user.getRhFactor());
			psAlter.setString(6, user.getCourse());
			psAlter.setString(7, user.getEmergencyPhone());
			psAlter.setString(8, user.getCpf());
			return psAlter.executeUpdate()>0;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removerPessoa(String cpf) {
		Connection con = dbConnection.getConnection();
		
		try {
			PreparedStatement psDelete = con.prepareStatement("DELETE FROM USERS WHERE cpf=?;");
			psDelete.setString(1, cpf);
			return psDelete.executeUpdate()>0;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verificarLogin(String username, String password) {
		boolean loginCorreto = false;
		Connection con = dbConnection.getConnection();
		
		try {
			PreparedStatement psSelect = con.prepareStatement("SELECT * FROM USERS WHERE username = ? AND password=?");
			psSelect.setString(1, username);
			psSelect.setString(2, password);
			ResultSet result = psSelect.executeQuery();
			loginCorreto = result.next();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return loginCorreto;
	}

	public boolean verificarUsuario(String cpf) {
		boolean usuarioExiste = false;
		Connection con = dbConnection.getConnection();
		
		try {
			PreparedStatement psSelect = con.prepareStatement("SELECT * FROM USERS WHERE cpf = ?");
			psSelect.setString(1, cpf);
			ResultSet result = psSelect.executeQuery();
			usuarioExiste = result.next();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return usuarioExiste;
	}
	
	
	public boolean alterarSenha(String cpf, String password) {
		Connection con = dbConnection.getConnection();
		
		try {
			PreparedStatement psAlterPassword = con.prepareStatement("UPDATE USERS SET password=? WHERE cpf=?");
			psAlterPassword.setString(1, password);
			psAlterPassword.setString(2, cpf);
			return psAlterPassword.executeUpdate()>0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public User obterUsuario(String cpf){
		User user=null;
		Connection con = dbConnection.getConnection();
		
		try {
			PreparedStatement psSelect = con.prepareStatement("SELECT * FROM USERS WHERE cpf = ?");
			psSelect.setString(1, cpf);
			ResultSet result = psSelect.executeQuery();
			if(result.next()) {
				user = new User(
		                result.getString("name"),
		                result.getString("address"),
		                result.getString("phone"),
		                result.getString("cpf"),
		                result.getString("bloodtype"),
		                result.getString("rhFactor"),
		                result.getString("course"),
		                result.getString("emergencyPhone"),
		                result.getString("username"),
		                result.getString("password")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}