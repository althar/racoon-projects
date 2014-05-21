package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DatabaseService
{
    @Autowired
    private DBProcessor dbProc;

    public void transaction(String type,String status,Long user_id, Double amount) throws Exception
    {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("type",type);
        map.put("status",status);
        map.put("user_id",user_id);
        map.put("amount",amount);
        dbProc.executeInsert("transaction",map);
    }

    public void synchronize() throws Exception
    {
        DBProcessor mysql = new DBProcessor("teploclubknauf.ru","teploclub",3306,"teploclubknauf","Mg=v7bUTpGU3KL","com.mysql.jdbc.Driver","jdbc:mysql:");
        mysql.connect();

        //1. Select all the users
        ArrayList<DBRecord> users = mysql.getRecords("SELECT * FROM site_users");

        //2. Create each user and give him a role

        for(DBRecord user:users)
        {
            try
            {
                Integer id = user.getIntValue("user_id");
                DBRecord userDat = mysql.getRecord("SELECT * FROM site_user_forms WHERE user_id="+id);

                HashMap<String,Object> pars = new HashMap<String, Object>();
                pars.put("login", user.getStringValue("user_email"));
                pars.put("password", "");
                pars.put("first_name", userDat.getStringValue("first_name"));
                pars.put("last_name",userDat.getStringValue("last_name"));
                if(userDat.getStringValue("gender")!=null&&!userDat.getStringValue("gender").equalsIgnoreCase(""))
                {
                    pars.put("gender",userDat.getStringValue("gender").toUpperCase().substring(0,1));
                }
                pars.put("birthday",new SimpleDateFormat("dd.MM.yyyy").parse(userDat.getStringValue("dob")));
                pars.put("phone",userDat.getStringValue("phone"));
                pars.put("company",userDat.getStringValue("employer"));
                pars.put("address",userDat.getStringValue("employer_address"));
                //pars.put("",userDat.getStringValue("employer_phone"));
                pars.put("post",userDat.getStringValue("position"));

                pars.put("ozon_id",user.getStringValue("ozon_id"));
                pars.put("ozon_login",user.getStringValue("ozon_email"));

                pars.put("sale_how_long",userDat.getStringValue("sale_how_long").replace(", ","|"));
                pars.put("sale_what",userDat.getStringValue("sale_what").replace(", ", "|"));
                pars.put("sale_whom",userDat.getStringValue("sale_whom").replace(", ", "|"));
                pars.put("sale_brand",userDat.getStringValue("sale_brand").replace(", ", "|"));
                pars.put("sale_brand_most",userDat.getStringValue("sale_brand_most").replace(", ", "|"));
                pars.put("why_sale_brand_most",userDat.getStringValue("why_sale_brand_most").replace(", ", "|"));

                Long userNewId = dbProc.executeInsert("\"user\"",pars);

                dbProc.executeNonQuery("INSERT INTO user_role (user_id,role) VALUES ("+userNewId+",'CLIENT')");
            }
            catch (Exception ex)
            {
                System.out.println(Helper.getStackTraceString(ex));
            }

        }
        String str = "";
    }
}
