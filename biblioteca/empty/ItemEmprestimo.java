package biblioteca.empty;

import java.util.Date;

public class ItemEmprestimo {
	private int id;
	private int idVolume;
	private int idUsuario;
	private Date dtEmprestimo,dtDevolucao;
	private boolean status;
	
	public ItemEmprestimo(int id,int idUsuario,int idVolume,boolean status) {
		this.id=id;
		this.idVolume = idVolume;
		this.idUsuario = idUsuario;
		this.status = status;
	}
	public ItemEmprestimo(int idVolume,int idUsuario,Date dtEmprestimo, Date dtDevolucao) {
		this.idVolume = idVolume;
		this.idUsuario = idUsuario;
		this.dtEmprestimo = dtEmprestimo;
		this.dtDevolucao = dtDevolucao;
		this.status = true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdVolume() {
		return idVolume;
	}
	public void setIdVolume(int idVolume) {
		this.idVolume = idVolume;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
