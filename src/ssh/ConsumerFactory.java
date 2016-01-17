package ssh;

public class ConsumerFactory {
	private static String ConsumerJarPath="/home/zakaria/dockerFiles/consumer";
	private static String ConsumerImage="tomcat";
	private static int threadsNumber=100;
	
	public static String createConsumer(String activeMqAdress,String UltraEsbAdress,String consumerName){
		String ConsumerId=SshManager.execOnDocker("docker run -d -v "+ConsumerJarPath+":/jar "+ConsumerImage);
		String ConsumerIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+ConsumerId);
		String options=consumerName+" "+threadsNumber+" "+activeMqAdress+" "+UltraEsbAdress;
		SshManager.execOnDocker("docker exec "+ConsumerId+" java -jar /jar/Consumer.jar "+options);
		return ConsumerIp;
	}
}
