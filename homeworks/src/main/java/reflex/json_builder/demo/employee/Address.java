package reflex.json_builder.demo.employee;


import reflex.json_builder.JsonBuild;
import reflex.json_builder.JsonBuildField;

@JsonBuild(objectName = "address")
public class Address {
    @JsonBuildField
    private String street;
    @JsonBuildField(fieldName = "build")
    private int numberBuild;

    public Address(String street, int numberBuild) {
        this.street = street;
        this.numberBuild = numberBuild;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", numberBuild=" + numberBuild +
                '}';
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberBuild() {
        return numberBuild;
    }

    public void setNumberBuild(int numberBuild) {
        this.numberBuild = numberBuild;
    }
}