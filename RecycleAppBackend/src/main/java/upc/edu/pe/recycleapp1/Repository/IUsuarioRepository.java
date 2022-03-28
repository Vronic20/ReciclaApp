package upc.edu.pe.recycleapp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.recycleapp1.DTO.UsuarioDTO;
import upc.edu.pe.recycleapp1.Model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,String> {


    @Query(value = "SELECT us.id AS idUsuario,us.contraseña AS contraseña, us.usuario AS usuario," +
            "us.correo AS correo, us.estado AS estado FROM Usuario us group by us")
    List<UsuarioDTO> findAllUsuarioDTO();


    @Query(value = "SELECT us.id AS idUsuario,us.contraseña AS contraseña," +
            "us.correo AS correo, us.estado AS estado FROM Usuario us" +
            " where idUsuario = :id")
    Optional<UsuarioDTO> findByIdUsuarioDTO(@Param("id") String id);
}
