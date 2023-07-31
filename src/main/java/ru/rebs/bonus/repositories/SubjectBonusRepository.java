package ru.rebs.bonus.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rebs.bonus.repositories.entities.SubjectBonus;

import java.util.Optional;

public interface SubjectBonusRepository extends JpaRepository<SubjectBonus, Long> {
    Optional<SubjectBonus> findBySubjectIdAndSubjectType(String subjectId, String subjectType);
}
