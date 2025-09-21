package VFS.GLOBAL.VFS_GLOBAL.repository;

import VFS.GLOBAL.VFS_GLOBAL.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    @Query("SELECT u.trackingId FROM User u WHERE u.trackingId LIKE :prefix% ORDER BY u.trackingId DESC LIMIT 1")
    String findLastTrackIdByPrefix(@Param("prefix") String prefix);

    @Query(value = "SELECT status, tracking_id, name, expected_date " + "FROM vfs_global.user " + "WHERE tracking_id = :trackingId AND dob = :dob", nativeQuery = true)
    Object findUserByTrackingId(@Param("trackingId") String trackingId, @Param("dob") LocalDate dob);

    boolean existsByTrackingId(String trackingId);

}
