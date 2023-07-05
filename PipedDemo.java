package pipeddemo;

import java.io.*;


class Producer extends Thread
{
	OutputStream os;
	
	public Producer(OutputStream os)
	{
		this.os = os;
	}
	
	public void run() {
		int count =1;
		while(true)
		{
			try {
				System.out.println("Producer " + count);
				System.out.flush();
				os.write(count++);
				os.flush();
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println("Producer " + e);
			}
		}
	}
}


class Consumer extends Thread
{
	
	InputStream is;
	
	public Consumer(InputStream is)
	{
		this.is = is;
	}
	
	public void run()
	{
		int x;
		while(true)
		{
			try 
			{
				
				x = is.read();
				System.out.println("Consumer " + x);
				System.out.flush();
				Thread.sleep(1000);
				
			}
			catch(Exception e)
			{
				System.out.println("Consumer " + e);
				e.printStackTrace();
			}
		}
		
	}
}

public class PipedDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(
				PipedInputStream pis = new PipedInputStream();
				PipedOutputStream pos = new PipedOutputStream();
		)
		{
				pos.connect(pis);
				//pis.connect(pos);
				
				Producer p = new Producer(pos);
				Consumer c = new Consumer(pis);
				
				p.start();
				c.start();
				
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
