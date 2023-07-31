package ru.rebs.bonus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.rebs.bonus.model.BonusDto;
import ru.rebs.bonus.repositories.entities.SubjectBonus;
import ru.rebs.bonus.repositories.SubjectBonusRepository;

@Service
@RequiredArgsConstructor
public class BonusService {
    private final SubjectBonusRepository subjectRepository;

    /**
     * Метод позволяет обновить бонусы у субъекта.
     *
     * @param subjectId   внешний ид субъекта, для которого обновляются бонусы.
     * @param subjectType тип субъекта.
     * @param addBonuses  может принимать как положительные так и отрицательные значения.
     * @return Обьект с обновленным количеством бонусов.
     * @throws IllegalStateException - если количество бонусов после изменения меньше 0.
     */
    @Retryable(value = CannotAcquireLockException.class,
            backoff = @Backoff(delay = 100, maxDelay = 300))
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BonusDto updateBonuses(String subjectId, String subjectType, Integer addBonuses) {
        SubjectBonus subjectBonus = subjectRepository.findBySubjectIdAndSubjectType(subjectId, subjectType)
                .orElseGet(() -> new SubjectBonus().setBonuses(0L).setSubjectId(subjectId).setSubjectType(subjectType));

        long newBonuses = subjectBonus.getBonuses() + addBonuses;

        if (newBonuses < 0) {
            throw new IllegalStateException("Количество бонусов не может быть меньше 0");
        }

        SubjectBonus result = subjectRepository.save(subjectBonus.setBonuses(newBonuses));
        return new BonusDto(result.getBonuses());
    }

    /**
     * Метод позволяет получить бонусы по субъекту.
     * Если субъекта не существует, вернется объект с количеством бонусов = 0
     *
     * @param subjectId   внешний ид субъекта, для которого обновляются бонусы.
     * @param subjectType тип субъекта.
     * @return Обьект бонусами.
     */
    @Transactional(readOnly = true)
    public BonusDto getBonus(String subjectId, String subjectType) {
        return subjectRepository.findBySubjectIdAndSubjectType(subjectId, subjectType)
                .map(subjectBonus -> new BonusDto(subjectBonus.getBonuses()))
                .orElseGet(() -> new BonusDto(0L));

    }
}
