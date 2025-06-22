package esprit.tn.ms_event.FeignClient;

import esprit.tn.ms_event.Dto.EventDto;
import esprit.tn.ms_event.Dto.ProspectDto;
import esprit.tn.ms_event.FallBack.EventClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.events.Event;

import java.util.Map;

@FeignClient(
        name = "prospect-client",
        url = "http://localhost:9090/prospects",
        fallback = EventClientFallback.class)  // classe fallback à implémenter
public interface EventClient {

    @GetMapping("/{id}")
    ProspectDto getProspect(@PathVariable("id") String id);

    @PatchMapping("/{id}")
    ProspectDto patchUpdate(@RequestBody Map<String, Object> fields, @PathVariable("id") String id);
}