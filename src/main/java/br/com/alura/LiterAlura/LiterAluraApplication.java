package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.principal.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiterAluraApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

		Principal principal = context.getBean(Principal.class);
		principal.exibirMenu();

		context.close();
	}

}
