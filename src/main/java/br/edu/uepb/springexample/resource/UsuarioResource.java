package br.edu.uepb.springexample.resource;

import br.edu.uepb.springexample.DTO.UsuarioDTO;
import br.edu.uepb.springexample.model.Usuario;
import br.edu.uepb.springexample.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin
public class UsuarioResource {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Usuario> createUser(@RequestBody UsuarioDTO user) {
        try {
        	Usuario usuario = new Usuario();
        	usuario.setUsername(user.getUsername());
        	usuario.setPassword(user.getPassword());
            return ResponseEntity.ok(
                usuarioRepository.save(usuario)
                );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
