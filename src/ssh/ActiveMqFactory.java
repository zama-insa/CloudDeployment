package ssh;

public class ActiveMqFactory {
	private static String activeMqImage="webcenter/activemq";
	public static String createActiveMq(){
		String activeMqId=SshManager.execOnDocker("docker run -p 61616:61616 -d "+activeMqImage);
		String activeMqIp=SshManager.execOnDocker("docker inspect --format '{{ .NetworkSettings.IPAddress }}' "+activeMqId);
		int activeMqPort=61616;
		String activeMqAddress="tcp://"+activeMqIp+":"+activeMqPort;
		return activeMqAddress;
	}
}