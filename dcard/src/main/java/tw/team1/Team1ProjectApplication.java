package tw.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team1ProjectApplication {

	public static void main(String[] args) {
//		String encodedPassword = new BCryptPasswordEncoder().encode("123456");
//		System.out.println(encodedPassword);
		SpringApplication.run(Team1ProjectApplication.class, args);
	}

}
