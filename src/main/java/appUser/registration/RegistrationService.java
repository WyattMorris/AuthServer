package appUser.registration;

import appUser.appuser.AppUser;
import appUser.appuser.AppUserRole;
import appUser.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signUp(RegistrationRequest request) {

        appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));

        //Should return a JWT token on successful login
        return jwtUtil.generateToken(request.getEmail(), request.getFirstName(), request.getLastName());
    }

    public String logIn(RegistrationRequest request) {
        try {
            //Gather the user details of the user from their username
            AppUser User = appUserService.loadUserByUsername(request.getEmail());

            //Check to see if the request password and held password match
            if(!bCryptPasswordEncoder.matches(request.getPassword(), User.getPassword())){
                throw new CredentialException("Invalid Credentials");
            }

            //Need to return a JWT token
            return jwtUtil.generateToken(request.getEmail(), User.getFirstName(), User.getLastName());

        } catch(Exception e){
            throw new UsernameNotFoundException("Username not found");
        }

    }


}
