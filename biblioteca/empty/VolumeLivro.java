package biblioteca.empty;

public class VolumeLivro {
	private int codigoLivro;
	private int exemplar;
	private boolean status;
	
	public VolumeLivro(int exemplar,int codigoLivro,boolean status) {
		this.exemplar = exemplar;
		this.codigoLivro = codigoLivro;
		this.status = status;		
	}
	public VolumeLivro(int codigoLivro,int quantExemp) {
		this.codigoLivro = codigoLivro;
		this.exemplar = quantExemp;
	}
	public int getCodigoLivro() {
		return codigoLivro;
	}
	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	public int getExemplar() {
		return exemplar;
	}
	public void setExemplar(int exemplar) {
		this.exemplar = exemplar;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
