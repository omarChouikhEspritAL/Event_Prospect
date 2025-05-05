package esprit.tn.ms_prospect.Mappers;

import esprit.tn.ms_prospect.Dto.ProspectDto;
import esprit.tn.ms_prospect.Entities.Prospect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProspectMapper {
Prospect mapToEntity(ProspectDto prospectDto);
ProspectDto mapToDto(Prospect prospect);
}
