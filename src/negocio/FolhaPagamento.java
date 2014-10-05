package negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import objeto.*;
@SuppressWarnings("unused")
public class FolhaPagamento {
	
	private Date data;
	private SimpleDateFormat format;
	public GregorianCalendar gegorianCal;
	
	@SuppressWarnings("static-access")
	public FolhaPagamento(int dia, int mes, int ano)
	{
		this.gegorianCal=new GregorianCalendar();
		this.gegorianCal.set(this.gegorianCal.DATE, dia);
		this.gegorianCal.set(this.gegorianCal.MONTH, mes);
		this.gegorianCal.set(this.gegorianCal.YEAR, ano);
	}
	@SuppressWarnings("static-access")
	public ArrayList<Funcionario> baterPonto(ArrayList<Funcionario> list_funcionario)
	{
		this.gegorianCal.add(this.gegorianCal.DATE, 1);
		for(Funcionario obj_funcionario : list_funcionario)
		{
			if(obj_funcionario instanceof Comissionado)
			{
				if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
				{
					if(((Comissionado) obj_funcionario).isPagamento())
					{
						((Comissionado) obj_funcionario).setPagamento(false);
					}
					else
						((Comissionado) obj_funcionario).setPagamento(true);
				}
			}
		}
		return list_funcionario;
	}
	
	@SuppressWarnings("static-access")
	public void retornaDia()
	{
		this.gegorianCal.add(this.gegorianCal.DATE, -1);
	}
	@SuppressWarnings("static-access")
	public void adicionaDia()
	{
		this.gegorianCal.add(this.gegorianCal.DATE, 1);
	}
	
	//poderia usar um switch
	
	@SuppressWarnings("static-access")
	public String getDate()
	{
		String data = this.gegorianCal.get(this.gegorianCal.DATE)+"/"+(this.gegorianCal.get(this.gegorianCal.MONTH)+1)+"/"+this.gegorianCal.get(this.gegorianCal.YEAR);
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.SUNDAY)
		{
			data=data.concat("(Domingo)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.MONDAY)
		{
			data=data.concat("(Segunda)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.TUESDAY)
		{
			data=data.concat("(Terca)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.WEDNESDAY)
		{
			data=data.concat("(Quarta)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.THURSDAY)
		{
			data=data.concat("(Quinta)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
		{
			data=data.concat("(Sexta)");
		}
		if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.SATURDAY)
		{
			data=data.concat("(Sabado)");
		}
		return data;
	}
	
	@SuppressWarnings("static-access")
	public String geraPagamento(ArrayList<Funcionario> list_funcionario)
	{
		String pagamento="\n";
		ArrayList<Funcionario> list_funcionario_aux = new ArrayList<Funcionario>(list_funcionario);
		for(Funcionario obj_funcionario : list_funcionario_aux)
		{
			if(obj_funcionario instanceof Assalariado && ! (obj_funcionario instanceof Comissionado))
			{
				if(this.gegorianCal.get(this.gegorianCal.DATE)==this.gegorianCal.getMaximum(this.gegorianCal.DATE))
				{
					pagamento=pagamento.concat("\n\n"+obj_funcionario.pagamento());
				}
			}
			if(obj_funcionario instanceof Horista)
			{
				if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
				{
					pagamento=pagamento.concat("\n\n"+obj_funcionario.pagamento());
				}
			}
			if(obj_funcionario instanceof Comissionado)
			{
				if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
				{
					if(((Comissionado) obj_funcionario).isPagamento())
					{
						pagamento=pagamento.concat("\n\n"+obj_funcionario.pagamento());
					}
				}
			}
		}
		return pagamento;
	}

	
	//eu acho que esse método vai dar um bug monstruoso
	@SuppressWarnings("static-access")
	public ArrayList<Funcionario> limpaBoleto(ArrayList<Funcionario> list_funcionario)
	{
		ArrayList<Funcionario> list_funcionario_aux = new ArrayList<Funcionario>(list_funcionario);
		for(int i=0 ; i < list_funcionario_aux.size() ; i++)
		{
			if(list_funcionario_aux.get(i) instanceof Assalariado && ! (list_funcionario.get(i) instanceof Comissionado))
			{
				int diaultil = 0;
				if(this.gegorianCal.SUNDAY==this.gegorianCal.getMaximum(this.gegorianCal.DATE))
					diaultil=-2;
				if(this.gegorianCal.SATURDAY==this.gegorianCal.getMaximum(this.gegorianCal.DATE))
					diaultil=-1;
				if(this.gegorianCal.get(this.gegorianCal.DATE)+diaultil==this.gegorianCal.getMaximum(this.gegorianCal.DATE))
				{
					list_funcionario_aux.set(i, new Assalariado(list_funcionario_aux.get(i)));
					list_funcionario_aux.get(i).list_pagamento.clear();
				}
			}
			if(list_funcionario.get(i) instanceof Horista)
			{
				if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
				{
					list_funcionario_aux.set(i, new Horista(list_funcionario_aux.get(i)));
					list_funcionario_aux.get(i).list_cartao.clear();
					list_funcionario_aux.get(i).list_pagamento.clear();
				}
			}
			if(list_funcionario.get(i) instanceof Comissionado)
			{
				if(this.gegorianCal.get(this.gegorianCal.DAY_OF_WEEK)==this.gegorianCal.FRIDAY)
				{
					if(((Comissionado) list_funcionario.get(i)).isPagamento())
					{
						list_funcionario_aux.set(i, new Comissionado(list_funcionario_aux.get(i)));
						list_funcionario_aux.get(i).list_venda.clear();
						list_funcionario_aux.get(i).list_pagamento.clear();
					}
				}
			}
		}
		return list_funcionario_aux;
	}
}
