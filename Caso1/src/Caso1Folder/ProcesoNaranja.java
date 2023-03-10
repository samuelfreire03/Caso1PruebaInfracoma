package Caso1Folder;

public class ProcesoNaranja extends Thread{
	
	private Numero idProductos;
	
	private BufferLimitado buf;
	
	private BufferLimitado buf2;
	
	private BufferLimitado buf3;
	
	private int cantProductos;
	
	private boolean tipo;
	
	private int etapa;
	
	public ProcesoNaranja(Numero id,BufferLimitado buf,BufferLimitado buf2,BufferLimitado buf3,int cantidadproductos,boolean tipo,int etapa)
	{
		
		this.idProductos = id;
		this.buf = buf;
		this.buf2 = buf2;
		this.cantProductos = cantidadproductos;
		this.tipo = tipo;
		this.etapa = etapa;
		this.buf3 = buf3;
		
	}
	
	private void MandarProducto(int id)
	{
		
		this.buf.insertProductNaranaja("El producto con el id" + id + ", sale del proceso 1 en la etapa 1  a la etapa 2");
		
	}
	
	private void imprimirProductoyMandarProductoEtapa2(String message)
	{
		
		System.out.println(message + ", recibido en etapa 2 por proceso 1");
		String mensaje = message + ", recibido en etapa 2 por proceso 1";
		this.buf2.insertProductNaranaja(mensaje  + " El producto sale de la etapa 2 a etapa 3");
		
	}
	
	private void imprimirProductoEstapa3yMandarProductoEtapa3(String message)
	{
		
		System.out.println(message + ", recibido en etapa 3 por proceso 1");
		this.buf3.insertProductNaranaja(message  + " El producto sale de la etapa 3 a etapa final");
		
	}
	
	@Override
	public void run() 
	{
		if (tipo==true && etapa==1)
		{
			
			for(int i = 0; i < this.cantProductos;i++)
			{
				idProductos.MasNumero();
				this.MandarProducto(idProductos.getNumero());
			}
			
		}
		
		if (etapa==2)
		
		{
			
			while(!buf.isFinishedBuffer()||this.buf.hasProducts())
			{
				
				String message = this.buf.recogerProductNaranaja();
				
				if (message == "")
				{
					return;
				}
				
				this.imprimirProductoyMandarProductoEtapa2(message);
				
			}
			
		}
		
		if (etapa==3)
			
		{
			
			while(!buf2.isFinishedBuffer()||this.buf2.hasProducts())
			{
				
				String message = this.buf2.recogerProductNaranajaEtapa2();
				
				if (message == "")
				{
					return;
				}
				
				this.imprimirProductoEstapa3yMandarProductoEtapa3(message);
			
				
			}
			
		}
		
			
	}

}