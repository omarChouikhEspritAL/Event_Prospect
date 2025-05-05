package esprit.tn.ms_prospect.Services;

import esprit.tn.ms_prospect.Dto.ProspectDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProspectService {
    ProspectDto add(ProspectDto prospectDto);
    ProspectDto update (String idProspect, Map<Object,Object> fields);
    boolean delete (String idProspect);
    Page<ProspectDto> getProspects (int pageNbr, int pageSize);
    ProspectDto getProspect (String id);
    ProspectDto getProspectByFiledWork(String filedWork);
}
