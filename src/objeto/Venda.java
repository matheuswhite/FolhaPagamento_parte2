package objeto;

import java.util.GregorianCalendar;

public class Venda {

	//poderia usar o GregorianCalendar
	public String data;
	private float valorVenda;
	private int idFuncionario;
	public Venda(float valorVenda, int idFuncionario, String data) {
		
		this.data=data;
		this.valorVenda = valorVenda;
		this.idFuncionario = idFuncionario;
	}
	public float getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	

}
