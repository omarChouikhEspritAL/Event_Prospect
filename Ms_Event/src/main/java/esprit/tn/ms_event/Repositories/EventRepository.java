package esprit.tn.ms_event.Repositories;

import esprit.tn.ms_event.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,String> {

    Optional<Event> findByDateEvent(LocalDate dateEvent);
}
