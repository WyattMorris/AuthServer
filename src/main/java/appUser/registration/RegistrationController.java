package appUser.registration;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin
    @PostMapping(path = "/registration/signup")
    public String signUp(@RequestBody RegistrationRequest request) {
        return registrationService.signUp(request);
    }
    //Create the user, after the user is made, it should authenticate the user and return a token

//    get the user credentials, authenticate them, return the token back to them.
    @CrossOrigin
    @PostMapping(path = "/registration/login")
    public String login(@RequestBody RegistrationRequest request) {
        return registrationService.logIn(request);
    }

}
