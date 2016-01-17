package ssh;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileModifier {
	public static void modifyLine(int lineNumber,String line,String ultraConfPath){
		File file=new File(ultraConfPath);
		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			lines.set(lineNumber,line);
			Files.write(file.toPath(),lines,StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void addLine(int lineNumber,String line,String ultraConfPath){
		File file=new File(ultraConfPath);
		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			lines.add(lineNumber,line);
			Files.write(file.toPath(),lines,StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/*public static void main(String[] args) {
		String ultraConfPath="/home/aziouiz/testDockerMigration/ultraConf/ultra-unit.xml";
		addLine(12,"haha",ultraConfPath);
		addLine(12,"hehe",ultraConfPath);
	}*/
}
