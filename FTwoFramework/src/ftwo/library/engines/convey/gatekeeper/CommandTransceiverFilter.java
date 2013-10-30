package ftwo.library.engines.convey.gatekeeper;

import ftwo.library.engines.convey.commandstorage.Command;

public abstract class CommandTransceiverFilter
{
    public abstract Command filter(Command command);
}
