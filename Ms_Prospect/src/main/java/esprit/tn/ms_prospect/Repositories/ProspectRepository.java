package esprit.tn.ms_prospect.Repositories;

import esprit.tn.ms_prospect.Entities.Prospect;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProspectRepository extends MongoRepository<Prospect,String> {
    Optional<Prospect> findByFiledWork(String filedWork);
}
