package br.com.llealcruz;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import br.com.llealcruz.base.BaseJob;
import br.com.llealcruz.enumeration.ProcessEnum;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Log4j2
public class BatchApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (args.length == 0) {
			log.error("Parametros nao informados");
		}

		final BaseJob processo = getInstace(args);
		processo.run(args);

		log.info("Processamento executado com sucesso");
	}

	public BaseJob getInstace(String... args) {

		final ProcessEnum tipoProcesso = ProcessEnum.buscarProcessoPorCodigo(args[0]);

		log.info("JOB: {} - {}", tipoProcesso.getCodigo(), tipoProcesso.getClasse().getSimpleName());

		return (BaseJob) appContext.getBean(tipoProcesso.getClasse().getSimpleName().substring(0, 1)
				.toLowerCase(new Locale("pt", "BR")).concat(tipoProcesso.getClasse().getSimpleName().substring(1)));

	}

}
