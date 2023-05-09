package server;

import SmartHome.Intensity;
import SmartHome.SprinklerSettings;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;
import server.servants.*;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeServantLocator implements ServantLocator {
    public List<String> devices;

    public SmartHomeServantLocator() {
        this.devices = new ArrayList<>();
    }

    @Override
    public LocateResult locate(Current current) throws UserException {
        String name = current.id.name;
        String category = current.id.category;
        System.out.println("name: " + name + " category: " + category);

        ObjectAdapter adapter = current.adapter;

        switch (name) {
            case "insideTemp" -> {
                TemperatureSensorI temperatureServant1 = new TemperatureSensorI(name, 13.4);
                adapter.add(temperatureServant1, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(temperatureServant1, null);
            }
            case "outsideTemp" -> {
                TemperatureSensorI temperatureServant2 = new TemperatureSensorI(name, 24.8);
                adapter.add(temperatureServant2, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(temperatureServant2, null);
            }
            case "gardenHum" -> {
                HumiditySensorI humidityServant1 = new HumiditySensorI(name, 40);
                adapter.add(humidityServant1, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(humidityServant1, null);
            }
            case "potHum" -> {
                HumiditySensorI humidityServant2 = new HumiditySensorI(name, 50);
                adapter.add(humidityServant2, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(humidityServant2, null);
            }
            case "gardenSprinkler" -> {
                SprinklerSettings sprinklerSettings = new SprinklerSettings(Intensity.Medium, 4);
                SprinklerI sprinklerServant1 = new SprinklerI(name, sprinklerSettings);
                adapter.add(sprinklerServant1, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(sprinklerServant1, null);
            }
            case "kitchenLight", "bedroomLight" -> {
                LightI lightServant1 = new LightI(name);
                adapter.add(lightServant1, new Identity(name, category));
                devices.add(name + " " + category);
                return new LocateResult(lightServant1, null);
            }
            case "connectedDevices" -> {
                ConnectedDevicesI connectedDevicesServant = new ConnectedDevicesI(devices);
                adapter.add(connectedDevicesServant, new Identity(name, category));
                return new LocateResult(connectedDevicesServant, null);
            }
            default -> throw new Error("Invalid name");
        }

    }

    @Override
    public void finished(Current current, Object object, java.lang.Object o) throws UserException {

    }

    @Override
    public void deactivate(String s) {

    }

    public void printDevices() {
        for (String device : devices)
            System.out.println(device);
    }
}
