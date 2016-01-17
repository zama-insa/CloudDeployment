package ssh;

public class IConsumerFactory {
	private static String ConsumerJarPath="/home/zakaria/dockerFiles/consumer";
	private static String ConsumerImage="tomcat";
	
	public static String createConsumer(String UltraEsbAdress){
		String ConsumerId=SshManager.execOnDocker("docker run -d -v "+ConsumerJarPath+":/jar "+ConsumerImage);
		String ConsumerIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+ConsumerId);
		String options=UltraEsbAdress;
		String result=SshManager.execOnDocker("docker exec "+ConsumerId+" java -jar /jar/Consumer.jar "+options);
		System.out.println("=============== Response from Consumer =====================");
		System.out.println(result);
		System.out.println("=============== End response =====================");
		return ConsumerIp;
	}
}
