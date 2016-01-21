package ssh;

public class UltraEsbFactory {
	private static String ultraSrc="/home/zakaria/dockerFiles/ultraesb";
	private static String ultraTarget="/opt/ultraesb-2.3.0-min/conf/deployments/default/";
	private static String ultraEsbImage="peterchapmanuk/ultraesb";
	private static String ultraConfPathTemplate="/home/zakaria/dockerFiles/ultraesb/ultra-unit.xml.template";
	private static String ultraConfPath="/home/zakaria/dockerFiles/ultraesb/ultra-unit.xml";
	private static int offset=12;
	private static int UltraEsbPort=8280;
	public static String createUltraEsb(String ProducerAdress,String ProducerName){	
		String addedFile="<u:address>"+ProducerAdress+"</u:address>";
		SshManager.execOnDocker("cp "+ultraConfPathTemplate+" "+ultraConfPath);
		SshManager.execOnDocker("sed '"+offset+" a "+addedFile+"' "+ultraConfPath+" >tmp;cat tmp>"+ultraConfPath);
		//docker run -v /home/zakaria/dockerFiles/ultraesb:/opt/ultraesb-2.3.0-min/conf/deployments/default/ -d peterchapmanuk/ultraesb /opt/ultraesb-2.3.0-min/bin/ultraesb-daemon.sh console
		String UltraEsbId=SshManager.execOnDocker("docker run -v \""+ultraSrc+":"+ultraTarget+"\" -d "+ultraEsbImage+" /opt/ultraesb-2.3.0-min/bin/ultraesb-daemon.sh console");
		String UltraEsbIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+UltraEsbId);
		//String UltraEsbAdress="http://"+UltraEsbIp+":"+UltraEsbPort;
		String UltraEsbAdress="http://"+UltraEsbIp+":"+UltraEsbPort+"/service/"+ProducerName+"?wsdl";
		return UltraEsbAdress;
	}
}
