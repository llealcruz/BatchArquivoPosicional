package br.com.llealcruz.base;

import br.com.llealcruz.exception.BusinessException;
import br.com.llealcruz.util.FileUtils;
import br.com.llealcruz.util.MessageUtils;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public abstract class BaseJob extends BaseBatch {
    public abstract void run(String... args);

    protected void handleFileException(File file, Exception ex) {
        try {
            log.error(MessageUtils.ERRO_PROCESSAR_ARQUIVO_PAR1, file.getAbsolutePath());
            log.error(ex.getMessage());
            FileUtils.moveAndReplaceFile(file.getAbsolutePath(), getPathFtpErro() + file.getName());
        } catch (IOException e) {
            log.error(MessageUtils.ERRO_AO_TENTAR_MOVER_ARQUIVO_PAR1, file.getAbsolutePath());
            throw new BusinessException("Erro ao mover o arquivo", e);
        }
    }
}
