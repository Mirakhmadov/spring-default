package example.server.service.main;

import example.server.entity.User;
import example.server.entity.enums.RoleName;
import example.server.repository.mainReps.RoleRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecondaryService {
    private final RoleRepository roleRepository;

    public SecondaryService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean orderCheck(User currentUser, RoleName roleName){
        return currentUser.getRole().equals(roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResourceNotFoundException("getRole")));
    }

    public boolean orderDeveloper(User currentUser){
        return currentUser.getRole().equals(roleRepository.findByRoleName(RoleName.DEVELOPER).orElseThrow(() -> new ResourceNotFoundException("getRole")));
    }

    public boolean orderAdmin(User currentUser){
        return currentUser.getRole().equals(roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new ResourceNotFoundException("getRole")));
    }
}
