package esprit.tn.ms_event.Controllers;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Services.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventRestController {
    private final IEventService eventService;
    @PostMapping
    public EventDto add(@RequestBody EventDto eventDto){
        return eventService.add(eventDto);
    }

    @PatchMapping("{id}")
    public EventDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return eventService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return eventService.delete(id);
    }


    @GetMapping
    public Page<EventDto> getEvents(int pageNbr, int pageSize){
        return eventService.getEvents(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public EventDto getEvent(@PathVariable String id){
        return eventService.getEvent(id);
    }

    @GetMapping("dateEvent/{dateEvent}")
    public EventDto getEventByDateEvent(@PathVariable LocalDate dateEvent){
        return eventService.getEventByDateEvent(dateEvent);
    }
}
