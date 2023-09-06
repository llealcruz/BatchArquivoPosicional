package br.com.llealcruz.model.primeiro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Group
public class ArquivoJobUmRoot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Record(name = "registro01", maxLength = 104, minOccurs = 1, maxOccurs = 1)
    private Registro01 registro01;

    /*
     * @Record(name = "registro02", maxLength = 104, minOccurs = 1, maxOccurs = 1)
     * private Registro02 registro02;
     *
     * @Record(name = "registro03", maxLength = 104, minOccurs = 1, maxOccurs = 1)
     * private Registro03 registro03;
     *
     * @Record(name = "registros04", maxLength = 104, collection = List.class)
     * private List<Registro04> registros04 = new ArrayList<>();
     */

}
