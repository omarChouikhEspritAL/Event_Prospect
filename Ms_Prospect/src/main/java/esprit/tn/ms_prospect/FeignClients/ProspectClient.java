package esprit.tn.ms_prospect.FeignClients;

import esprit.tn.ms_prospect.Dto.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "event-client", url = "http://localhost:8080/events")
public interface ProspectClient {

    @GetMapping("/{id}")
    EventDto getEvent(@PathVariable("id") String id);

    @PatchMapping("/{id}")
    EventDto patchUpdate(@RequestBody Map<String, Object> fields, @PathVariable("id") String id);
}
