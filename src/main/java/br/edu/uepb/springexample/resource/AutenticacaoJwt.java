package br.edu.uepb.springexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uepb.springexample.DTO.JwtResponseDTO;
import br.edu.uepb.springexample.DTO.UsuarioDTO;
import br.edu.uepb.springexample.security.JwtTokenUtil;
import br.edu.uepb.springexample.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class AutenticacaoJwt {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponseDTO> createAuthToken(@RequestBody UsuarioDTO authenticationRequest){
		final UserDetails userDetails = jwtService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new JwtResponseDTO(token));
	}
	
}
