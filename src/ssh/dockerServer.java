package ssh;

import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class dockerServer {
	private String dockerServerName;
	private Session session;
	private String host;
	private String passwd;
	
	public dockerServer(String host,String user,String passwd,String dockerServerName){
		try{
			JSch jSch = new JSch();
			session = jSch.getSession(user, host);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(passwd);
			session.connect();
			this.dockerServerName=dockerServerName;
			this.host=host;
			this.passwd=passwd;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void closeConnection(){
		session.disconnect();
	}
	public String sendCommand(String command){
		try{
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			InputStream commandOutput = channel.getInputStream();
			channel.connect();
			int readByte = commandOutput.read();
			StringBuilder outputBuffer = new StringBuilder();
			int nbreRetourLigne=0;
			while(readByte != 0xffffffff)
			{
				if((char)readByte=='\n') nbreRetourLigne++;
				outputBuffer.append((char)readByte);
				readByte = commandOutput.read();
			}
			channel.disconnect();
			
			String s=outputBuffer.toString();
			if(nbreRetourLigne==1)
				s=s.replace("\n","");
			return s;
		}
		catch(Exception e){
			e.printStackTrace();
			return "error on execution of command="+command+"\t on server="+dockerServerName;
		}
	}
}
