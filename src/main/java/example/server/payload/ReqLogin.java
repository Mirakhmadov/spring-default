package example.server.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqLogin {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
