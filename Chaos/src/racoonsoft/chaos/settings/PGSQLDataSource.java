package racoonsoft.chaos.settings;

import org.springframework.stereotype.Repository;
import racoonsoft.chaos.service.FileHandler;
import racoonsoft.library.database.DBProcessor;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Repository
public class PGSQLDataSource extends DBProcessor
{
    public PGSQLDataSource() throws ClassNotFoundException,SQLException
    {
        super("198.211.117.180","chaos",5432,"althar","bredokbredok2000","org.postgresql.Driver","jdbc:postgresql:");
        connect();
        FileHandler.dataSource = this;
    }
}