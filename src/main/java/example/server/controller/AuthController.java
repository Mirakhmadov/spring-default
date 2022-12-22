package example.server.controller;

import example.server.bean.DateComponents;
import example.server.entity.User;
import example.server.payload.UserDto;
import example.server.payload.ReqLogin;
import example.server.payload.secondary.CustomResponse;
import example.server.payload.secondary.JwtToken;
import example.server.repository.UserRepository;
import example.server.security.CurrentUser;
import example.server.security.JwtTokenProvider;
import example.server.service.UserService;
import example.server.service.main.AuthService;
import example.server.utils.MessageConst;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final
    AuthService authService;
    private final
    UserService userService;
    private final
    UserRepository userRepository;
    private final
    AuthenticationManager authenticationManager;
    private final
    JwtTokenProvider jwtTokenProvider;
    private final
    PasswordEncoder passwordEncoder;

    private final DateComponents dateComponents;

    public AuthController(AuthService authService, UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, DateComponents dateComponents) {
        this.authService = authService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.dateComponents = dateComponents;
    }


    @PostMapping("/login")
    private CustomResponse login(@Valid @RequestBody ReqLogin reqLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(reqLogin.getEmail(), reqLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user);
            return new CustomResponse(MessageConst.LOGIN_SUCCESS, true, 200, dateComponents.getNow(), new JwtToken(token));
        } catch (Exception e) {
            return new CustomResponse(MessageConst.LOGIN_ERROR, false, 401, dateComponents.getNow());
        }
    }


    @GetMapping("/me")
    public CustomResponse getUserMe(@CurrentUser User user) {
        return new CustomResponse(MessageConst.GET_SUCCESS, true, 200, dateComponents.getNow(), userService.getUserForList(user));
    }

    @PostMapping("/register")
    public CustomResponse register(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }


    @GetMapping("/encoder/{password}")
    public String encodePassword(@PathVariable String password) {
        return passwordEncoder.encode(password);
    }
}
