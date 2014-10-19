package edu.papolicy.daos;

import edu.papolicy.models.User;
import java.util.List;

public interface UserDAO {
    public List<User> list();
	public User find(String email);
	public User save(User userObj);
}
