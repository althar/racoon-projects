package racoonsoft.circos.structure;

import org.w3c.dom.Entity;
import racoonsoft.racoonspring.data.annotation.DataStructureField;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

import javax.ejb.EJB;

@DataStructureTable(name = "user")
public class RegistrationStructure extends DatabaseStructure
{
    @DataStructureField(name = "login")
    public String login;
    @DataStructureField(name = "password")
    public String password;
    @DataStructureField(name = "name")
    public String name;
    @DataStructureField(name = "phone")
    public String phone;
    @DataStructureField(name = "vk_id")
    public String vk_id;
    @DataStructureField(name = "confirmed")
    public Boolean confirmed = false;

    public String getLogin()
    {
        return login;
    }
    public void setLogin(String login)
    {
        this.login = login;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
