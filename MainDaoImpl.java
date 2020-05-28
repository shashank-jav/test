package com.dgh.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dgh.model.entity.SingleSignonUser;
import com.dgh.utility.BcryptUtil;
import com.dgh.utility.HibernateUtil;




@Repository
@Transactional(value="transactionManager")
public class MainDaoImpl implements MainDao {
	
	
	
	@Override
	public List<?> executeNamedQuery(String queryName, String[] paramNames, String[] paramValues) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<?> query = session.getNamedQuery(queryName);
		int i = 0;
		for(String paramName : paramNames) {
			query.setParameter(paramName, paramValues[i++]);
		}
		return query.getResultList();
	}
	
	
	@Override
	public Integer registerUser(SingleSignonUser user) {
		
		Integer id=0;
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession())
		{
        // start a transaction
        transaction = session.beginTransaction();

		id= (int) session.save(user);
		 // commit transaction
        transaction.commit();
        
		}catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
		return id;
	}




	@Override
	public void updateUserById(SingleSignonUser user) {
		
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession())
		{
        // start a transaction
        transaction = session.beginTransaction();
		     
		    	    session.update(user);
				
		 transaction.commit();
		      
		}catch (Exception e) {
            if (transaction != null) {
            	e.printStackTrace();  
                transaction.rollback();
            }
            e.printStackTrace();
        }
		//finally {session.close();} 
		
	}


	@Override
	public boolean checkIfEmailExists(String username) {
		boolean flag=false;
		try {
			Session session =HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			TypedQuery<SingleSignonUser> query= session.createQuery("from SingleSignonUser u where u.username= :uniqueemail and u.enabled =1", SingleSignonUser.class);
			query.setParameter("uniqueemail", username);
			List<SingleSignonUser> record = query.getResultList();
			session.getTransaction().commit();
			if(record.isEmpty())
			{
				System.out.println("No  user with given email found in DB!");
			}
			else {
			
				flag=true; 
				System.out.println("Yes, Exists");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<SingleSignonUser> getUsersWithRole() {
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TypedQuery<SingleSignonUser> query1= session.createQuery("from SingleSignonUser u  ", SingleSignonUser.class);
		List<SingleSignonUser> list=query1.getResultList();
		session.getTransaction().commit();
		return list;
	}




	@Override
	public SingleSignonUser getUserByEmail(String email) {
		SingleSignonUser user= new SingleSignonUser();
		
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TypedQuery<SingleSignonUser> query= session.createQuery("from SingleSignonUser u where u.username= :uniqueemail and u.enabled =1", SingleSignonUser.class);
		query.setParameter("uniqueemail", email);
		List<SingleSignonUser> record = query.getResultList();
		session.getTransaction().commit();
		if(record.isEmpty())
		{
			System.out.println("No  user with given email found in DB!");
		}
		else {
		
			
			user=record.get(0);
			System.out.println("Yes, Exists");
		}
		//session.close();
		return user;
	}


	@Override
	public boolean savePasswordResetToken(SingleSignonUser userAuthInfo) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.merge(userAuthInfo);
			System.out.println("Auth Record Updated");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	return true; 
	}
	
	@Override
	public SingleSignonUser checkIfRecoveryTokenValid(String username,String token) {
		
		SingleSignonUser ssouser = new SingleSignonUser();
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			TypedQuery<SingleSignonUser> query= session.createQuery("from SingleSignonUser u where u.username= :username and u.token= :token ", SingleSignonUser.class);
			query.setParameter("username", username);
			query.setParameter("token", token);
			List<SingleSignonUser> record = query.getResultList();
			if(record.isEmpty())
			{
				System.out.println("No  user with given id found in DB!");
			}
			else {
				ssouser=record.get(0);
				System.out.println("Yes, Requested");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ssouser;
	}


	@Override
	public boolean checkIfUserRequestedPasswordRecovery(SingleSignonUser userByEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(SingleSignonUser sso, String password) {
		boolean flag = false;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			TypedQuery<SingleSignonUser> query = session.createQuery("from SingleSignonUser u where u.username= :username", SingleSignonUser.class);
			query.setParameter("username", sso.getUsername());
			List<SingleSignonUser> record = query.getResultList();
			if (record.isEmpty()) {
				System.out.println("No  user with given userid found in DB!");
			} else {
				SingleSignonUser qrm = record.get(0);
				qrm.setPassword(BcryptUtil.getEncryptedPassword(password));
				session.update(qrm);
				session.getTransaction().commit();
				System.out.println("User password changed!!");
				//BcryptUtil.getEncryptedPassword(password);
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}



	
}