package tw.team1.trail.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.team1.trail.dao.TrailRepository;
import tw.team1.trail.dto.TrailDTO;
import tw.team1.trail.model.Trail;
import tw.team1.trail.service.TrailService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Controller
@CrossOrigin
//@SessionAttributes(names = {"totalPages","totalElements"})
public class TrailController {
    @Autowired
    private TrailService tService;
    @Autowired
    private TrailRepository tRepo;


//    Pageable test
    @ResponseBody
    @GetMapping("/trailPage.controller")
    public Page<Trail> listItems(@PageableDefault(size = 3) Pageable pageable) {
        return tService.findAllItems(pageable);
    }




    //SQL 語法測試
//    @ResponseBody
//    @GetMapping("/trailSql.controller")
//    public List<Trail> findAllSql(){
//        return tService.findAllSql();
//    }

    //DTO test
    @ResponseBody
    @GetMapping("/trailDto.controller")
    public List<TrailDTO> getAllTrails() {
        return tRepo.findAll().stream()
                .map(TrailDTO::new)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/trail.controller")
    public List<Trail> processFindAllTrail(){
        return  tService.findAll();
    }

    @ResponseBody
    @GetMapping("/trail.controller2")
    public List<Trail> processFindAllTrail2(){
        return  tService.getAllTrails();
    }

    @GetMapping("/productquerymainaction.controller")
    public String processQueryMainAction() {
        return "productQueryAll";
    }

    @ResponseBody
    @GetMapping("/queryByPage/{pageNo}")
    public void processQueryByPageAction(@PathVariable("pageNo") int pageNo, Model m, HttpServletRequest request){

        int pageSize=10;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Trail> page = tService.findAllByPage(pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
//        m.addAttribute("totalPages", page.getTotalPages());
//        m.addAttribute("totalElements", page.getTotalElements());


        // Uncomment the line below if you want to return pagination-related information in the response
        // return Arrays.asList(page.getTotalPages(), page.getTotalElements(), page.getContent());

//        return page.getContent();
    }

    @ResponseBody
    @PostMapping(path = "/add.controller")
    public Trail processInsertAction(@RequestBody Trail t){
        System.out.println(t.getTname());
        return tService.insert(t);
    }

    @ResponseBody
    @PostMapping(path = "/add.controller2")
    public Trail processInsertAction2(@RequestParam("tname") String tname){
        System.out.println(tname);
        Trail insertBean = new Trail(tname);
        return tService.insert(insertBean);
    }

    //Photo
    @ResponseBody
    @PostMapping(path = "/addPhoto.controller")
    public Trail processInsertAction3(@RequestParam("tname") String tname, @RequestPart("tphoto") MultipartFile tphoto){
        System.out.println(tname);

        try {
            byte[] tphotoBytes = tphoto.getBytes();
            Trail insertBean = new Trail(tname, tphotoBytes);
//            return tService.insert(insertBean);
            return tService.saveOrUpdateTrail(insertBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @GetMapping(path = "/findById.controller/{tid}")
    public String processFindById(@PathVariable("tid") Long tid){
        Trail resultBean = tService.findById(tid);
        return resultBean.getTphotobase64();
    }


    @ResponseBody
    @PutMapping("/update.controller")
    public Trail processUpdateAction(@RequestBody Trail t){
        return tService.update(t);
    }

//    update photo
    @ResponseBody
    @PutMapping("/updatePhoto.controller")
    public Trail processUpdatePhotoAction(@RequestParam("tid") Long tid, @RequestPart("tphoto") MultipartFile tphoto) throws IOException {
        byte[] tphotoBytes = tphoto.getBytes();
        Trail updateTrail = new Trail(tid, tphotoBytes);
        return tService.saveOrUpdateTrail(updateTrail);
    }

    @ResponseBody
    @DeleteMapping("/delete.controller")
    public void processDeleteAction(@RequestBody Trail t){
        tService.delete(t);
    }


}
