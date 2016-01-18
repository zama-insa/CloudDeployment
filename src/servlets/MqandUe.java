package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ssh.SshManager;


/**
 * Servlet implementation class init
 */
@WebServlet("/MqandUe")
public class MqandUe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MqandUe() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String activeMqId=SshManager.execOnDocker("docker run -p 6161:61616 -d webcenter/activemq");
		String activeMqIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+activeMqId);
		int activeMqPort=61616;
		String activeMqAddress="tcp://"+activeMqIp+":"+activeMqPort;
		System.out.println("activemqip="+activeMqIp);
		
		
		String UltraEsbId=SshManager.execOnDocker("docker run -d peterchapmanuk/ultraesb /opt/ultraesb-2.3.0-min/bin/ultraesb-daemon.sh console");
		String UltraEsbIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+UltraEsbId);
		//String UltraEsbAdress="http://"+UltraEsbIp+":"+UltraEsbPort;
		String UltraEsbAdress="http://"+UltraEsbIp+":"+8280+"/service/"+1+"?wsdl";
		System.out.println("ultraesbip="+UltraEsbIp);
	}

	/**6f0443bde784
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
