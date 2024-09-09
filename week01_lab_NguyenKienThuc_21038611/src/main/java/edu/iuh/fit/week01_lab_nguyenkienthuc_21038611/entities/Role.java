package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Role.findByRoleId", query = "select r from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.deleteByRoleId", query = "delete from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.updateRoleNameAndDescriptionAndStatusByRoleId", query = "update Role r set r.roleName = :roleName, r.description = :description, r.status = :status where r.roleId = :roleId"),
        @NamedQuery(name = "Role.findByRoleName", query = "select r from Role r where r.roleName = :roleName")
})
public class Role {
    @Id
    @Size(max = 50)
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Size(max = 50)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Size(max = 50)
    @Column(name = "description", length = 50)
    private String description;

    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "role")
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}