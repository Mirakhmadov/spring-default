package example.server.component;

import example.server.entity.User;
import example.server.entity.enums.RoleName;
import example.server.repository.UserRepository;
import example.server.repository.mainReps.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initMode;

    private final
    PasswordEncoder passwordEncoder;
    private final
    UserRepository userRepository;
    private final
    RoleRepository roleRepository;


    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args){
        if (initMode.equals("always")) {

            User user = new User();
            user.setEmail("developer@mail.com");
            user.setPassword(passwordEncoder.encode("root123"));
            user.setRole(roleRepository.findByRoleName(RoleName.DEVELOPER).orElseThrow(() -> new ResourceNotFoundException("getRole")));
            user.setEnabled(true);
            userRepository.save(user);




        }
    }
}
