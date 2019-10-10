package kan10.mock;

import kan10.common.ProfileUtils;
import kan10.entities.Client;
import kan10.entities.Profile;
import kan10.service.ClientService;
import kan10.service.ProfileService;

import java.util.List;
import java.util.Random;

class MockClient {

    private static final int numberOfClients = 10;

    static void insertClient(ProfileService profileService, ClientService clientService) {
        List<Profile> profiles = profileService.getAllProfiles();

        for (int i = 1; i <= numberOfClients; i++) {
            Profile major = profiles.get(new Random().nextInt(profiles.size()));
            clientService.createClient(
                    new Client(i,
                            "client_" + i + "@pgm.com",
                            generatePhoneNumber(),
                            major,
                            ProfileUtils.generateMinorProfile(major, profiles)
                    )
            );
        }
    }

    private static String generatePhoneNumber() {
        Random r = new Random();

        int i1 = r.nextInt(8);
        int i2 = r.nextInt(8);
        int i3 = r.nextInt(8);
        int i4 = r.nextInt(742);
        int i5 = r.nextInt(10000);

        return String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
    }

}