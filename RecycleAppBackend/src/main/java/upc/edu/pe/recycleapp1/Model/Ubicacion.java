package upc.edu.pe.recycleapp1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "UBICACIONES")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Ubicacion implements Serializable {

    //Properties
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name = "UBI_ID", length = 50)
    /**IDENTIFICADOR DE LA UBICACION*/
    private String id;

    @Column(name = "UBI_CALLE", length = 50)
    /**CALLE DE LA UBICACION*/
    private String calle;

    @Column(name = "UBI_JIRON", length = 50)
    /**JIRON DE LA UBICACION*/
    private String jiron;

    @Column(name = "UBI_NUMERO", length = 10)
    /**NUMERODE LA UBICACION*/
    private BigInteger numero;

    @Column(name = "UBI_REFERENCIA", length = 50)
    /**REFERENCIA DE LA UBICACION*/
    private String referencia;

    @Column(name = "UBI_DISTRITO", length = 50)
    /**DISTRITO DE LA UBICACION*/
    private String distrito;
}
