package biblioteca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import biblioteca.empty.Livro;
import biblioteca.empty.Usuario;
import biblioteca.empty.VolumeLivro;

public class VolumeLivroDAO {
	private final String insertSQL = "INSERT INTO volumeLivro (codigoLivro,status) VALUES (?,?)";
	private final String quantidade = "SELECT count(*) FROM volumeLivro WHERE codigoLivro = ?";
	private final String quantDisponivel = "SELECT count(*) FROM volumeLivro WHERE codigoLivro = ? AND status=?";
	private final String IdDisponivel = "SELECT exemplar FROM volumeLivro WHERE codigoLivro = ? AND status=? LIMIT 1";
	private final String emprestar = "UPDATE volumeLivro SET status=? WHERE exemplar = ?";
	private final String encerrar = "UPDATE volumeLivro SET status=? WHERE exemplar = ?";
	private final String delete = "DELETE FROM volumeLivro WHERE exemplar=?";
	private final String selectSQL ="SELECT * FROM volumeLivro WHERE exemplar=?";
	
	
	public void inserir(VolumeLivro vl) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			while(vl.getExemplar()>0) {
				PreparedStatement ps = con.getCon().prepareStatement(insertSQL);
				ps.setInt(1, vl.getCodigoLivro());
				ps.setBoolean(2, true);
				ps.execute();
				vl.setExemplar(vl.getExemplar()-1);
			}
//			JOptionPane.showMessageDialog(null, "inserido com sucesso");
		}catch (SQLException sqle) {
			// TODO: handle exception
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "Erro SQLkkk!!!");
		}
		con.desconecta();	
	}
	public int getQuantidade(int idLivro) {
		Conexao con = new Conexao();
		con.conecte();
		int quant = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(quantidade);
			ps.setInt(1, idLivro);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				quant = (int)rs.getInt(1);
		}catch (SQLException sqle) {
			// TODO: handle exception
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "Erro SQL!!!");
		}
		con.desconecta();
		return quant;
	}
	public int getQuantDisponivel(int idLivro) {
		Conexao con = new Conexao();
		con.conecte();
		int quant = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(quantDisponivel);
			ps.setInt(1, idLivro);
			ps.setBoolean(2, true);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				quant = (int)rs.getInt(1);
		}catch (SQLException sqle) {
			// TODO: handle exception
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "Erro SQL!!!");
		}
		con.desconecta();
		return quant;
	}
	public int getIdDisponivel(int idLivro) {
		Conexao con = new Conexao();
		con.conecte();
		int quant = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(IdDisponivel);
			ps.setInt(1, idLivro);
			ps.setBoolean(2, true);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				quant = (int)rs.getInt(1);
		}catch (SQLException sqle) {
			// TODO: handle exception
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "Erro SQL!!!");
		}
		con.desconecta();
		return quant;
	}
	public void Emprestar(int idVolume) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(emprestar);
			ps.setBoolean(1, false);
			ps.setInt(2, idVolume);
			ps.execute();
//			JOptionPane.showMessageDialog(null, "Realizado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public void Encerrar(int idVolume) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(encerrar);
			ps.setBoolean(1, true);
			ps.setInt(2, idVolume);
			ps.execute();
//			JOptionPane.showMessageDialog(null, "Realizado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public void Excluir(int idVolume) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(delete);
			ps.setInt(1, idVolume);
			ps.execute();
//			JOptionPane.showMessageDialog(null, "Excluido com sucesso com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public VolumeLivro getExemplar(int id) {
		Conexao con = new Conexao();
		con.conecte();
		VolumeLivro vl = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectSQL);
			ResultSet rs;
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			vl = new VolumeLivro(rs.getInt(1),
							rs.getInt(2),
							rs.getBoolean(3));
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
		return vl;
	}
}
