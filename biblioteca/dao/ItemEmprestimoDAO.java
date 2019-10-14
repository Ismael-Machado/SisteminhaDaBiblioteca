package biblioteca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblioteca.empty.ItemEmprestimo;
import biblioteca.empty.Livro;
import biblioteca.empty.Usuario;

public class ItemEmprestimoDAO {
	private final String consultarQuant ="SELECT count(*) FROM itemEmprestimo WHERE idUsuario = ? AND status=?";
	private final String insertSQL ="INSERT INTO itemEmprestimo (idUsuario,idVolume,status) VALUES (?,?,?)";
	private final String selectSQL ="SELECT * FROM itemEmprestimo WHERE status=true";
	private final String selectIdSQL ="SELECT * FROM itemEmprestimo WHERE id = ?";
	private final String deleteSQL ="UPDATE itemEmprestimo SET status=? WHERE id=?";
	private final String consultaIdSQL ="SELECT * FROM itemEmprestimo WHERE id=? AND status=? LIMIT 1";
	
	public int getQuantidade(int idUsuario) {
		Conexao con = new Conexao();
		con.conecte();
		int quant = 0;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultarQuant);
			ps.setInt(1, idUsuario);
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
	public void inserir(ItemEmprestimo ie) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(insertSQL);
			ps.setInt(1, ie.getIdUsuario());
			ps.setInt(2, ie.getIdVolume());
			ps.setBoolean(3, true);
			ps.execute();
//			JOptionPane.showMessageDialog(null, "inserido com sucesso");
		}catch (SQLException sqle) {
			// TODO: handle exception
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "Erro SQL!!!");
		}
		con.desconecta();	
	}
	public List<ItemEmprestimo> listar(){
		Conexao con = new Conexao();
		con.conecte();
		List<ItemEmprestimo> lista = new ArrayList<>();
		try {
			ResultSet rs = con.getCon().createStatement().executeQuery(selectSQL);
			while(rs.next()) {
				ItemEmprestimo ie = new ItemEmprestimo(rs.getInt(1),
										rs.getInt(2),
										rs.getInt(3),
										rs.getBoolean(4));
				lista.add(ie);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro SQL");
		}
		con.desconecta();
		return lista;
	}
	public ItemEmprestimo getItemEmprestimo(int id) {
		Conexao con = new Conexao();
		con.conecte();
		ItemEmprestimo ie = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectIdSQL);
			ResultSet rs;
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			ie = new ItemEmprestimo(rs.getInt(1),
									rs.getInt(2),
									rs.getInt(3),
									rs.getBoolean(4));
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
		return ie;
	}
	public void deletar(int id) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(deleteSQL);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.execute();
//			JOptionPane.showMessageDialog(null, "Encerrado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL!!!");
		}
		con.desconecta();
	}
	public boolean consultar(int id) {
		Conexao con = new Conexao();
		con.conecte();
		boolean result = true;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaIdSQL);
			ps.setInt(1, id);
			ps.setBoolean(2, true);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())
				result=false;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		con.desconecta();
		return result;
	}
}
