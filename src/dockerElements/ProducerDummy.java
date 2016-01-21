package dockerElements;
import ssh.SshManager;
public class ProducerDummy {
	private String ProducerWarPath="/home/zakaria/testVodich/producer/newp";
	private String ProducerImage="tomcat:8.0.30-jre8";
	private int ProducerPort=8080;
	private String id;
	private String ip;
	private String ProducerAdress;
	private int number;
	public ProducerDummy(int number){	
		id=SshManager.execOnDocker("docker run --name ProducerDummy"+number+" -d -v "+ProducerWarPath+":/usr/local/tomcat/webapps "+ProducerImage);
		ip=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+id);
		setProducerAdress("http://"+ip+":"+ProducerPort+"/Producer/services/Producer?wsdl");
		this.setNumber(number);
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProducerAdress() {
		return ProducerAdress;
	}
	public void setProducerAdress(String producerAdress) {
		ProducerAdress = producerAdress;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
