package esprit.tn.ms_event.FallBack;

import esprit.tn.ms_event.Dto.ProspectDto;
import esprit.tn.ms_event.FeignClient.EventClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventClientFallback implements EventClient {

    @Override
    public ProspectDto getProspect(String id) {
        // Retourne un ProspectDto "vide" ou par défaut en fallback
        return new ProspectDto(id, "", "", "", "");
    }

    @Override
    public ProspectDto patchUpdate(Map<String, Object> fields, String id) {
        System.out.println("Fallback for patchUpdate called");
        // Même logique : retour par défaut
        return new ProspectDto(id, "", "", "", "");
    }

}
