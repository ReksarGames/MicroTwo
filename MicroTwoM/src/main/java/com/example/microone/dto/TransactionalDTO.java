package com.example.microone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionalDTO {
    @Schema(description = "User который отправляет")
    private UUID currentUserId;
    @Schema(description = "Сумма перевода")
    private Double amount;
    @Schema(description = "Пользователь которому переводится")
    private UUID targetUserId;
}
