package VFS.GLOBAL.VFS_GLOBAL.service;

import VFS.GLOBAL.VFS_GLOBAL.entity.User;
import VFS.GLOBAL.VFS_GLOBAL.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepo;

    @Override
    public String createUser(User user) {
        String status = "Failed";
        String trackId = "";

        // Step 1: Get today's date in yyyyMMdd format
        String datePrefix = "";
        if (user.getExpectedDate() != null) {
            datePrefix = user.getExpectedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else {
            datePrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        String basePrefix = datePrefix + "INCDTKT";

        // Step 2: Get last tracking ID for today
        String lastTrackId = userRepo.findLastTrackIdByPrefix(basePrefix); // You need to implement this method
        System.out.println("lastTrackId: " + lastTrackId);
        int sequenceNumber = 90001; // Default start

        if (lastTrackId != null && lastTrackId.startsWith(basePrefix)) {
            String lastSequence = lastTrackId.substring(basePrefix.length());
            try {
                sequenceNumber = Integer.parseInt(lastSequence) + 1;
            } catch (NumberFormatException e) {
                // fallback to default
                sequenceNumber = 90001;
            }
        }

        trackId = basePrefix + sequenceNumber;
        System.out.println("trackId " + trackId);

        // Step 3: Save user with tracking ID
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassportNo(user.getPassportNo());
        newUser.setDob(user.getDob());
        newUser.setExpectedDate(user.getExpectedDate());
        newUser.setStatus(user.getStatus());
        newUser.setTrackingId(trackId); // Add this field in your User entity

        try {
            logger.info("Test1");
            logger.info(newUser.toString());

            userRepo.save(newUser);
            logger.info("Test2");
            status = "Inserted";
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        return trackId; // Return tracking ID instead of status
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public HashMap checkStatus(String trackId, LocalDate dob) {
        boolean isExist = userRepo.existsByTrackingId(trackId);
        HashMap hm = new HashMap();
        if (isExist) {
            Object obj = userRepo.findUserByTrackingId(trackId, dob);
            hm.put("data", obj);
            hm.put("status", true);
            System.out.println(obj.toString());
            return hm;
        } else {
            hm.put("data", "Tracking Id is invalid");
            hm.put("status", false);
            return hm;
        }

    }

}
