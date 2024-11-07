package edu.iuh.fit.lad5.model;

public class Job {
    private String id;
    private String description;

    public Job() {
    }

    public Job(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", description=" + description + '}';
    }
}
