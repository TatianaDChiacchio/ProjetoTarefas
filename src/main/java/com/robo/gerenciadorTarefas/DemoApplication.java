package com.robo.gerenciadorTarefas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.robo.gerenciadorTarefas.Repositories.UsuarioRepository;
import com.robo.gerenciadorTarefas.entities.Usuario;
import com.robo.gerenciadorTarefas.enums.ProfileEnum;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args ->{
			initUsers(usuarioRepository, passwordEncoder);
		};
	}
	
	private void initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		Usuario admin = new Usuario();
		admin.setId("2");
		admin.setEmail("tata@gmail.com");
		admin.setPassword(passwordEncoder.encode("1234567"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);
		
		Usuario find = usuarioRepository.findByEmail("tata@gmail.com");
		if (find == null) {
			usuarioRepository.save(admin);
		}
	}

}
