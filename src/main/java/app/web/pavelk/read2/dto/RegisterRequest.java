package app.web.pavelk.read2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "sing up data")
public class RegisterRequest {
    @NotNull
    @Schema(description = "email")
    private String email;
    @NotNull
    @Schema(description = "username")
    private String username;
    @NotNull
    @Schema(description = "password")
    private String password;
}
