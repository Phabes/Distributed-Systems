package server.servants;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class SprinklerI extends DeviceI implements Sprinkler {
    private SprinklerSettings sprinklerSettings;

    public SprinklerI(String name, SprinklerSettings sprinklerSettings) {
        super(name);
        this.sprinklerSettings = sprinklerSettings;
    }

    @Override
    public Intensity getIntensity(Current current) {
        System.out.println("Getting sprinkler (" + current.id.name + " " + current.id.category + ") intensity");
        return sprinklerSettings.intensity;
    }

    @Override
    public double getBespatteringTime(Current current) {
        System.out.println("Getting sprinkler (" + current.id.name + " " + current.id.category + ") bespattering time");
        return sprinklerSettings.bespatteringTime;
    }

    @Override
    public SprinklerSettings getSettings(Current current) {
        System.out.println("Getting sprinkler (" + current.id.name + " " + current.id.category + ") settings");
        return sprinklerSettings;
    }

    @Override
    public void setSettingsAndWorkingState(SprinklerSettings sprinklerSettings, WorkingState workingState, Current current) throws BespatteringTimeError, IntesityError {
        System.out.println("Changing sprinkler (" + current.id.name + " " + current.id.category + ") settings");
        if (sprinklerSettings.bespatteringTime < 1 || sprinklerSettings.bespatteringTime > 10)
            throw new BespatteringTimeError();
        if (sprinklerSettings.intensity.value() < 0 || sprinklerSettings.intensity.value() > 2)
            throw new IntesityError();
        this.sprinklerSettings = sprinklerSettings;
        setWorkingState(workingState, current);
    }
}
