package esprit.tn.ms_event.Services;

import esprit.tn.ms_event.Dto.EventDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Map;

public interface IEventService {
    EventDto add(EventDto eventDto);
    EventDto update (String idEvent, Map<Object,Object> fields);
    boolean delete (String idEvent);
    Page<EventDto> getEvents (int pageNbr, int pageSize);
    EventDto getEvent (String id);
    EventDto getEventByDateEvent(LocalDate dateEvent);
}
