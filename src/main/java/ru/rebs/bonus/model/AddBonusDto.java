package ru.rebs.bonus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddBonusDto {

    @NotBlank
    @Size(max = 255)
    @Schema(name = "subjectId", description = "Ид субъекта, которому добавляются бонусы.", example = "1")
    private String subjectId;

    @NotBlank
    @Size(max = 255)
    @Schema(name = "subjectType", description = "Тип субъекта, которому добавляются бонусы.", example = "type")
    private String subjectType;

    @NotNull
    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @Schema(name = "bonusesToAdd", description = "Количество бонусов для добавления.", defaultValue = "0", minimum = "0", example = "10")
    private Integer bonusesToAdd;

}
