package objeto;

import java.util.ArrayList;

public class Comissionado extends Assalariado
{

	private float comissao;
	private boolean pagamento;

	public Comissionado(Funcionario obj_comissionado)
	{
		super(obj_comissionado);
		this.comissao=((Comissionado)obj_comissionado).getComissao();
		this.pagamento=((Comissionado)obj_comissionado).isPagamento();
	}
	
	public Comissionado(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento,float salario, float comissao) {
		super(nome, enderenco, matricula, sindicato, tipo_pagamento, salario);
		// TODO Auto-generated constructor stub
		this.comissao=comissao;
		this.pagamento=true;
	}
	
	public Comissionado(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento,float salario, float comissao, Sindicato obj_sindicato) {
		super(nome, enderenco, matricula, sindicato, tipo_pagamento, salario, obj_sindicato);
		// TODO Auto-generated constructor stub
		this.comissao=comissao;
		this.pagamento=true;
	}
	
	public boolean isPagamento() {
		return pagamento;
	}

	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}

	public float getComissao() {
		return comissao;
	}

	public void setComissao(float comissao) {
		this.comissao = comissao;
	}

	@Override
	public String toString() {
		return super.toString().replace("Assalariado [","Comissionado [" ).replace("]", "") +", comissao=" + comissao + "]";
	}
	
	@Override
	public boolean igual(Funcionario obj_funcionario) 
	{
		boolean retorno=false;
		retorno=super.igual(obj_funcionario);
		if(retorno)
		{
			if(((Comissionado)obj_funcionario).getComissao()==this.comissao)
				retorno=true;
			else
				retorno=false;
		}
		return retorno;
	}
	
	@Override
	public String pagamento()
	{
		String pagamento;
		float salario=this.getSalario();
		for(Venda obj_venda : this.list_venda)
		{
			salario+=obj_venda.getValorVenda()*this.comissao;
		}
		if(this.isSindicato())
		{
			for(Pagamento obj_pagamento : this.list_pagamento)
			{
				salario-=obj_pagamento.getTaxaSindicato();
			}
		salario-=this.getObj_sindicato().getTaxa();
		}
		pagamento="O funcionario "+this.getNome()+" recebeu de salario "+ salario +" pois:\n";
		pagamento=pagamento.concat("Salario fixo: "+this.getSalario());
		for(Venda obj_venda : this.list_venda)
		{
			pagamento=pagamento.concat("\nData: "+obj_venda.getData()+"  Venda: "+obj_venda.getValorVenda() + "   comissao: " 
		+ obj_venda.getValorVenda()*this.comissao + "(" + this.comissao*100 + "%)");
		}
		if(this.isSindicato())
		{
			for(Pagamento obj_pagamento : this.list_pagamento)
			{
				pagamento=pagamento.concat("\nTaxas sindicais: -" + obj_pagamento.getTaxaSindicato());
			}
			pagamento=pagamento.concat("\nMensalidade sindical: -" + this.getObj_sindicato().getTaxa());
		}
		pagamento=pagamento.concat("\nMetodo de pagamento: ");
		switch(this.getTipo_pagamento())
		{
		case 0:
			pagamento=pagamento.concat("Cheque pelos correios");
			break;
		case 1:
			pagamento=pagamento.concat("Cheque em maos");
			break;
		case 2:
			pagamento=pagamento.concat("Deposito");
			break;
		}
		return pagamento;
		
	}

}
