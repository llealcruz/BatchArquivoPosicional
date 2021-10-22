package br.com.llealcruz.model.primeiro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

import java.io.Serializable;

@Record(name = "Registro01", maxLength = 104)
@Getter
@Setter
@NoArgsConstructor
public class Registro01 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Field(ordinal = 1, length = 8, defaultValue = "00000001")
	private String sequencial;

	@Field(ordinal = 2, length = 3, defaultValue = "T01")
	private String tipo;

	@Field(ordinal = 3, length = 14, align = Align.RIGHT, padding = '0')
	private String cnpj;

	@Field(ordinal = 4, length = 6)
	private String mesAno;

	@Field(ordinal = 5, length = 1, defaultValue = "0")
	private String tipoDado;

	@Field(ordinal = 6, length = 1, defaultValue = "1")
	private String tipoRegistro;

	@Field(ordinal = 7, length = 2, defaultValue = "ES")
	private String uf;

	@Field(ordinal = 8, length = 60, align = Align.LEFT, padding = ' ')
	private String nomePessoa;

	@Field(ordinal = 9, length = 11, defaultValue = "PrimeiroJob")
	private String nomeArquivo;

	@Field(ordinal = 10, length = 4)
	private String branco;

}
