package edu.iuh.fit.lad5.model;

public class Skill {
    private String id;
    private String skill_name;
    private String description;
    private String field;

    public Skill() {
    }

    public Skill(String id, String skill_name, String description, String field) {
        this.id = id;
        this.skill_name = skill_name;
        this.description = description;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", skill_name=" + skill_name + ", description=" + description + ", field=" + field + '}';
    }
}
