package biblioteca.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblioteca.empty.Pessoa;
import biblioteca.empty.Usuario;

public class UsuarioDAO {
	private final String idMaxSQL ="SELECT MAX(codigo) FROM usuario";
	private final String insertSQL ="INSERT INTO pessoa (nome,cpf,rg,endereco,telefone,dtNascimento,status) VALUES (?,?,?,?,?,?,?)";
	private final String selectCpfSQL ="SELECT * FROM usuario WHERE cpf = ?";
	private final String selectModificadoSQL ="SELECT * FROM usuario WHERE cpf = ? or usuario = ?";
	private final String updateSQL ="UPDATE usuario SET usuario=?,nome=?,cpf=?,endereco=?,telefone=?,status=? WHERE codigo = ?";
	private final String selectSQL ="SELECT * FROM usuario";
	private final String ativarSQL ="UPDATE usuario SET status=? WHERE codigo = ?";
	private final String consultaSQL ="SELECT * FROM usuario WHERE codigo = ? LIMIT 1";
	private final String consultaSQLcpf ="SELECT status FROM pessoa WHERE cpf = ? LIMIT 1";
	private final String consultaUserSQL ="SELECT codigo FROM usuario WHERE usuario = ?LIMIT 1";
	private final String selectNomeSQL ="SELECT * FROM usuario WHERE nome LIKE ?  or usuario = ?";
	private final String consultaNomeSQL ="SELECT * FROM usuario WHERE nome LIKE ?";
	private final String deleteSQL ="UPDATE usuario SET status=? WHERE codigo = ?";
	
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
	/*public void insert(Usuario u) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps;
			ps = con.getCon().prepareStatement(insertSQL);
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getCPF());
			ps.setString(4, u.getEndereco());
			ps.setString(5, u.getTelefone());
			ps.setBoolean(6, u.isStatus());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}*/
	public void insert(Pessoa p) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps;
			ps = con.getCon().prepareStatement(insertSQL);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setInt(3, p.getRg());
			ps.setString(4, p.getEndereco());
			ps.setString(5, p.getTelefone());
			SimpleDateFormat sdf = new SimpleDateFormat();
			ps.setString(6, sdf.format(p.getDtNascimento()));
			ps.setBoolean(7, p.isStatus());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public Usuario getUsuario(String CPForUser) {
		Conexao con = new Conexao();
		con.conecte();
		Usuario u = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectModificadoSQL);
			ResultSet rs;
			ps.setString(1,CPForUser);
			ps.setString(2,CPForUser);
			rs = ps.executeQuery();
			rs.next();
			u = new Usuario(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getBoolean(7));
		}catch(SQLException sqle) {
			sqle.getMessage();
		}
		con.desconecta();
		return u;
	}
	public Usuario getUsuario(int id) {
		Conexao con = new Conexao();
		con.conecte();
		Usuario u = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaSQL);
			ResultSet rs;
			ps.setInt(1,id);
			rs = ps.executeQuery();
			rs.next();
			u = new Usuario(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getBoolean(7));
		}catch(SQLException sqle) {
			sqle.getMessage();
		}
		con.desconecta();
		return u;
	}
	public void atualizar(Usuario u) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(updateSQL);
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getCPF());
			ps.setString(4, u.getEndereco());
			ps.setString(5, u.getTelefone());
			ps.setBoolean(6, u.isStatus());
			ps.setInt(7, u.getUsuarioCod());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public List<Usuario> listar(){
		Conexao con = new Conexao();
		con.conecte();
		List<Usuario> lista = new ArrayList<>();
		try {
			ResultSet rs = con.getCon().createStatement().executeQuery(selectSQL);
			while(rs.next()) {
				Usuario u = new Usuario(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getBoolean(7));
				lista.add(u);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro SQL");
		}
		con.desconecta();
		return lista;
	}
	public List<Usuario> listar(String nome) {
		Conexao con = new Conexao();
		con.conecte();
		List<Usuario> lista = new ArrayList<>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectNomeSQL);
			ps.setString(1, "%"+nome+"%");
			ps.setString(2, nome);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getBoolean(7));
				lista.add(u);
			}
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
		return lista;
	}
	public int getId(String cpf) {
		Conexao con = new Conexao();
		con.conecte();
		int result = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(selectCpfSQL);
			ResultSet rs;
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next())
				result = rs.getInt(1);
		}catch(SQLException sqle) {
			sqle.getMessage();
		}
		con.desconecta();
		return result;
	}
	public void ativarCadastro(int id) {
		Conexao con = new Conexao();
		con.conecte();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(ativarSQL);
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Ativado Com Sucesso!!");
		}catch(SQLException sqle) {
			sqle.getMessage();
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
	public int consultar(int id) {
		Conexao con = new Conexao();
		con.conecte();
		int result = -1;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaSQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		con.desconecta();
		return result;
	}
	public boolean consultar(String cpf) {
		Conexao con = new Conexao();
		con.conecte();
		boolean status = false;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaSQLcpf);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				status = true;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		con.desconecta();
		return status;
	}
	/*public boolean consultar(String usuario) {
		Conexao con = new Conexao();
		con.conecte();
		boolean result = true;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaUserSQL);
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())
				result = false;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
			result = false;
		}
		con.desconecta();
		return result;
	}*/
	public boolean consulta(String nome){
		Conexao con = new Conexao();
		con.conecte();
		boolean result = true;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(consultaNomeSQL);
			ps.setString(1, "%"+nome+"%");
			ResultSet rs = ps.executeQuery();
			if(!rs.next())
				result = false;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
			result = false;
		}
		con.desconecta();
		return result;
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
			System.out.println(sqle.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO SQL");
		}
		con.desconecta();
	}
}
