package racoonsoft.store.settings;

import org.springframework.stereotype.Repository;
import racoonsoft.library.database.DBProcessor;

import java.sql.SQLException;

@Repository
public class PGSQLDataSource extends DBProcessor
{
    public PGSQLDataSource() throws ClassNotFoundException,SQLException
    {
        super("192.168.0.110","chaos",5432,"althar","bredokbredok2000","org.postgresql.Driver","jdbc:postgresql:");
        connect();
    }
}