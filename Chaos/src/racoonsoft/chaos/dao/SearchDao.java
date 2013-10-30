package racoonsoft.chaos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racoonsoft.chaos.entity.Fragment;
import racoonsoft.chaos.settings.PGSQLDataSource;

import java.lang.String;
import java.util.ArrayList;

@Repository
public class SearchDao
{
    @Autowired
    private PGSQLDataSource dataSource;

    public ArrayList<Fragment> searchFragments(String[] keywords,String type,String genre)
    {
        ArrayList<Fragment> result = new ArrayList<Fragment>();

        return result;
    }
}
