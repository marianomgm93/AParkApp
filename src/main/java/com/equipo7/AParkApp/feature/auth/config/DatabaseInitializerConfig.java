package com.equipo7.AParkApp.feature.auth.config;

import com.equipo7.AParkApp.feature.auth.credentials.CredentialsEntity;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsRepository;
import com.equipo7.AParkApp.feature.auth.permissions.*;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class DatabaseInitializerConfig {

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
            PermitRepository permitRepository,     // Asumiendo que tenés estos repositorios
            RoleRepository roleRepository,
            CredentialsRepository credentialsRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder          // Inyectamos tu PasswordEncoder real
    ) {
        return args -> {
            // 1. Evitar duplicados si el ddl-auto no está en create-drop
            if (permitRepository.count() > 0) return;

            System.out.println(">> Cargando datos de prueba en la base de datos...");

            // 2. Crear y guardar Permisos
            PermitEntity crearCuenta = permitRepository.save(PermitEntity.builder().permit(Permits.ACTUALIZAR_CUENTA).build());
            PermitEntity verUsuarios = permitRepository.save(PermitEntity.builder().permit(Permits.ELIMINAR_USUARIO).build());

            // 3. Crear y guardar Roles asignando los permisos correspondientes
            RoleEntity roleUser = new RoleEntity(Roles.ROLE_CLIENT);
            roleUser.getPermits().add(crearCuenta);
            roleRepository.save(roleUser);

            RoleEntity roleAdmin = new RoleEntity(Roles.ROLE_ADMIN);
            roleAdmin.getPermits().add(crearCuenta);
            roleAdmin.getPermits().add(verUsuarios);
            roleRepository.save(roleAdmin);

            // 4. Crear los 10 usuarios de prueba en un bucle
            String passwordPlano = "password123";
            String passwordEncriptada = passwordEncoder.encode(passwordPlano); // <-- ACÁ SUCEDE LA MAGIA

            // Ejemplo: Crear Admin
            UserEntity adminUser = UserEntity.builder().email("admin@example.com").name("mariano").active(true).build();
            userRepository.save(adminUser);

            CredentialsEntity adminCreds = new CredentialsEntity();
            adminCreds.setUsername("admin");
            adminCreds.setPassword(passwordEncriptada); // Guardamos el hash generado en vivo
            adminCreds.setEnabled(true);
            adminCreds.setUsuario(adminUser);
            adminCreds.getRoles().add(roleAdmin);
            credentialsRepository.save(adminCreds);
/*
            // Ejemplo: Crear los 9 usuarios restantes con un for-loop dinámico
            for (int i = 1; i <= 9; i++) {
                UserEntity user = new UserEntity();
                user.setName("user"+i);
                user.setEmail("user" + i + "@example.com");
                userRepository.save(user);

                CredentialsEntity creds = new CredentialsEntity();
                creds.setUsername("user" + i);
                creds.setPassword(passwordEncriptada);
                // El usuario 9 lo creamos deshabilitado para mostrar la excepción en clase
                creds.setEnabled(i != 9);
                creds.setUsuario(user);
                creds.getRoles().add(roleUser);
                credentialsRepository.save(creds);
            }
*/
            System.out.println(">> ¡Datos de prueba cargados exitosamente usando el PasswordEncoder");
        };
    }
}
