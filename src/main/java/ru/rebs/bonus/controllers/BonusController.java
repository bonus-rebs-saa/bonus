package ru.rebs.bonus.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rebs.bonus.model.AddBonusDto;
import ru.rebs.bonus.model.BonusDto;
import ru.rebs.bonus.model.MinusBonusDto;
import ru.rebs.bonus.services.BonusService;

@Tag(name = "BonusController", description = "Предоставляет методы для получения, добавления и списания бонусов")
@RestController
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @Operation(summary = "Получение доступного количества бонусов по субъекту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество доступных бонусов успешно вернулось",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BonusDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера",
                    content = @Content) })
    @GetMapping(value = "/bonus", consumes = MediaType.ALL_VALUE)
    public BonusDto getBonus(@RequestParam("subjectId") String subjectId,
                             @RequestParam("subjectType") String subjectType) {
        return bonusService.getBonus(subjectId, subjectType);
    }

    @Operation(summary = "Добавление бонусов у суъекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бонусы успешно добавлены у субъекта",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BonusDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера",
                    content = @Content) })
    @PostMapping(value = "/add-bonuses")
    public BonusDto addBonuses(@RequestBody @Validated AddBonusDto addBonusDto) {
        return bonusService.updateBonuses(
                addBonusDto.getSubjectId(),
                addBonusDto.getSubjectType(),
                addBonusDto.getBonusesToAdd()
        );
    }

    @Operation(summary = "Списание бонусов у суъекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бонусы успешно списаны у субъекта",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BonusDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера",
                    content = @Content) })
    @PostMapping(value = "/minus-bonuses")
    public BonusDto minusBonuses(@RequestBody @Validated MinusBonusDto minusBonusDto) {
        return bonusService.updateBonuses(
                minusBonusDto.getSubjectId(),
                minusBonusDto.getSubjectType(),
                -minusBonusDto.getBonusesToSubtract()
        );
    }

}
