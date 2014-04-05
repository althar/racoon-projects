package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.enums.EventCardType;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.database.DBRecord;

public class EventCard extends DBRecord
{
    public EventCard(EventCardType type)
    {
        this.event_card_type = type;
    }
    @DataStructureField(name="event_card_type")
    public EventCardType event_card_type = EventCardType.SELL_COMPANY;

    @DataStructureField(name="accepted")
    public Boolean accepted = false;

    @DataStructureField(name="event_card_value")
    public Integer event_card_value = 0;

    @DataStructureField(name="current_turn")
    public Boolean current_turn = false;
    @DataStructureField(name="previous_turn")
    public Boolean previous_turn = false;
    @DataStructureField(name="pre_previous_turn")
    public Boolean pre_previous_turn = false;
}
