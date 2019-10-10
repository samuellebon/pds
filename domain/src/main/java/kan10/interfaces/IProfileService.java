package kan10.interfaces;

import kan10.entities.Profile;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface IProfileService {

    List<Profile> getAllProfiles();
    boolean createProfile(Profile profile);

    DataTablesOutput<Profile> findAll(DataTablesInput input);
}
