package upc.edu.pe.recycleapp1.Model;

import javax.persistence.*;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "USUARIOS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario implements Serializable {

    //Properties
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name = "USU_ID", length = 50)
    /**IDENTIFICADOR DEL USUARIO*/
    private String id;

    @Column(name = "USU_USUARIO", nullable = false, length = 25)
    /**USUARIO REGISTRADO*/
    private String usuario;

    @Column(name = "USU_CONTRASEÑA", nullable = false, length = 25)
    /**CONTRASEÑA DEL USUARIO*/
    private String contraseña;

    @Column(name = "USU_CORREO", nullable = false, length = 25)
    /**CORREO DEL USUARIO*/
    private String correo;

    @Column(name = "USU_ESTADO", nullable = false, length = 40)
    /**ESTADO DEL USUARIO*/
    private String estado;
}
