package esprit.tn.ms_prospect.Controllers;

import esprit.tn.ms_prospect.Dto.ProspectDto;
import esprit.tn.ms_prospect.Services.IProspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/prospects")
@RequiredArgsConstructor
public class ProspectRestController {

    private final IProspectService prospectService;

    @PostMapping
    public ProspectDto add(@RequestBody ProspectDto prospectDto){
    return prospectService.add(prospectDto);
    }

    @PatchMapping("{id}")
    public ProspectDto patchUpdate(@RequestBody Map<Object,Object> fields,@PathVariable String id){
        return prospectService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return prospectService.delete(id);
    }


    @GetMapping
    public Page<ProspectDto> getProspects(int pageNbr, int pageSize){
        return prospectService.getProspects(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public ProspectDto getProspect(@PathVariable String id){
        return prospectService.getProspect(id);
    }

    @GetMapping("filedWork/{filedWork}")
    public ProspectDto getProspectByFiledWork(@PathVariable String filedWork){
        return prospectService.getProspectByFiledWork(filedWork);
    }

}
