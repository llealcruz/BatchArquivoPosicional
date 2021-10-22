package br.com.llealcruz.enumeration;

import java.util.Arrays;
import java.util.Optional;

import br.com.llealcruz.exception.BusinessException;
import br.com.llealcruz.job.*;
import lombok.Getter;

@Getter
public enum ProcessEnum {
	PROCESSAR_ARQUIVO_A1("01", PrimeiroJob.class);
//	PROCESSAR_ARQUIVO_B2("02", SegundoJob.class),
//	PROCESSAR_ARQUIVO_C3("03", TerceiroJob.class);

	private String codigo;

	private Class classe;

	private ProcessEnum(String codigo, Class classe) {
		this.codigo = codigo;
		this.classe = classe;
	}

	public static ProcessEnum buscarProcessoPorCodigo(String codigo) {

		final Optional<ProcessEnum> process = Arrays.asList(ProcessEnum.values()).stream()
				.filter(p -> p.getCodigo().equals(codigo)).findFirst();

		if (!process.isPresent()) {
			throw new BusinessException("Processo Inexistente");
		}

		return process.get();
	}
}
