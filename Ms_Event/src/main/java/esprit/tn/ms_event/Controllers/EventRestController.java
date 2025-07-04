package esprit.tn.ms_event.Controllers;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Services.IEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventRestController {
    private final IEventService eventService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto add(@RequestBody EventDto eventDto){
        return eventService.add(eventDto);
    }

    @PatchMapping("{id}")
    public EventDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return eventService.update(id,fields);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete( @PathVariable String id){
        return eventService.delete(id);
    }


    @GetMapping
    public Page<EventDto> getEvents(@RequestParam(required = true) int pageNbr,
                                    @RequestParam(required = true) int pageSize){

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
