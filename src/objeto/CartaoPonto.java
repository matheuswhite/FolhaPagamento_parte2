package objeto;

import java.util.Date;
import java.util.GregorianCalendar;
import java.text.*;

public class CartaoPonto 
{
	//utilize o Date no ultimo caso!
	private Date data;
	private SimpleDateFormat format;
	public GregorianCalendar gegorianCal;
	public GregorianCalendar horaInicial;
	public GregorianCalendar horaFinal;
	
	//poderia ajustar as horas e os minutos finais num mesmo 
	public CartaoPonto(int horaInicial, int minutoInicial, int horaFinal, int minutoFinal)
	{
		this.format=new SimpleDateFormat("dd/MM/yyyy");
		this.data=new Date();
		this.gegorianCal=new GregorianCalendar();
		this.gegorianCal.set(2014, 9, 01);
		this.horaInicial=new GregorianCalendar();
		this.horaFinal=new GregorianCalendar();
		this.horaInicial.set(this.horaInicial.HOUR_OF_DAY, horaInicial);
		this.horaInicial.set(this.horaInicial.MINUTE, minutoInicial);
		this.horaFinal.set(this.horaFinal.HOUR_OF_DAY, horaFinal);
		this.horaFinal.set(this.horaFinal.MINUTE, minutoFinal);
	}
	
	//nâo use metodos depreciados
	//não há garantia que eles funcionem corretamente
	public void setDate(String date)
	{
		try 
		{
			data=format.parse(date);
			this.gegorianCal.set(data.getYear(), data.getMonth(), data.getDate());
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public float horasTrabalhadas()
	{
		float horas, minuto;
		horas=this.horaFinal.get(this.horaFinal.HOUR_OF_DAY)-this.horaInicial.get(this.horaInicial.HOUR_OF_DAY);
		if(horas<0)
		{
			horas=24+horas;
		}
		minuto=this.horaFinal.get(this.horaFinal.MINUTE)-this.horaInicial.get(this.horaInicial.MINUTE);
		if(minuto<0)
		{
			minuto=60+minuto;
		}
		minuto=minuto/60;
		horas+=minuto;
		return horas;
	}
	
	public String getData()
	{
		return format.format(data);
	}
	

}
