package ru.rebs.bonus.repositories.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Data
@Entity
@Audited
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "subject_bonus")
public class SubjectBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String subjectType;

    @Column(nullable = false)
    private String subjectId;

    @Column(nullable = false)
    private Long bonuses;
}
