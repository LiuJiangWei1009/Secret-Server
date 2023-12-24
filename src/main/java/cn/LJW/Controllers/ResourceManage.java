package cn.LJW.Controllers;

import cn.LJW.Entities.Resource.Resource;
import cn.LJW.MyDispatchServlet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ResourceManage extends MyDispatchServlet {

    @RequestMapping(value = "/Upload")
    @ResponseBody
    public synchronized void Upload(Resource resource, MultipartFile file){
        System.out.println(System.currentTimeMillis()+" : "+resource);
        try {
            file.transferTo(new File("C:\\Users\\LJW\\Desktop\\a\\"+file.getOriginalFilename()));
            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
