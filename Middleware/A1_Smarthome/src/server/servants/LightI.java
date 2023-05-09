package server.servants;

import SmartHome.Light;

public class LightI extends DeviceI implements Light {
    public LightI(String name) {
        super(name);
    }
}
