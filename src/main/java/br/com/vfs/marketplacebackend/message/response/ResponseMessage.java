package br.com.vfs.marketplacebackend.message.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseMessage {

    private String message;
}
