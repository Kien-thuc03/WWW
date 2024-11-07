package vn.edu.iuh.fit.labweek_05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillType;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "skill", schema = "works")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private SkillType type;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "skill")
    private Set<JobSkill> jobSkills = new LinkedHashSet<>();

}