package Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {



    @GetMapping
    public String getRuns(@RequestParam(value = "userID", defaultValue = "") String userID) {
        return null;
    }




}
