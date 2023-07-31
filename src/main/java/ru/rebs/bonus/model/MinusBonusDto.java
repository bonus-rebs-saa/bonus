package ru.rebs.bonus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MinusBonusDto {

    @NotBlank
    @Size(max = 255)
    @Schema(name = "subjectId", description = "Ид субъекта, которому снимаются бонусы.", example = "1")
    private String subjectId;

    @NotBlank
    @Size(max = 255)
    @Schema(name = "subjectType", description = "Тип субъекта, которому снимаются бонусы.", example = "type")
    private String subjectType;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Schema(name = "bonusesToSubtract", description = "Количество бонусов для вычитания.", defaultValue = "0", minimum = "0", example = "0")
    private Integer bonusesToSubtract;

}
