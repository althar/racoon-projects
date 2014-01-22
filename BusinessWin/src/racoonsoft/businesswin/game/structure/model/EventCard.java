package racoonsoft.businesswin.game.structure.model;

import racoonsoft.businesswin.game.structure.enums.EventCardType;
import racoonsoft.library.database.DBRecord;

public class EventCard extends DBRecord
{
    private EventCardType type = EventCardType.PRIZE;
}
