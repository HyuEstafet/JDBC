package daos;

/*
- DAO = Data Access Object
- DaoInterface consists of the methods that the CustomerDao or any other DAO class can implement.
- The added sign <T> makes it possible for this interface to be implemented by different classes.
- T = type parameter of the DaoInterface
 */

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {

    // Saves the record to the database
    void save(T object) throws SQLException;

    // Updates the record in the database
    void update(int id, String email) throws SQLException;

    // Deletes the record from the database by id
   void delete(int id) throws SQLException;

    // Deletes all records in the table
    void deleteAll() throws SQLException;

    // Returns a random record id
    T getRandomId() throws SQLException;

    // Returns a list of X random records' ids
    List<Integer> getRandomIds(int randomCount) throws SQLException;

    // Get the count of all records in the table
    int getRecordsCount() throws SQLException;

    // Extract a single object from the database by id
    T getById(int id) throws SQLException;

    // Extract a list of objects from the database by a List of ids
    List<T> getByIds(List<Integer> ids) throws SQLException;

}
