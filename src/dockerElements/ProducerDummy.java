package dockerElements;
import ssh.SshManager;
public class ProducerDummy {
	private String ProducerWarPath="/home/zakaria/dockerFiles/producer";
	private String ProducerImage="tomcat";
	private int ProducerPort=8080;
	private String id;
	private String ip;
	private String ProducerAdress;
	private String nom;
	public ProducerDummy(String nom){	
		id=SshManager.execOnDocker("docker run -d -v "+ProducerWarPath+":/usr/local/tomcat/webapps "+ProducerImage);
		ip=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+id);
		setProducerAdress("http://"+ip+":"+ProducerPort+"/Producer/services/Producer?wsdl");
		this.setNom(nom);
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
