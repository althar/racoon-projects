package racoonsoft.tinyheroes.model;

import racoonsoft.library.settings.Settings;

import java.util.Date;

public class TimeMachine
{
    private long last_tick;
    private boolean started = false;
    private long tick_duration;

    public TimeMachine()
    {
        this.tick_duration = Settings.getLongSetting("tick_duration");
    }
    public void start()
    {
        started = true;
        last_tick = new Date().getTime();
    }
    public void stop()
    {
        started = false;
    }

    public synchronized long waitForTicks(int ticks) throws Exception
    {
        if(started)
        {
            long now = 0;
            while((now=(new Date().getTime()))-last_tick<(tick_duration*ticks))
            {
                Thread.sleep(1);
            }
            long passed = now-last_tick;
            last_tick = now;
            return passed;
        }
        throw new Exception("Time machine not started");
    }
    public synchronized long waitForTick() throws Exception
    {
        return waitForTicks(1);
    }
}
