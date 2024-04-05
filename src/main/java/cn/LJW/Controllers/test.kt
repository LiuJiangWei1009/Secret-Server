package cn.LJW.Controllers

import cn.LJW.Entities.Net.OnlineSession
import netscape.javascript.JSObject
import org.json.JSONObject
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Controller
class test {

    companion object {
        private val sessionMap: Map<String, OnlineSession> = HashMap()
    }


    @RequestMapping(value = ["/fileUpload"])
    @ResponseBody
    fun register(file: MultipartFile): String {
        println("欢迎回地方别的地方来!!!!")
        println(file.originalFilename)
        file.transferTo(File("C:/Users/LJW/Desktop/store/${file.originalFilename}"))
        return JSONObject().apply {
            put("loadResult", true)
        }.toString()
    }
}