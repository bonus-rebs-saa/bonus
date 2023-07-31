package ru.rebs.bonus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BonusDto {
    @NotNull
    @Schema(name = "bonuses", description = "Количество бонусов", defaultValue = "0", minimum = "0")
    private Long bonuses;
}
