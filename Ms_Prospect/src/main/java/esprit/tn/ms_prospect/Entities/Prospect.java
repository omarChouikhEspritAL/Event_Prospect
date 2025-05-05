package esprit.tn.ms_prospect.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prospect implements Serializable {
    @Id
    @Indexed
    @Setter(AccessLevel.MODULE)
    String id;
    String firstName;
    String lastName;
    String filedWork;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
