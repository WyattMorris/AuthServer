package appUser.stockdata;

import appUser.appuser.AppUser;
import appUser.appuser.AppUserRepository;
import appUser.registration.AuthorizationRequest;
import appUser.registration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public StockData getSingleStock(String ticker, Float amount) {
        return new StockData(ticker, amount);
    }

    public List<StockData> getMultipleStocks(String[] tickers) {
        List<StockData> myList = new ArrayList<>();

        Random rand = new Random();

        for (String ticker : tickers) {
            int amount = rand.nextInt(10) + 1;
            myList.add(new StockData(ticker, (float)amount));
        }

        return myList;
    }

    public void saveStocks(StockEntity[] stocks, AuthorizationRequest authorization) {
        String token = authorization.getToken().substring(7);
        String username = jwtUtil.extractUsername(token);

        //Extracting token and username
        Optional<AppUser> appUser = appUserRepository.findByEmail(username);
        Long userId;

        if(appUser.isPresent()){
            userId = appUser.get().getId();
            stockRepository.deleteByUserIDEquals(userId);
            //Deleting previous stock objects. Want to change this to a put/patch
            for (StockEntity i : stocks){
                stockRepository.save(new StockEntity(userId, i.getTicker(), i.getAmount()));
            }
        } else {
            throw new UsernameNotFoundException("Username was not found");
        }

    }

    public List<StockData> getStocks(AuthorizationRequest authorization){
        String token = authorization.getToken().substring(7);
        String username = jwtUtil.extractUsername(token);

        //Extract token and then username from the token

        Optional<AppUser> appUser = appUserRepository.findByEmail(username);

        if(appUser.isPresent()){
            Long userId = appUser.get().getId();
            List<StockEntity> entries = stockRepository.findAllByUserID(userId);

            //Collect all entries from the user

            List<StockData> myList = new ArrayList<>();
            for(StockEntity i : entries){
                myList.add(new StockData(i.getTicker(), i.getAmount()));
            }

            //Create array of stock objects
            return myList;
        }

        return null;
    }


}
