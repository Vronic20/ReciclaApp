package upc.edu.pe.recycleapp1.Service;

import upc.edu.pe.recycleapp1.DTO.UsuarioDTO;
import upc.edu.pe.recycleapp1.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Usuario create(Usuario usuario);

    public List<UsuarioDTO> findAllUsuarioDTO();

    public Optional<UsuarioDTO> findByIdUsuarioDTO(String id);

    public void deleteById(String id);

}
