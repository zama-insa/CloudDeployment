package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ssh.ConsumerFactory;
import ssh.IConsumerFactory;
import ssh.ProducerFactory;
import ssh.SshManager;
import ssh.ActiveMqFactory;
import ssh.UltraEsbFactory;

/**
 * Servlet implementation class init
 */
@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Init() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("******Launching Producer ******");
		String ProducerAdress=ProducerFactory.createProducer();
		System.out.println("******Producer adress ******"+ProducerAdress);
		
		System.out.println("******Launching UltraEsb ******");
		String UltraEsbAdress=UltraEsbFactory.createUltraEsb(ProducerAdress,"1");
		System.out.println("******UltraEsb adress ******"+UltraEsbAdress);
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("******Launching Consumer ******");
		String ConsumerIp=IConsumerFactory.createConsumer(UltraEsbAdress);
		System.out.println("******Consumer Ip ******"+ConsumerIp);
		
		System.out.println("******Launching ActiveMq ******");
		String ActiveMqAddress=ActiveMqFactory.createActiveMq();
		System.out.println("******ActiveMq Address ******"+ActiveMqAddress);
		
		//to do 
		/*System.out.println("******Launching Consumer ******");
		String ConsumerIp=ConsumerFactory.createConsumer(ActiveMqAddres,UltraEsbAdress,"1");
		System.out.println("******Consumer Ip ******"+ConsumerIp);*/
	
		
		System.out.println("deleting containers ...");
		System.out.println(SshManager.execOnDocker("docker ps -aq|xargs docker rm -f"));
		
		
	}

	/**6f0443bde784
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
