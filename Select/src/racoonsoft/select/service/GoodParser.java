package racoonsoft.select.service;

import org.springframework.beans.factory.annotation.Autowired;
import racoonsoft.select.database.PGSQLDataSource;

public class GoodParser
{
    @Autowired
    private PGSQLDataSource dbProc;

    public static void parseAll()
    {

    }

    public static void parseBlancoGoods()
    {
        // Site: http://www.blanco-markt.ru/
    }
}
