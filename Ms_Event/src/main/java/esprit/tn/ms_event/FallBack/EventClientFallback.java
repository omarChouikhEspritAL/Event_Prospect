package esprit.tn.ms_event.FallBack;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Entities.Event;
import esprit.tn.ms_event.FeignClient.EventClient;

import java.util.Map;

public class EventClientFallback implements EventClient {
    @Override
    public EventDto getProspect(String id) {
        return new EventDto(id,"",null);
    }

    @Override
    public EventDto patchUpdate(Map<Object, Object> fields, String id) {
        System.out.println("Fallback for patchUpdate called");
        return new EventDto(id,"",null);
    }
}
