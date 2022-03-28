package upc.edu.pe.recycleapp1.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.pe.recycleapp1.Controller.UsuarioController;
import upc.edu.pe.recycleapp1.DTO.UsuarioDTO;
import upc.edu.pe.recycleapp1.Model.Usuario;
import upc.edu.pe.recycleapp1.Repository.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioController.class);

    @Override
    public Usuario create(Usuario usuario) {
        log.info("Creando el Usuario");
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAllUsuarioDTO() {
        log.info("Buscando todos los usuarios");
        return usuarioRepository.findAllUsuarioDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByIdUsuarioDTO(String id) {
        log.info("Buscando el Usuario con el id {}",id);
        return usuarioRepository.findByIdUsuarioDTO(id);
    }

    @Override
    public void deleteById(String id) {
        log.info("Eliminando el Usuario");
        usuarioRepository.deleteById(id);
    }
}
