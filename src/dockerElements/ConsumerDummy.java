package dockerElements;
import ssh.SshManager;
public class ConsumerDummy {
	private String ConsumerJarPath="/home/zakaria/testVodich/consumer";
	private String ConsumerImage="lwieske/java-8";
	private int threadsNumber=100;
	private String ip;
	private String id;
	
	//docker run --name tester -d -v $PWD:/jar lwieske/java-8 java -jar /jar/test.jar
	public ConsumerDummy(int consumerNumber){
		String options=consumerNumber+" "+threadsNumber;
		id=SshManager.execOnDocker("docker run --name ConsumerDummy"+consumerNumber+" -d -v "+ConsumerJarPath+":/jar "+ConsumerImage+" java -jar /jar/Consumer"+consumerNumber+".jar "+options);
		setIp(SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+id));
		
		//SshManager.execOnDocker("docker exec "+id+" java -jar /jar/Consumer"+consumerNumber+".jar "+options);
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
