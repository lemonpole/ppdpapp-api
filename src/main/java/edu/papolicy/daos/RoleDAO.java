package edu.papolicy.daos;

import java.util.List;
import edu.papolicy.models.Role;

public interface RoleDAO {
	public List<Role> list();
	public Role find(int id);
}
