package br.com.llealcruz.beanio;

import java.math.BigDecimal;
import java.util.Objects;

public class BigDecimalScaleBaseHandler {
    public Object parse(String text, int scale) {
        BigDecimal retorno;
        if (text == null || text.isEmpty()) {
            retorno = null;
        } else {
            if ("0".equals(text.trim())) {
                retorno = BigDecimal.ZERO;
            } else {
                retorno = new BigDecimal(
                        text.substring(0, text.length() - scale) + "." + text.substring(text.length() - scale));
            }
        }
        return retorno;
    }

    public String format(Object value, int scale) {
        if (Objects.nonNull(value)) {
            BigDecimal retorno = ((BigDecimal) value).multiply(new BigDecimal("10").pow(scale));
            return retorno.setScale(0).toString();
        } else {
            return "0";
        }
    }
}
