package servlets;

import ssh.SshManager;
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

@WebServlet("/Delete5C5P")
public class Delete5C5P extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Delete5C5P() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String ProducerAdress=ProducerFactory.createProducer();
		List<String> ids=(List<String>)request.getSession(true).getAttribute("ids");
		for(String id:ids)
			SshManager.execOnDocker("docker rm -f "+id);
		
	}

	/**6f0443bde784
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
