package ru.rebs.bonus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.rebs.bonus.controllers.BonusController;
import ru.rebs.bonus.model.AddBonusDto;
import ru.rebs.bonus.model.BonusDto;
import ru.rebs.bonus.model.MinusBonusDto;
import ru.rebs.bonus.services.BonusService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@SpringJUnitConfig(BonusController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BonusControllerTests {

    @MockBean
    private BonusService bonusService;

    @Autowired
    private BonusController bonusController;

    @Test
    public void getBonusTest() {
        Mockito.when(bonusService.getBonus(any(), any())).thenReturn(new BonusDto(10L));

        BonusDto result = bonusController.getBonus("testSubjectId", "testSubjectType");

        Mockito.verify(bonusService, Mockito.times(1))
                .getBonus("testSubjectId", "testSubjectType");

        assertEquals(10L, result.getBonuses());
    }

    @Test
    public void addBonusesTest() {
        Mockito.when(bonusService.updateBonuses(any(), any(), any())).thenReturn(new BonusDto(10L));

        AddBonusDto addBonusDto = new AddBonusDto();
        addBonusDto.setBonusesToAdd(10);
        addBonusDto.setSubjectId("testSubjectId");
        addBonusDto.setSubjectType("testSubjectType");

        BonusDto result = bonusController.addBonuses(addBonusDto);

        Mockito.verify(bonusService, Mockito.times(1))
                .updateBonuses("testSubjectId", "testSubjectType", 10);

        assertEquals(10L, result.getBonuses());
    }

    @Test
    public void minusBonusesTest() {
        Mockito.when(bonusService.updateBonuses(any(), any(), any())).thenReturn(new BonusDto(10L));

        MinusBonusDto minusBonusDto = new MinusBonusDto();
        minusBonusDto.setBonusesToSubtract(-10);
        minusBonusDto.setSubjectId("testSubjectId");
        minusBonusDto.setSubjectType("testSubjectType");

        BonusDto result = bonusController.minusBonuses(minusBonusDto);

        Mockito.verify(bonusService, Mockito.times(1))
                .updateBonuses("testSubjectId", "testSubjectType", 10);

        assertEquals(10L, result.getBonuses());
    }
}
