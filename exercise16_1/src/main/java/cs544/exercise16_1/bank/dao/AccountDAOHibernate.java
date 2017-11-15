package cs544.exercise16_1.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;

import cs544.exercise16_1.bank.domain.Account;

public class AccountDAOHibernate implements IAccountDAO {

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr
		// ="+account.getAccountnumber());

		sf.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr
		// ="+account.getAccountnumber());

		sf.getCurrentSession().saveOrUpdate(account);

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr
		// ="+accountnumber);
		return (Account) sf.getCurrentSession().get(Account.class, accountnumber);
	}

	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts() {
		Collection<Account> accountList = sf.getCurrentSession().createQuery("from Account").list();

		return accountList;
	}

}
