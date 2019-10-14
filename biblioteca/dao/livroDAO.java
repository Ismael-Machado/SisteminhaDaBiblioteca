
package biblioteca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblioteca.empty.Livro;
import biblioteca.empty.Usuario;

public class livroDAO {
	private final String idMaxSQL ="SELECT MAX(codigo) FROM livro";
	private final String insertSQL ="INSERT INTO livro (autor,editora,titulo,ano,status) VALUES (?,?,?,?,?)";
	private final String selectIdSQL ="SELECT * FROM livro WHERE codigo = ?";
	private final String selectTitleSQL ="SELECT * FROM livro WHERE titulo = ?";
	private final String updateSQL ="UPDATE livro SET autor=?,editora=?,titulo=?,ano=? WHERE codigo=?";
	private final String selectSQL ="SELECT * FROM livro";
	private final String deleteSQL ="UPDATE livro SET status=? WHERE codigo=?";
	private final String selectTituloSQL ="SELECT * FROM livro WHERE titulo LIKE ? ";
	private final String consultaCodigoSQL ="SELECT * FROM livro WHERE codigo=? LIMIT 1";
	private final String consultaTituloSQL ="SELECT * FROM livro WHERE titulo LIKE ?";
	private final String ativarSQL ="UPDATE livro SET status=? WHERE titulo=?";
	
	public int IdMax() {
		Conexao con = new Conexao();
		con.conecte();
		int id=-1;
		ResultSet rs;
		try {
			rs = con.getCon().createStatement().executeQuery(idMaxSQL);
			rs.next();
			id  = rs.getInt(1);
		}catch(SQLException e){
			
		}
		con.desconecta();
		return id;
		
	}
	public void ativarCadastro(String titulo) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(ativarSQL);
			ps.setBoolean(1, true);
			ps.setString(2, titulo);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Ativado Com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public void insert(Livro l) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps;
			ps = con.getCon().prepareStatement(insertSQL);
			ps.setString(1, l.getAutor());
			ps.setString(2, l.getEditora());
			ps.setString(3, l.getTitulo());
			ps.setInt(4, l.getAno());
			ps.setBoolean(5, l.isStatus());
			ps.execute();
//			JOptionPane.showMessageDialog(null, "Livro Cadastrado com Sucesso!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public Livro getLivro(int id) {
		Conexao con = new Conexao();
		con.conecte();
		Livro l = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectIdSQL);
			ResultSet rs;
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			l = new Livro(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5));
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
		return l;
	}
	public Livro getLivro(String titulo) {
		Conexao con = new Conexao();
		con.conecte();
		Livro l = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectTitleSQL);
			ResultSet rs;
			ps.setString(1, titulo);
			rs = ps.executeQuery();
			rs.next();
			l = new Livro(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5));
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
		return l;
	}
	public void atualizar(Livro l) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(updateSQL);
			ps.setString(1, l.getAutor());
			ps.setString(2, l.getEditora());
			ps.setString(3, l.getTitulo());
			ps.setInt(4, l.getAno());
			ps.setInt(5, l.getLivroCodigo());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL!!!");
		}
		con.desconecta();
	}
	public void deletar(int id) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(deleteSQL);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Deletado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL!!!");
		}
		con.desconecta();
	}
	public List<Livro> listar(){
		Conexao con = new Conexao();
		con.conecte();
		List<Livro> lista = new ArrayList<>();
		try {
			ResultSet rs = con.getCon().createStatement().executeQuery(selectSQL);
			while(rs.next()) {
				if(rs.getBoolean(6)) {
					Livro l = new Livro(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5));
					lista.add(l);
				}
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro SQL");
		}
		con.desconecta();
		return lista;
	}
	public List<Livro> listar(String titulo){
		Conexao con = new Conexao();
		con.conecte();
		List<Livro> lista = new ArrayList<>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectTituloSQL);
			ps.setString(1, "%"+titulo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean(6)) {
					Livro l = new Livro(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5));
					lista.add(l);
				}
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro SQL");
		}
		con.desconecta();
		return lista;
	}
	public int consultar(int codigo,boolean status) {
		Conexao con = new Conexao();
		con.conecte();
		int result = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaCodigoSQL);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) 
				if(rs.getBoolean(6) == status)
					result=rs.getInt(1);
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		con.desconecta();
		return result;
	}
	public boolean consultar(String titulo,boolean status) {
		Conexao con = new Conexao();
		con.conecte();
		boolean result = false;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaTituloSQL);
			ps.setString(1, "%"+titulo+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				if(rs.getBoolean(6) == status)
					result = true;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		con.desconecta();
		return result;
	}
}
