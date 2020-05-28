package com.dgh.utility;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.dgh.model.entity.SingleSignonUser;
import com.dgh.model.entity.SingleSignonUserRole;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
                settings.put(Environment.URL, "jdbc:oracle:thin:@//192.168.0.163:1521/dgh");
                settings.put(Environment.USER, "xuser");
                settings.put(Environment.PASS, "xuser");
                
                /*settings.put(Environment.URL, "jdbc:oracle:thin:@//192.168.4.124:1522/vcms");
                settings.put(Environment.USER, "dcmsdev");
                settings.put(Environment.PASS, "dcmsdev1");*/
                
                settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle12cDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
               // settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(SingleSignonUser.class);
                configuration.addAnnotatedClass(SingleSignonUserRole.class);
                
            
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
