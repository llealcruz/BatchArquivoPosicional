package br.com.llealcruz.base;

import org.springframework.beans.factory.annotation.Value;

public class BaseBatch {
	public static final String SISTEMA_BATCH = "Sistema Batch - Luã Leal Cruz";

	@Value("${llealcruz-dir.rx}")
	private String pathFtpRx;

	@Value("${llealcruz-dir.ro}")
	private String pathFtpRo;

	@Value("${llealcruz-dir.erro}")
	private String pathFtpErro;

	@Value("${llealcruz-dir.temp}")
	private String pathFtpTemp;

	@Value("${llealcruz-dir.tx}")
	private String pathFtpTx;

	public String getPathFtpRx() {
		return pathFtpRx.concat("\\");
	}

	public String getPathFtpRo() {
		return pathFtpRo.concat("\\");
	}

	public String getPathFtpErro() {
		return pathFtpErro.concat("\\");
	}

	public String getPathFtpTemp() {
		return pathFtpTemp.concat("\\");
	}

	public String getPathFtpTx() {
		return pathFtpTx.concat("\\");
	}
}
