package objeto;

public class Pagamento {
	
	private int idFuncionario;
	private float taxaSindicato;
	public Pagamento(int idFuncionario, float taxaSindicato) 
	{
		this.idFuncionario = idFuncionario;
		this.taxaSindicato = taxaSindicato;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public float getTaxaSindicato() {
		return taxaSindicato;
	}
	public void setTaxaSindicato(float taxaSindicato) {
		this.taxaSindicato = taxaSindicato;
	}
	

}
