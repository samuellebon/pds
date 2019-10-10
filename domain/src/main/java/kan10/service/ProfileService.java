package kan10.service;

import kan10.dao.ProfileDao;
import kan10.entities.Profile;
import kan10.interfaces.IProfileService;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    private final ProfileDao profileDao;

    @Inject
    public ProfileService(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileDao.findAll();
    }

    @Override
    public boolean createProfile(Profile profile) {
        profileDao.save(profile);
        return true;
    }

    @Override
    public DataTablesOutput<Profile> findAll(DataTablesInput input) {
        return profileDao.findAll(input);
    }
}