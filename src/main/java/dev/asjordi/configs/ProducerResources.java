package dev.asjordi.configs;

import dev.asjordi.util.HibernateUtil;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConnection
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    public void closeBeanConnection(@Disposes @MysqlConnection Connection conn) throws SQLException {
        conn.close();
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    private void closeEntityManager(@Disposes EntityManager em) {
        if (em.isOpen()) em.close();
    }
}
