package com.example.microone.dto;

import com.example.microone.model.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageDTO {
    @Schema(name = "senderId")
    private UUID senderId;

    @Schema(name = "content") // Текст который мы отправляем
    private String content;

    @Schema(name = "is_read")
    private Boolean isRead;
}
