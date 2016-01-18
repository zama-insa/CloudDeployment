package dockerElements;

import ssh.SshManager;

public class UltraEsbDummy {
	private static String ultraConfPathTemplate="/home/zakaria/dockerFiles/ultraesb/ultra-unit.xml.template2";
	private static String ultraConfPath="/home/zakaria/dockerFiles/ultraesb/ultra-unit.xml";
	private static int offset=12;
	private static int UltraEsbPort=8280;
	private static String id="e1efe655ef88";
	private static String ip="172.17.0.3";
	public static void addProducer(ProducerDummy producerdummy){
		String producerAddress=producerdummy.getProducerAdress();
		String s="<u:proxy id=\""+producerdummy.getNom()+"\">";
		s+="<u:transport id=\"http-8280\">";
		s+="</u:transport>";
	  	s+="<u:target>";
		s+="<u:inDestination>";
		s+="<u:address>"+producerAddress+"</u:address>";
		s+="</u:inDestination>";
	    s+="<u:outDestination>";
	    s+="<u:address type=\"response\"/>";
	    s+="</u:outDestination>";
	  	s+="</u:target>";
		s+="</u:proxy>";
		SshManager.execOnDocker("cp "+ultraConfPathTemplate+" "+ultraConfPath);
		SshManager.execOnDocker("sed '"+offset+" a "+s+"' "+ultraConfPath+" >tmp;cat tmp>"+ultraConfPath);
	}
	public static String addressOfProducer(String producerName){
		return "http://"+ip+":"+UltraEsbPort+"/service/"+producerName+"?wsdl";
	}
}
