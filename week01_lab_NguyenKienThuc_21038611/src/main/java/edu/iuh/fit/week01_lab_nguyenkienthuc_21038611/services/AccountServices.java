package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.services;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.repositories.AccountRepository;

import java.util.List;

public class AccountServices {
    AccountRepository accountRepository = new AccountRepository();

    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }
    public void updateAccount(String accountId, String fullName, String password, String email, String phone, byte status, List<String> roleNames){
        accountRepository.updateAccount(accountId, fullName, password, email, phone, status, roleNames);
    }
    public void deleteAccountById(String accountId) {
        accountRepository.deleteAccountById(accountId);
    }
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
    public Account findAccountByAccountId(String accountId) {
        return accountRepository.findAccountByAccountId(accountId);
    }

    public Account validateLogin(String accountId, String password) {
        return accountRepository.validateLogin(accountId, password);
    }
    public boolean isAdministrator(String accountId, String roleId) {
        return accountRepository.isAdministrator(accountId,roleId);
    }
    public void updateRoles(String accountId, List<String> roleNames) {
        accountRepository.updateRoles(accountId, roleNames);
    }
}

