package kotojava.docker.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        try {
            String hostname = java.net.InetAddress.getLocalHost().getHostName();
            String ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
            String osName = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");
            String javaVersion = System.getProperty("java.version");
            String systemInfo = "Hostname: " + hostname + ", IP Address: " + ipAddress +
                    ", OS: " + osName + " " + osVersion + ", Java Version: " + javaVersion;
            System.out.println("System Information: " + systemInfo);
            return systemInfo;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
