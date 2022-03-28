package upc.edu.pe.recycleapp1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALARMAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Alarma implements Serializable {

    //Properties
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name = "ALAR_ID", length = 50)
    /**IDENTIFICADOR DE LA ALARMA*/
    private String id;

    @Column(name = "ALAR_HORARIO", nullable = false)
    /**HORARIO DE LA ALARMA*/
    private LocalDateTime Horario;

}
