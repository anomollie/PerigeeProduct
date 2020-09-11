import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Device {
    private SimpleStringProperty deviceName;
    private SimpleStringProperty ipAddress;
    private SimpleStringProperty deviceLocation;
    private SimpleDoubleProperty deviceAvailability;
    private SimpleStringProperty deviceAvailabilityDisplay;
    private SimpleDoubleProperty deviceHygiene;
    private SimpleStringProperty deviceHygieneDisplay;
    private SimpleStringProperty lastThreatBlockedDate;
    private final String percent = "%";

    public Device(String name, String ip, String location, Double availability, Double hygiene, String lastThreatDate) {
        this.deviceName = new SimpleStringProperty(name);
        this.ipAddress = new SimpleStringProperty(ip);
        this.deviceLocation = new SimpleStringProperty(location);
        this.deviceAvailability = new SimpleDoubleProperty(availability);
        this.deviceAvailabilityDisplay = new SimpleStringProperty(availability + percent);
        this.deviceHygiene = new SimpleDoubleProperty(hygiene);
        this.deviceHygieneDisplay = new SimpleStringProperty(hygiene + percent);
        this.lastThreatBlockedDate = new SimpleStringProperty(lastThreatDate);

    }

    public String getIpAddress() {
        return ipAddress.get();
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public String getDeviceLocation() {
        return deviceLocation.get();
    }

    public Double getDeviceAvailability() {
        return deviceAvailability.get();
    }

    public String getDeviceAvailabilityDisplay() {
        return deviceAvailabilityDisplay.get();
    }

    public SimpleDoubleProperty getDeviceAvailabilityProperty() {
        return this.deviceAvailability;
    }

    public Double getDeviceHygiene() {
        return deviceHygiene.get();
    }

    public String getDeviceHygieneDisplay() {
        return deviceHygieneDisplay.get();
    }

    public String getLastThreatBlockedDate() {
        return lastThreatBlockedDate.get();
    }

    public void increaseDeviceAvailability(int timeIncrement) {
        this.deviceAvailability.setValue(this.deviceAvailability.getValue() + timeIncrement);
        this.deviceAvailabilityDisplay.setValue(this.deviceAvailability.getValue() + percent);
    }


}
