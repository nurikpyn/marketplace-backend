package br.com.vfs.marketplacebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Image {
    private String idProvider;
    private String provider;
    private String type;
    private boolean primary;
    private byte[] bytes;
}
