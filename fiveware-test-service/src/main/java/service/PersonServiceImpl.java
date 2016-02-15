package service;

import java.util.List;

import model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Override
	@Transactional
	public void addPerson(Person person) {
		this.personDAO.addPerson(person);
	}

	@Override
	@Transactional
	public List<Person> listPeople() {
		return this.personDAO.listPeople();
	}

}
