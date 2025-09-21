package VFS.GLOBAL.VFS_GLOBAL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VfsGlobalApplication {
	public static void main(String[] args) {
		SpringApplication.run(VfsGlobalApplication.class, args);
		System.err.println("Server started...");
	}

}
