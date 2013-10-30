package racoonsoft.wish.struct;

import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

public class FacebookFriendWishList
{
    private ArrayList<DBRecord> wishes = new ArrayList<DBRecord>();
    private String facebookId,name;

    public FacebookFriendWishList(String facebookId,String name)
    {
        this.facebookId = facebookId;
        this.name = name;
    }
    public void addWish(DBRecord wish)
    {
        wishes.add(wish);
    }
    public ArrayList<DBRecord> getWishes()
    {
        return wishes;
    }
    public void setWishes(ArrayList<DBRecord> wishes)
    {
        this.wishes = wishes;
    }
    public String getFacebookId()
    {
        return facebookId;
    }
    public void setFacebookId(String facebookId)
    {
        this.facebookId = facebookId;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}
