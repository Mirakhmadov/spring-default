package example.server.service.main;

import example.server.entity.User;
import example.server.payload.UserDto;
import example.server.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmailEqualsIgnoreCase(s).orElseThrow(() -> new ResourceNotFoundException("getUsername"));
    }


    public UserDto getCurrentUser(User user){
        try {
            if (user != null){
                return new UserDto(
                        user.getId(),
                        user.getEmail(),
//                        contactService.getContactDto(user.getContact()),
                        user.getRole()
//                        companyService.getCompanyDtoForUser(user.getCompany())
                );
            }return null;
        }catch (Exception e){
            return null;
        }
    }
}
