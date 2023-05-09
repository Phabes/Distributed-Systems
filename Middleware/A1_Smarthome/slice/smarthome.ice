#ifndef SMARTHOME_ICE
#define SMARTHOME_ICE

module SmartHome {
    enum WorkingState {
        Off = 0,
        On = 1,
        Broken = 2
    };

    interface Device {
        idempotent string getName();
        idempotent WorkingState getWorkingState();
        idempotent void toogleWorkingState();
        idempotent void setWorkingState(WorkingState workingState);
    };

    interface Sensor extends Device {
        idempotent int getChargeState();
    };

    exception DataRetrievingError {};

    interface TemperatureSensor extends Sensor {
        idempotent double getTemperature() throws DataRetrievingError;
    };

    interface HumiditySensor extends Sensor {
        idempotent int getHumidity() throws DataRetrievingError;
    };

    enum Intensity {
        Low = 0,
        Medium = 1,
        High = 2
    };

    struct SprinklerSettings {
        Intensity intensity;
        double bespatteringTime;
    };

    exception BespatteringTimeError {};

    exception IntesityError {};

    interface Sprinkler extends Device {
        idempotent Intensity getIntensity();
        idempotent double getBespatteringTime();
        idempotent SprinklerSettings getSettings();
        idempotent void setSettingsAndWorkingState(SprinklerSettings sprinklerSettings, WorkingState workingState) throws BespatteringTimeError, IntesityError;
    };

    interface Light extends Device {
    };

    sequence<string> Devices;

    interface ConnectedDevices {
        idempotent Devices getConnectedDevices();
    };
};

#endif