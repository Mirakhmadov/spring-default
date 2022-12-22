package example.server.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import example.server.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    @Email
    private String email;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Pattern(regexp = "^(?:(?=.*?\\p{N})(?=.*?[\\p{S}\\p{P} ])(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]{8,16}$",
            message = "Password 8 tadan 16 tagacha va eng kamida 1 ta katta harf, raqam va belgidan iborat bolish kerak")
    private String password;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Pattern(regexp = "^(?:(?=.*?\\p{N})(?=.*?[\\p{S}\\p{P} ])(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]{8,16}$",
            message = "Password 8 tadan 16 tagacha va eng kamida 1 ta katta harf, raqam va belgidan iborat bolish kerak")
    private String prePassword;

//    private ContactDto contact;

    private Role role;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Collection<? extends GrantedAuthority> authorities;

//    private CompanyDto company;

    public UserDto(UUID id, @Email String email) {
        this.id = id;
        this.email = email;
    }

    public UserDto(UUID id, @Email String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
