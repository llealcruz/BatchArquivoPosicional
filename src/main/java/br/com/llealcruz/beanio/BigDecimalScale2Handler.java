package br.com.llealcruz.beanio;

import java.math.BigDecimal;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

public class BigDecimalScale2Handler extends BigDecimalScaleBaseHandler implements TypeHandler {
	public Class<?> getType() {
		return BigDecimal.class;
	}

	@Override
	public Object parse(String text) throws TypeConversionException {
		return this.parse(text, 2);
	}

	@Override
	public String format(Object value) {
		return super.format(value, 2);
	}
}
