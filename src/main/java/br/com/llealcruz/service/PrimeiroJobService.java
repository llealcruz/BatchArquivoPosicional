package br.com.llealcruz.service;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.springframework.stereotype.Service;

import br.com.llealcruz.base.BaseBatch;
import br.com.llealcruz.beanio.BigDecimalScale2Handler;
import br.com.llealcruz.model.primeiro.ArquivoJobUmRoot;
import br.com.llealcruz.model.primeiro.Registro01;
import br.com.llealcruz.util.DateUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PrimeiroJobService extends BaseBatch {

	public <T> void gravarArquivo(T obj, String streamName, String path) {
		log.info("Gravando arquivo {}", streamName);
		StreamFactory factory = StreamFactory.newInstance();
		StreamBuilder streamBuilder = new StreamBuilder(streamName).format("fixedlength")
				.addTypeHandler(BigDecimalScale2Handler.class.getName(), new BigDecimalScale2Handler())
				.parser(new FixedLengthParserBuilder()).addGroup(obj.getClass());
		factory.define(streamBuilder);
		try (BeanWriter out = factory.createWriter(streamName, new File(path))) {
			out.write(obj);
			log.info("Arquivo gravado com sucesso em {}", path);
		}
	}

	public String gerarArquivoPrimeiroJob(String nomeArquivo) {
		// Definir nome e diretorio do arquivo
		String pathDestinoTemp = getPathFtpTemp();
		String nmArquivoFinal = this.getNmArquivo(nomeArquivo);
		String caminhoArquivoDestinoTemp = pathDestinoTemp.concat(nmArquivoFinal);

		this.gravarDadosGerarArquivo(caminhoArquivoDestinoTemp);
		log.info("Arquivo PrimeiroJob gerado com sucesso.");

		return nmArquivoFinal;
	}

	private String getNmArquivo(String nmArquivo) {
		LocalDate dtReferencia = LocalDate.now();

		int qtArquivosDia = 1;

		return DateUtils
				.getDataValueString(dtReferencia).concat(".").concat(StringUtils
						.leftPad(Long.toString(Objects.nonNull(qtArquivosDia) ? qtArquivosDia + 1L : 1L), 6, "0"))
				.concat(".").concat(nmArquivo);
	}

	private void gravarDadosGerarArquivo(String caminhoArquivoDestinoTemp) {
		ArquivoJobUmRoot arquivoJobUmRoot = new ArquivoJobUmRoot();

		// Registro 01
		this.definirRegistro01(arquivoJobUmRoot);

		// Registro 02
		/* this.definirRegistro02(); */
		// Registro 03
		/* this.definirRegistro03(); */
		// Registro 04
		/* this.definirRegistro04(); */

		gravarArquivo(arquivoJobUmRoot, "PrimeiroJob", caminhoArquivoDestinoTemp);

	}

	private void definirRegistro01(ArquivoJobUmRoot arquivoJobUmRoot) {
		Registro01 registro01 = new Registro01();

		registro01.setCnpj("17080737796");
		registro01.setNomePessoa("Luã Leal Cruz");
		registro01.setMesAno("122020");

		arquivoJobUmRoot.setRegistro01(registro01);
	}

}
