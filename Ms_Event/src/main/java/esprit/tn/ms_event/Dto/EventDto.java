package esprit.tn.ms_event.Dto;

import java.sql.Date;
import java.time.LocalDate;

public record EventDto(String id, String name, LocalDate dateEvent) {
}
