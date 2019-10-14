package biblioteca.dao;

import java.sql.*;

public class Conexao {
	
	private Connection con = null;
	private Statement smt = null;
	private boolean conectado = false;
	
	public Connection getCon() {
		return con;
	}
	public boolean conecte () {
		String urlBanco = "jdbc:mysql://127.0.0.1/teste?useSSL=False";
		String nome = "root";
		String senha = "123456789";
		if (conectado) {
			System.out.println("ja esta conectado!");
		}else {
			try {
				con = DriverManager.getConnection(urlBanco, nome, senha);
				smt = con.createStatement();
				conectado = true;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(),
						sqle.getMessage());
			}
		}
		return conectado;
	}

	public void desconecta () {
		
		if (!conectado) {
			System.out.println("Ja esta desconectado!");
		}else {
			try {
				con.close();
				conectado = false;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(),
						sqle.getMessage());
			}
		}
		
	}
	
}


