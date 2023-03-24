package com.zakharov.dao;

import com.zakharov.exceptionHandler.DAOExceptionHandler;
import com.zakharov.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Repository
@Validated
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Person> index() {
        TypedQuery<Person> query = sessionFactory.openSession().createQuery("from Person", Person.class);
        return query.getResultList();
    }

    public Person get(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, id);
            session.getTransaction().commit();
            return person;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(@Valid Person person) throws DAOExceptionHandler {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DAOExceptionHandler(e.getCause().getMessage());
        }
    }

    public void update(int id, Person updatedPerson) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            updatedPerson.setId(id);
            session.update(updatedPerson);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void delete(Person person) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
