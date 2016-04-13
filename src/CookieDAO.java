/**
 * @author Yingxue Mei
 */
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.alice.bean.CookieBean;

public class CookieDAO extends GenericDAO<CookieBean> {
    public CookieDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(CookieBean.class, tableName, cp);
    }

    @Override
	public void createAutoIncrement(CookieBean bean) throws RollbackException {
		super.createAutoIncrement(bean);
	}

    @Override
	public void create(CookieBean bean) throws RollbackException {
		super.create(bean);
	}

	@Override
	public CookieBean read(Object... primaryKeyValues) throws RollbackException {
		return super.read(primaryKeyValues);
	}

	public CookieBean readHid(long id, String domain) throws RollbackException {
        // Calls GenericDAO's match() method.
        // This no match constraint arguments, match returns all the Item beans
		CookieBean[] cookies = match(MatchArg.equals("hid", id).and(MatchArg.equals("domain", domain)));
        return cookies != null && cookies.length > 0 ? cookies[0] : null;

    }

	@Override
	public void update(CookieBean bean) throws RollbackException {
				super.update(bean);
	}

}
