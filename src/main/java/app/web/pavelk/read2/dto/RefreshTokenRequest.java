package app.web.pavelk.read2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Token refresh data")
public class RefreshTokenRequest {
    @NotBlank
    @NotNull
    @Size(min = 2)
    @Schema(description = "token")
    private String refreshToken;
    @NotNull
    @Schema(description = "name user")
    private String username;
}
