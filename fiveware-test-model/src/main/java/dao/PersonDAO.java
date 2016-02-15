package dao;

import java.util.List;

import model.Person;

public interface PersonDAO {

	public void addPerson(Person person);
	public List<Person> listPeople();
}
