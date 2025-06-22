package esprit.tn.ms_event.Services;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Entities.Event;
import esprit.tn.ms_event.Mappers.EventMapper;
import esprit.tn.ms_event.Repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IEventServiceImp implements IEventService{
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    @Override
    public EventDto add(EventDto eventDto) {
        log.info("Adding new event: {}", eventDto);
        Event event = eventMapper.mapToEntity(eventDto);
        event.setCreatedAt(LocalDateTime.now());
        return eventMapper.mapToDto(eventRepository.save(event));
    }

    @Override
    @Transactional  // Add transaction management
    public EventDto update(String idEvent, Map<Object, Object> fields) {
        Event event = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + idEvent));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Event.class, (String) key);
            if (field == null) {
                throw new IllegalArgumentException("Invalid field name: " + key);
            }
            field.setAccessible(true);
            try {
                if (field.getType().equals(LocalDate.class)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Fixed pattern (mm -> MM)
                    LocalDate localDate = LocalDate.parse(value.toString(), formatter);
                    ReflectionUtils.setField(field, event, localDate);
                } else {
                    ReflectionUtils.setField(field, event, value);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Error updating field " + key + ": " + e.getMessage());
            }
        });

        event.setUpdatedAt(LocalDateTime.now());
        return eventMapper.mapToDto(eventRepository.save(event));
    }
    @Override
    @Transactional
    public boolean delete(String idEvent) {
        if (!eventRepository.existsById(idEvent)) {
            throw new EntityNotFoundException("Event not found with id: " + idEvent);
        }
        eventRepository.deleteById(idEvent);
        // Return true if the entity was successfully deleted (no longer exists)
        return !eventRepository.existsById(idEvent);
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
