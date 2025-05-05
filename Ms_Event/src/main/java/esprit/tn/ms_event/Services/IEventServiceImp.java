package esprit.tn.ms_event.Services;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Entities.Event;
import esprit.tn.ms_event.Mappers.EventMapper;
import esprit.tn.ms_event.Repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class IEventServiceImp implements IEventService{
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    @Override
    public EventDto add(EventDto eventDto) {
        Event event = eventMapper.mapToEntity(eventDto);
        event.setCreatedAt(LocalDateTime.now());
        return eventMapper.mapToDto(eventRepository.save(event));
    }

    @Override
    public EventDto update(String idEvent, Map<Object, Object> fields) {
        Event event = eventRepository.findById(idEvent)
                .orElseThrow(()-> new IllegalArgumentException("Event not found: " + idEvent));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Event.class,(String) key);
            field.setAccessible(true);
            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-d");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field,event, localDate);
            }else {
                ReflectionUtils.setField(field,event,value);
            }
        });
        event.setUpdatedAt(LocalDateTime.now());
        return eventMapper.mapToDto(eventRepository.save(event));
    }

    @Override
    public boolean delete(String idEvent) {
        eventRepository.deleteById(idEvent);
        return eventRepository.existsById(idEvent);
    }

    @Override
    public Page<EventDto> getEvents(int pageNbr, int pageSize) {
        return eventRepository.findAll(PageRequest.of(pageNbr,pageSize))
                .map(eventMapper::mapToDto);
    }

    @Override
    public EventDto getEvent(String id) {
        return eventRepository.findById(id)
                .map(eventMapper::mapToDto)
                .orElseThrow(()-> new IllegalArgumentException("Event not found"));
    }

    @Override
    public EventDto getEventByDateEvent(LocalDate dateEvent) {
        return eventRepository.findByDateEvent(dateEvent)
                .map(eventMapper::mapToDto)
                .orElseThrow(()->new IllegalArgumentException("Event not found with this Date"));
    }
}
