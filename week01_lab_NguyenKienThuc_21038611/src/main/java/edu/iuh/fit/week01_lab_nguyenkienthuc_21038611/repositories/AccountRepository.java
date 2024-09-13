package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.repositories;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account;
import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.GrantAccess;
import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.GrantAccessId;
import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    // Constructor để khởi tạo EntityManager và EntityManagerFactory
    public AccountRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MariaDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    // Phương thức để thêm một account mới
    public void addAccount(Account account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // Phương thức để cập nhật account

    public void updateAccount(String accountId, String fullName, String password, String email, String phone, byte status, List<String> roleNames) {
        try {
            entityManager.getTransaction().begin();
            Account account = entityManager.find(Account.class, accountId);
            if (account != null) {
                account.setFullName(fullName);
                account.setPassword(password);
                account.setEmail(email);
                account.setPhone(phone);
                account.setStatus(status);

                // Log current roles
                System.out.println("Current roles: " + account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList()));

                // Retrieve current roles
                List<String> currentRoleNames = account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList());

                // Nếu danh sách quyền thay đổi, thực hiện cập nhật
                if (!currentRoleNames.equals(roleNames)) {
                    // Xóa các quyền cũ khỏi cơ sở dữ liệu
                    for (GrantAccess grantAccess : account.getGrantAccesses()) {
                        entityManager.remove(grantAccess);
                    }
                    account.getGrantAccesses().clear(); // Xóa khỏi bộ nhớ

                    // Thêm các quyền mới
                    for (String roleName : roleNames) {
                        Role role = entityManager.createNamedQuery("Role.findByRoleName", Role.class)
                                .setParameter("roleName", roleName)
                                .getSingleResult();

                        if (role != null) {
                            GrantAccess grantAccess = new GrantAccess();
                            GrantAccessId grantAccessId = new GrantAccessId();
                            grantAccessId.setAccountId(accountId);
                            grantAccessId.setRoleId(role.getRoleId());
                            grantAccess.setId(grantAccessId);
                            grantAccess.setIsGrant(true);

                            grantAccess.setAccount(account);
                            grantAccess.setRole(role);

                            entityManager.persist(grantAccess);
                            account.getGrantAccesses().add(grantAccess);
                        }
                    }
                }

                entityManager.merge(account);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateRoles(String accountId, List<String> roleNames) {
        try {
            entityManager.getTransaction().begin();
            Account account = entityManager.find(Account.class, accountId);
            if (account != null) {
                // Log current roles
                System.out.println("Current roles: " + account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList()));

                // Retrieve current roles
                List<String> currentRoleNames = account.getGrantAccesses().stream()
                        .map(grantAccess -> grantAccess.getRole().getRoleName())
                        .collect(Collectors.toList());

                // Nếu danh sách quyền thay đổi, thực hiện cập nhật
                if (!currentRoleNames.equals(roleNames)) {
                    // Xóa các quyền cũ khỏi cơ sở dữ liệu
                    for (GrantAccess grantAccess : account.getGrantAccesses()) {
                        entityManager.remove(grantAccess);
                    }
                    account.getGrantAccesses().clear(); // Xóa khỏi bộ nhớ

                    // Thêm các quyền mới
                    for (String roleName : roleNames) {
                        Role role = entityManager.createNamedQuery("Role.findByRoleName", Role.class)
                                .setParameter("roleName", roleName)
                                .getSingleResult();

                        if (role != null) {
                            GrantAccess grantAccess = new GrantAccess();
                            GrantAccessId grantAccessId = new GrantAccessId();
                            grantAccessId.setAccountId(accountId);
                            grantAccessId.setRoleId(role.getRoleId());
                            grantAccess.setId(grantAccessId);
                            grantAccess.setIsGrant(true);

                            grantAccess.setAccount(account);
                            grantAccess.setRole(role);

                            entityManager.persist(grantAccess);
                            account.getGrantAccesses().add(grantAccess);
                        }
                    }
                }

                entityManager.merge(account);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }


    // Phương thức để xóa account theo accountId
    public void deleteAccountById(String accountId) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createNamedQuery("Account.deleteByAccountId")
                    .setParameter("accountId", accountId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // Phương thức để lấy tất cả accounts
    public List<Account> getAllAccounts() {
        return entityManager.createNamedQuery("Account.findAll", Account.class).getResultList();
    }

    // Phương thức để tìm account theo accountId
    public Account validateLogin(String accountId, String password) {
        TypedQuery<Account> query = entityManager.createNamedQuery("Account.findByAccountIdAndPassword", Account.class);
        query.setParameter("accountId", accountId);
        query.setParameter("password", password);
        List<Account> accounts = query.getResultList();
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    // Phương thức lấy thông tin account theo accountId

    public Account findAccountByAccountId(String account_id) {
        TypedQuery<Account> query = entityManager.createNamedQuery("Account.findByAccountId", Account.class);
        query.setParameter("accountId", account_id);
        List<Account> accounts = query.getResultList();
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public boolean isAdministrator(String accountId, String roleId) {
        TypedQuery<GrantAccess> query = entityManager.createNamedQuery("GrantAccess.findById_RoleIdAndId_AccountId", GrantAccess.class);
        query.setParameter("roleId", roleId);
        query.setParameter("accountId", accountId);
        List<GrantAccess> grantAccessList = query.getResultList();
        return !grantAccessList.isEmpty();
    }


    // Phương thức để đóng EntityManager và EntityManagerFactory khi không sử dụng nữa
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
