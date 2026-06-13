package carrentalsystem;

public class Customer {
    private final String customerId;
    private final String name;
    private final String contactInfo;
    private final String driversLicenseNumber;

    public Customer(String customerId, String name, String contactInfo, String driversLicenseNumber) {
        this.customerId=customerId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
