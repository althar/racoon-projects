package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.enums.EventCardType;
import racoonsoft.library.database.DBRecord;

public class EventCard extends DBRecord
{
    private EventCardType type = EventCardType.PRIZE;
}
