package Idao;

import java.util.List;

import model.Guitar;

public interface InventoryIDao {
	
	public List<Guitar> searchGuitar(Guitar  Guitar) throws Exception;
	public boolean findAll() throws Exception;
}
