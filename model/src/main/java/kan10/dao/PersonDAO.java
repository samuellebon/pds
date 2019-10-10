package kan10.dao;
import kan10.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


    @Repository
        public interface PersonDAO extends JpaRepository<Person, Integer> {

            @Query("from Person where id = ?1")
            Person findPersonById(Integer id);

        }




