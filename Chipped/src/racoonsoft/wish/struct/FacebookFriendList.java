package racoonsoft.wish.struct;

import java.util.ArrayList;
import java.util.HashMap;

public class FacebookFriendList
{
    public ArrayList<FacebookFriend> friends = new ArrayList<FacebookFriend>();
    public HashMap<String,FacebookFriend> friendsMap = new HashMap<String,FacebookFriend>();
    public void addFriend(String id,String name)
    {
        FacebookFriend f = new FacebookFriend(id,name);
        friends.add(f);
        friendsMap.put(id,f);
    }
    public ArrayList<FacebookFriend> getFriends()
    {
        return friends;
    }
    public void setFriends(ArrayList<FacebookFriend> friends)
    {
        this.friends = friends;
    }
    public FacebookFriend getFriend(String facebook_id)
    {
        return friendsMap.get(facebook_id);
    }
}