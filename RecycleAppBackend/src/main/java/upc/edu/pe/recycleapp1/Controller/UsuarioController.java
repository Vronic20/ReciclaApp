package upc.edu.pe.recycleapp1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.recycleapp1.DTO.UsuarioDTO;
import upc.edu.pe.recycleapp1.Model.Usuario;
import upc.edu.pe.recycleapp1.Service.IUsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody @Validated Usuario usuario){
       Usuario usuarioRegistrado = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado);
    }

    @GetMapping
    public List<UsuarioDTO> buscarTodosLosUsuarios(){
        return usuarioService.findAllUsuarioDTO();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioDTO> buscarUsuarioPorId(@PathVariable(value ="id") String usuario_id){
        return usuarioService.findByIdUsuarioDTO(usuario_id);
    }

}
