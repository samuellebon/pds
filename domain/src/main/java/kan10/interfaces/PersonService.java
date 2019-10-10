package kan10.interfaces;
import kan10.entities.Person;
import java.util.List;


public interface PersonService {

    List<Person> getAllPersons();
    Person savePersonToDb(Person person);
    Person getPersonById(int id);
}