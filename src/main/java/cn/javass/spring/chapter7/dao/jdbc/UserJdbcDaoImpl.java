package cn.javass.spring.chapter7.dao.jdbc;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import cn.javass.spring.chapter7.UserModel;
import cn.javass.spring.chapter7.dao.IUserDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserJdbcDaoImpl extends JdbcDaoSupport implements IUserDao {

    private static final String INSERT_SQL = "insert into test(name) values(:myName)";
    private static final String COUNT_ALL_SQL = "select count(*) from test";
    
    
    @Override
    public void save(UserModel model) {
        getJdbcTemplate().update(INSERT_SQL, new BeanPropertySqlParameterSource(model));
    }

    @Override
    public int countAll() {
        return getJdbcTemplate().queryForObject(COUNT_ALL_SQL,Integer.class);
    }
    
    
}
