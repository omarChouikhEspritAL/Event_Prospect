package esprit.tn.ms_prospect.Services;

import esprit.tn.ms_prospect.Dto.ProspectDto;
import esprit.tn.ms_prospect.Entities.Prospect;
import esprit.tn.ms_prospect.Kafka.KafkaProducerService;
import esprit.tn.ms_prospect.Mappers.ProspectMapper;
import esprit.tn.ms_prospect.Repositories.ProspectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class IProspectServiceImp implements IProspectService{
    private final ProspectRepository prospectRepository;
    private final ProspectMapper prospectMapper;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @Transactional
    public ProspectDto add(ProspectDto prospectDto) {
        Prospect prospect = prospectMapper.mapToEntity(prospectDto);
        prospect.setCreatedAt(LocalDateTime.now());
        Prospect saved = prospectRepository.save(prospect);

        // Envoi vers Kafka après insertion
        kafkaProducerService.sendMessage("prospect-topic", "PROSPECT_CREATED: " + saved.getId());

//        return prospectMapper.mapToDto(prospectRepository.save(saved));
          return prospectMapper.mapToDto(saved);
    }

    @Override
    public ProspectDto update(String idProspect, Map<Object, Object> fields) {
        Prospect prospect = prospectRepository.findById(idProspect)
                .orElseThrow(()-> new IllegalArgumentException("Prospect not found: " + idProspect));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Prospect.class,(String) key);
            field.setAccessible(true);
            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse((String)value,formatter);
                ReflectionUtils.setField(field,prospect,localDate);
            }else {
                ReflectionUtils.setField(field,prospect,value);
            }
        } );
        prospect.setUpdatedAt(LocalDateTime.now());
        return prospectMapper.mapToDto(prospectRepository.save(prospect));
    }
    @Override
    @Transactional
    public boolean delete(String id) {
        if (!prospectRepository.existsById(id)) {
            throw new EntityNotFoundException("Prospect not found");
        }
        prospectRepository.deleteById(id);
        kafkaProducerService.sendMessage("prospect-topic",
                "PROSPECT_DELETED:" + id);
        return prospectRepository.existsById(id);

    }

    @Override
    public Page<ProspectDto> getProspects(int pageNbr, int pageSize) {
        return prospectRepository.findAll(PageRequest.of(pageNbr,pageSize))
                .map(prospectMapper::mapToDto);
    }

    @Override
    public ProspectDto getProspect(String id) {
        return prospectRepository.findById(id)
                .map(prospectMapper::mapToDto)
                .orElseThrow(()-> new IllegalArgumentException("Prospect not found"));
    }

    @Override
    public ProspectDto getProspectByFiledWork(String filedWork) {
        return prospectRepository.findByFiledWork(filedWork)
                .map(prospectMapper::mapToDto)
                .orElseThrow(()->new IllegalArgumentException("prospect not found with this filed"));
    }


}
