package br.com.vfs.marketplacebackend.dto;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenJwt {
    private String userName;
    private String name;
    private Collection<SimpleGrantedAuthority> authorities;
}
