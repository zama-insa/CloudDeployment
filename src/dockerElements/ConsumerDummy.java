package dockerElements;
import ssh.SshManager;
public class ConsumerDummy {
	private String ConsumerJarPath="/home/zakaria/dockerFiles/consumer";
	private String ConsumerImage="tomcat";
	private int threadsNumber=100;
	private String ip;
	private String id;
	public ConsumerDummy(String consumerName){
		id=SshManager.execOnDocker("docker run -d -v "+ConsumerJarPath+":/jar "+ConsumerImage);
		setIp(SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+id));
		String options=consumerName+" "+threadsNumber;
		SshManager.execOnDocker("docker exec "+id+" java -jar /jar/Consumer"+consumerName+".jar "+options);
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
}
