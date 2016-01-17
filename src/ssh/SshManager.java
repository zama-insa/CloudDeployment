
package ssh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SshManager{
	public static String dockerConfPath="/home/aziouiz/testCloud/docker.conf";
	private static Map<String,dockerServer> connectedServers=new HashMap<String,dockerServer>();
	
	public static String execOnDocker(String command){
		if(connectedServers.get(dockerConfPath)==null){
			String host=null,passwd=null,user=null;
			try{
				//TO BE CHECKED
				BufferedReader bfr=new BufferedReader(new FileReader(dockerConfPath));

				String line=bfr.readLine();
				StringTokenizer st=new StringTokenizer(line,"=");
				st.nextToken();
				host=st.nextToken();

				line=bfr.readLine();
				st=new StringTokenizer(line,"=");
				st.nextToken();
				user=st.nextToken();

				line=bfr.readLine();
				st=new StringTokenizer(line,"=");
				st.nextToken();
				passwd=st.nextToken();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			dockerServer dockerserver=new dockerServer(host, user, passwd,dockerConfPath);
			connectedServers.put(dockerConfPath,dockerserver);
		}
		dockerServer dockerserver=connectedServers.get(dockerConfPath);
		//verbosity?
		//System.out.println("command is :"+command);
		return dockerserver.sendCommand(command);
	}
	
	public static void disconnectServer(){
		if(connectedServers.get(dockerConfPath)!=null){
			connectedServers.get(dockerConfPath).closeConnection();
			connectedServers.remove(dockerConfPath);
		}
	}
}