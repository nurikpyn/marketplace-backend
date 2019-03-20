package br.com.vfs.marketplacebackend.message.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {

    private String token;
    @Builder.Default
    private String type = "Bearer";
}
