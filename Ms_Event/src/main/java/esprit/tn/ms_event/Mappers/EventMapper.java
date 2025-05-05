package esprit.tn.ms_event.Mappers;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Entities.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event mapToEntity(EventDto eventDto);
    EventDto mapToDto(Event event);
}
