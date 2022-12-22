package example.server.service;

import example.server.entity.User;
import example.server.entity.enums.RoleName;
import example.server.payload.UserDto;
import example.server.payload.secondary.CustomResponse;
import example.server.repository.UserRepository;
import example.server.repository.mainReps.RoleRepository;
import example.server.security.JwtTokenProvider;
import example.server.service.main.CustomResponseService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CustomResponseService customResponseService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, CustomResponseService customResponseService, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.customResponseService = customResponseService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public CustomResponse registerUser(UserDto userDto) {
        try {
            if (userDto.getPassword().equals(userDto.getPrePassword())) {
                if (!userRepository.existsByEmailEqualsIgnoreCase(userDto.getEmail())) {
                    User user = new User();
                    user.setEmail(userDto.getEmail());
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    user.setRole(roleRepository.findByRoleName(RoleName.CANDIDATE).orElseThrow(() -> new ResourceNotFoundException("getRole")));


                    userRepository.save(user);
                    return customResponseService.savedResponse(user);
                }
                return customResponseService.existsResponse();
            }
            return customResponseService.objectsNotSameResponse();
        } catch (Exception e) {
            return customResponseService.tryErrorResponse();
        }
    }

    public UserDto getUserForList(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getRole());
    }
}

