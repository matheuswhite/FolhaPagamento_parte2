package objeto;

public class Sindicato {

	private int matricula_sindicato;
	private float taxa;
	public Sindicato(int matricula_sindicato, float taxa) {
		this.matricula_sindicato = matricula_sindicato;
		this.taxa = taxa;
	}
	public int getMatricula_sindicato() {
		return matricula_sindicato;
	}
	public void setMatricula_sindicato(int matricula_sindicato) {
		this.matricula_sindicato = matricula_sindicato;
	}
	public float getTaxa() {
		return taxa;
	}
	public void setTaxa(float taxa) {
		this.taxa = taxa;
	}
	
}
