package test.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import test.demo.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

	public User findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		Criteria criteria = createEntityCriteria();
		List<User> allUser = criteria.list();
		return allUser;
	}

}
