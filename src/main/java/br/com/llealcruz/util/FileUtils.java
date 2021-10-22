package br.com.llealcruz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import br.com.llealcruz.exception.BusinessException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileUtils {

	private static final String PONTO = ".";
	private static final int ZERO = 0;
	public static final int UM = 1;
	public static final int CINQUENTA_E_NOVE = 59;
	public static final int SESSENTA_E_DOIS = 62;
	private static final String DATE_PATTERN = "yyyyMMdd";

	private FileUtils() {
		throw new BusinessException("Classe utilitaria");
	}

	public static File[] findFile(String path, String pattern) {

		log.info("Buscando diretorio {} ", path);
		File diretorio = new File(FilenameUtils.getFullPath(path));
		if (!diretorio.exists()) {
			throw new BusinessException(String.format(MessageUtils.CAMINHO_NAO_ENCONTRADO_PAR1, path));
		}
		log.info("Buscando arquivos com o padrao {} no diretorio {} ", pattern, path);
		File[] arquivos = diretorio.listFiles(new FilenameFilterUtils(pattern));
		log.debug(MessageUtils.ARQUIVOS_ENCONTRADOS_PAR1, arquivos.length);
		return arquivos;
	}

	public static File[] findFileWithException(String path, String fileName) {
		File[] arquivos = findFile(path, fileName);

		if (arquivos.length == 0) {
			log.error("Arquivo {} não localizado.", fileName);
			throw new BusinessException("Nenhum arquivo foi localizado na pasta");
		}

		return arquivos;
	}

	public static File getNewFile(String fullPath, String fileName) {
		log.info("Instanciando um novo arquivo {} no diretorio {}", fileName, fullPath);
		return new File(FilenameUtils.getFullPath(fullPath), FilenameUtils.getName(fileName));
	}

	public static File createFile(String fullPath, String fileName) throws IOException {
		Path dir = Files.createTempDirectory(fullPath);
		Path newFilePath = Files.createFile(dir.resolve(fileName));
		return newFilePath.toFile();
	}

	public static void moveAndReplaceFile(String source, String target) throws IOException {
		log.info("Iniciando a movimentacao do arquivo");
		log.info("Movendo o arquivo {} para o diretorio {}.", source, target);
		log.warn("O arquivo no diretorio destino sera sobrescrito");
		Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
		log.info("Arquivo {} movido para o diretorio {}", source, target);
	}

	public static void copyAndReplaceFile(String source, String target) throws IOException {
		log.info("Iniciando a copia do arquivo");
		log.info("Copiando o arquivo {} para o diretorio {}.", source, target);
		log.warn("O arquivo no diretorio destino sera sobrescrito");
		Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
		log.info("Arquivo {} copiado para o diretorio {}", source, target);
	}

	public static List<String> readAllLines(String path) throws IOException {
		log.info("Lendo todas as linhas do arquivo {}", path);
		return Files.readAllLines(Paths.get(path));
	}

	public static String montaNomeArquivo(String caixa, LocalDate dataReferencia, String sequencia,
			String nomeArquivo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		int tamanho = nomeArquivo.indexOf('.');
		if (caixa.length() > 5) {
			caixa = caixa.substring(0, 5);
		}
		if (sequencia.length() > 6) {
			sequencia = sequencia.substring(0, 6);
		}
		if (tamanho > 10) {
			nomeArquivo = nomeArquivo.substring(0, 10) + nomeArquivo.substring(tamanho, tamanho + 4);
		}
		return caixa + PONTO + dataReferencia.format(formatter) + PONTO + sequencia + PONTO + nomeArquivo;

	}

	public static Integer checkTypeRecord(String linha) {
		return Integer.valueOf(linha.substring(ZERO, UM).trim());
	}

	public static String getFileName(LocalDate dataReferencia, String namePath) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		return dataReferencia.format(formatter) + "_" + namePath;
	}

	public static void handleFileException(File file, String target) {

		try {
			log.error(MessageUtils.ERRO_PROCESSAR_ARQUIVO_PAR1, file.getAbsolutePath());
			FileUtils.moveAndReplaceFile(file.getAbsolutePath(), target);
		} catch (IOException e) {
			log.error(MessageUtils.ERRO_AO_TENTAR_MOVER_ARQUIVO_PAR1, file.getAbsolutePath());
			throw new BusinessException("Erro ao mover o arquivo", e);
		}
	}

	public static String formatarDataArchivePattern(LocalDate dataReferencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		return dataReferencia.format(formatter);
	}

}
