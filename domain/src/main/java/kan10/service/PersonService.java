package kan10.service;
import kan10.dao.PersonDAO;
import kan10.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PersonService implements kan10.interfaces.PersonService {
    @Autowired
    PersonDAO personDAO;

    @Override
    public List<Person> getAllPersons() {
        return personDAO.findAll(); // Redirection des requêtes
    }

    @Override
    public Person getPersonById(int id) { return personDAO.findPersonById(id); }

    @Override
    public Person savePersonToDb(Person person) {
        return personDAO.save(person);
    }

    //Not implemented
    public boolean createValidation (Person person){ return true;};

}