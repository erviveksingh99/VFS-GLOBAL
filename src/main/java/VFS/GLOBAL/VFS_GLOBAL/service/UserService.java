package VFS.GLOBAL.VFS_GLOBAL.service;

import VFS.GLOBAL.VFS_GLOBAL.entity.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    public String createUser(User user);

    public List<User> getAllUsers();

    public HashMap checkStatus(String trackId, LocalDate dob);
}
