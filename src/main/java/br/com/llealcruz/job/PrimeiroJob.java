package br.com.llealcruz.job;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.com.llealcruz.base.BaseJob;
import br.com.llealcruz.service.PrimeiroJobService;
import br.com.llealcruz.exception.BusinessException;
import br.com.llealcruz.util.FileUtils;
import br.com.llealcruz.util.MessageUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrimeiroJob extends BaseJob {

	@Autowired
	private PrimeiroJobService primeiroJobService;

	@Value("${file-patterns.gerar-arquivo-primeirojob}")
	private String nomeArquivoPrimeiroJob;

	@Override
	public void run(String... args) {
		try {
			if (args.length < 1) {
				throw new BusinessException("Error na quantidade de argumentos.");
			}

			this.gerarArquivoPrimeiroJob();

		} catch (BusinessException e) {
			log.info("Ocorreu um erro ao processar", e);
			throw new BusinessException("Erro no processamento.", e);
		}
	}

	private void gerarArquivoPrimeiroJob() {
		try {
			log.info(this.nomeArquivoPrimeiroJob);
			System.out.println("ini " + LocalDateTime.now());
			String nmArquivo = this.primeiroJobService.gerarArquivoPrimeiroJob(this.nomeArquivoPrimeiroJob);
			this.moverArquivoJobUm(getPathFtpTemp(), getPathFtpRo(), nmArquivo);
			System.out.println("fim " + LocalDateTime.now());
		} catch (BusinessException ex) {
			throw ex;
		}
	}

	private void moverArquivoJobUm(String localArquivo, String localFinalArquivo, String nomeArquivo) {
		File arquivo = new File(localArquivo.concat(nomeArquivo));
		File diretorio = new File(localFinalArquivo);
		Boolean sucessoMover = arquivo.renameTo(new File(diretorio, arquivo.getName()));

		if (sucessoMover) {
			log.info("Arquivo {} movido para {}", nomeArquivo, localFinalArquivo);
		} else {
			log.info("Erro ao mover arquivo {} para {}", nomeArquivo, localFinalArquivo);
		}
	}

}
