package ru.rebs.bonus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.rebs.bonus.model.BonusDto;
import ru.rebs.bonus.repositories.SubjectBonusRepository;
import ru.rebs.bonus.repositories.entities.SubjectBonus;
import ru.rebs.bonus.services.BonusService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@SpringJUnitConfig(BonusService.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BonusServiceTests {

    @MockBean
    private SubjectBonusRepository subjectBonusRepository;

    @Autowired
    private BonusService bonusService;

    @Test
    public void updateBonuses_if_subject_not_exist_Success() {
        Mockito.when(subjectBonusRepository.findBySubjectIdAndSubjectType(any(), any())).thenReturn(Optional.empty());
        Mockito.when(subjectBonusRepository.save(any())).thenReturn(new SubjectBonus().setId(1L).setBonuses(10L));

        BonusDto result = bonusService.updateBonuses("testSubjectId", "testSubjectType", 10);

        Mockito.verify(subjectBonusRepository, Mockito.times(1))
                .findBySubjectIdAndSubjectType("testSubjectId", "testSubjectType");

        ArgumentCaptor<SubjectBonus> subjectBonusArgumentCaptor = ArgumentCaptor.forClass(SubjectBonus.class);

        Mockito.verify(subjectBonusRepository, Mockito.times(1)).save(subjectBonusArgumentCaptor.capture());

        assertEquals(10L, subjectBonusArgumentCaptor.getValue().getBonuses());
        assertEquals("testSubjectId", subjectBonusArgumentCaptor.getValue().getSubjectId());
        assertEquals("testSubjectType", subjectBonusArgumentCaptor.getValue().getSubjectType());

        assertEquals(10L, result.getBonuses());
    }

    @Test
    public void updateBonuses_if_subject_not_exist_Exception() {
        Mockito.when(subjectBonusRepository.findBySubjectIdAndSubjectType(any(), any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class,
                () -> bonusService.updateBonuses("testSubjectId", "testSubjectType", -10));

        assertEquals("Количество бонусов не может быть меньше 0", exception.getMessage());

        Mockito.verify(subjectBonusRepository, Mockito.times(1))
                .findBySubjectIdAndSubjectType("testSubjectId", "testSubjectType");
    }

    @Test
    public void getBonus_if_subject_not_exist() {
        Mockito.when(subjectBonusRepository.findBySubjectIdAndSubjectType(any(), any())).thenReturn(Optional.empty());
        BonusDto result = bonusService.getBonus("testSubjectId", "testSubjectType");
        assertEquals(0L, result.getBonuses());
    }
}
