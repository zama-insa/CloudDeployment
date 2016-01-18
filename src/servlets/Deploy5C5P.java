package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dockerElements.ConsumerDummy;
import dockerElements.ProducerDummy;
import dockerElements.UltraEsbDummy;

@WebServlet("/Deploy5C5P")
public class Deploy5C5P extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Deploy5C5P() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*TO DO TO DEPLOY 5 CONSUMERS AND 5 PRODUCERS
		 * 1-activemq is already on the cloud, it has a binded port to docker server port 6161, it means that you have to set ip : 87.98.158.131:6161 (in Vodich)
		 * 2-ultraesb is already on the cloud in the same private network as consumers and producers (when they will be created) in ip : 172.17.0.3:8280 
		 * 3-you should change the path of the static string dockerConfPath (SshManager class)to the path of a file in your pc , which contains 3 lines(host,user,pass) of docker server
		 * 4-send war file of producer to docker server to path ProducerWarPath (ProducerDummy class) with this command : scp /the/Producer/WarPath zakaria@ip-docker-server:ProducerWarPath
		 * 5-change Consumers' activemq adress to : 172.17.0.2:61616
		 * 6-change Consumers' ultraesb adress to : 172.17.0.3:8280
		 * 7-change Consumers' name to i (i from 1 to 5)
		 * 8-send 5 jar files (in a folder, and they will be named Consumer1.jar,....,Consumer5.jar) of consumers to docker server to path ConsumerJarPath (ConsumerDummy class) with this command : scp -r /folder/Of/Jars zakaria@ip-docker-server:ConsumerJarPath
		 * 9-execute this servlet
		 * 10-execute scenarii
		 * 11-execute Delete5C5P servlet 
		 */
		
		List<String> ids=new ArrayList<String>();
		
		ProducerDummy[] producerdummies=new ProducerDummy[5];
		for (int i = 0; i < producerdummies.length;i++){
			producerdummies[i]=new ProducerDummy(i+"");
			UltraEsbDummy.addProducer(producerdummies[i]);
			ids.add(producerdummies[i].getId());
		}
			
		ConsumerDummy[] consumerdummies=new ConsumerDummy[5];
		for(int i=0;i<consumerdummies.length;i++){
			consumerdummies[i]=new ConsumerDummy("1");
			ids.add(consumerdummies[i].getId());
		}
		
		request.getSession(true).setAttribute("ids",ids);
	}

	/**6f0443bde784
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
