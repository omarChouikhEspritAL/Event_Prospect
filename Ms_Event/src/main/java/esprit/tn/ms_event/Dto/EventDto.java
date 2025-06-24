package esprit.tn.ms_event.Dto;


import java.time.LocalDate;

public record EventDto(String id, String name, LocalDate dateEvent) {
    public EventDto withName(String name) {
        return new EventDto(this.id, name, this.dateEvent);
    }

    public EventDto withDateEvent(LocalDate dateEvent) {
        return new EventDto(this.id, this.name, dateEvent);
    }
}
