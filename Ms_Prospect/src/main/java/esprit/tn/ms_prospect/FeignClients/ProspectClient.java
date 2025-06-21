package esprit.tn.ms_prospect.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.events.Event;

import java.util.Map;

@FeignClient(name = "Event-client",url = "http://localhost:8080/events")
public interface ProspectClient {

    @GetMapping("{id}")
    Event getEvent(@PathVariable("id") String id);

    @PatchMapping("{id}")
    Event patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id);
}
