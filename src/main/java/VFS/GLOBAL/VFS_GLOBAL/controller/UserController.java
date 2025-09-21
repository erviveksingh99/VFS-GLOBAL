package VFS.GLOBAL.VFS_GLOBAL.controller;

import VFS.GLOBAL.VFS_GLOBAL.entity.User;
import VFS.GLOBAL.VFS_GLOBAL.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
@CrossOrigin(origins = "https://vfs-global-production-827f.up.railway.app")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
       String result= userService.createUser(user);
        HashMap hm=new HashMap();
        hm.put("trackingId", result);
        hm.put("status", "User created");
        return  ResponseEntity.ok(hm);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/checkStatus")
    public ResponseEntity<?> checkStatus(@RequestParam String trackId, @RequestParam LocalDate dob) {
        return ResponseEntity.ok(userService.checkStatus(trackId, dob));
    }

    @GetMapping("/checkStatus")
    public ResponseEntity<?> checkHealth() {
        return ResponseEntity.ok("Health of spring boot app is fine!");
    }
}
