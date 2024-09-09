package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "select a from Account a"),
        @NamedQuery(name = "Account.deleteByAccountId", query = "delete from Account a where a.accountId = :accountId"),
        @NamedQuery(name = "Account.updateFullNameAndPasswordAndEmailAndPhoneAndStatusByAccountId", query = "update Account a set a.fullName = :fullName, a.password = :password, a.email = :email, a.phone = :phone, a.status = :status where a.accountId = :accountId"),
        @NamedQuery(name = "Account.findByAccountId", query = "select a from Account a where a.accountId = :accountId"),
        @NamedQuery(name = "Account.findByAccountIdAndPassword", query = "select a from Account a where a.accountId = :accountId and a.password = :password"),
        @NamedQuery(name = "Account.updateFullNameAndPasswordAndEmailAndPhoneAndStatusByAccountIdLike", query = "update Account a set a.fullName = :fullName, a.password = :password, a.email = :email, a.phone = :phone, a.status = :status where a.accountId like :accountId")
})
public class Account {
    @Id
    @Size(max = 50)
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Size(max = 50)
    @NotNull
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Size(max = 50)
    @NotNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;

    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "account")
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

    public List<String> getRoleNames() {
        return grantAccesses.stream()
                .map(grantAccess -> grantAccess.getRole().getRoleName())
                .collect(Collectors.toList());
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}