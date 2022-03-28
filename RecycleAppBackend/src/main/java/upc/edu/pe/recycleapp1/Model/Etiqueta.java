package upc.edu.pe.recycleapp1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ETIQUETAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Etiqueta implements Serializable {

    //Properties
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name = "ETIQ_ID", length = 50)
    /**IDENTIFICADOR DE LA ETIQUETA*/
    private String id;

    @Column(name = "ETIQ_ETIQUETA", length = 50)
    /**NOMBRE DE LA ETIQUETA*/
    private String etiqueta;

    @Column(name = "ETIQ_TIPRESIDUO", length = 50)
    /**TIPO DE RESIDUO*/
    private String tipo_residuo;
}
