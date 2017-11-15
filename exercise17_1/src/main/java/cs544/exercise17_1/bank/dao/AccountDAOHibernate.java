package cs544.exercise17_1.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.exercise17_1.bank.domain.Account;

@Transactional(propagation = Propagation.MANDATORY)
public class AccountDAOHibernate implements IAccountDAO {

	private SessionFactory sf;

	@Transactional(propagation = Propagation.SUPPORTS)
	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}

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
