package esprit.tn.ms_prospect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsProspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProspectApplication.class, args);
	}

}
