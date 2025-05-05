package esprit.tn.ms_prospect.Services;

import esprit.tn.ms_prospect.Dto.ProspectDto;
import esprit.tn.ms_prospect.Entities.Prospect;
import esprit.tn.ms_prospect.Mappers.ProspectMapper;
import esprit.tn.ms_prospect.Repositories.ProspectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IProspectServiceImp implements IProspectService{
    private final ProspectRepository prospectRepository;
    private final ProspectMapper prospectMapper;

    @Override
    public ProspectDto add(ProspectDto prospectDto) {
        Prospect prospect = prospectMapper.mapToEntity(prospectDto);
        prospect.setCreatedAt(LocalDateTime.now());
        return prospectMapper.mapToDto(prospectRepository.save(prospect));
    }

    @Override
    public ProspectDto update(String idProspect, Map<Object, Object> fields) {
        Prospect prospect = prospectRepository.findById(idProspect)
                .orElseThrow(()-> new IllegalArgumentException("Prospect not found: " + idProspect));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Prospect.class,(String) key);
            field.setAccessible(true);
            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-d");
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
    public boolean delete(String idProspect) {
        prospectRepository.deleteById(idProspect);
        return prospectRepository.existsById(idProspect);
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
