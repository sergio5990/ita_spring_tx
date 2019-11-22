package by.academy.it.global.platform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * Class AtomikosJtaPlatform
 *
 * Created by yslabko on 11/01/2017.
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {
    private static final long serialVersionUID = 1L;
    private UserTransactionManager utm;

    public AtomikosJtaPlatform() {
        utm = new UserTransactionManager();
    }

    @Override
    protected TransactionManager locateTransactionManager() {
        return utm;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return utm;
    }
}
