package ssh;

public class ProducerFactory {
	private static String ProducerWarPath="/home/zakaria/dockerFiles/producer";
	private static String ProducerImage="tomcat";
	private static int ProducerPort=8080;
	public static String createProducer(){	
		String ProducerId=SshManager.execOnDocker("docker run -d -v "+ProducerWarPath+":/usr/local/tomcat/webapps "+ProducerImage);
		String ProducerIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+ProducerId);
		String ProducerAdress="http://"+ProducerIp+":"+ProducerPort+"/Producer/services/Producer?wsdl";
		return ProducerAdress;
	}
}
