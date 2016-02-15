package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Person;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
	private EntityManager manager;

	

	@Override
	public void addPerson(Person person) {
		manager.persist(person);

	}


	@Override
	public List<Person> listPeople() {
		List<Person> people = manager.createQuery("select p from Person p", Person.class).getResultList();
		return people;
	}

}
