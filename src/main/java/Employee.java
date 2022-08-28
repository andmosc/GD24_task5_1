import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;

import java.lang.reflect.Type;

public class Employee {


    public long id;
    @SerializedName("Имя")
    public String firstName;
    @SerializedName("Фамилия")
    public String lastName;
    @SerializedName("Страна")
    public String country;
    @SerializedName("Возраст")
    public int age;
//для парсинга из CSV
    public Employee() {

    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                ", firstName: " + firstName + '\'' +
                ", lastName: " + lastName + '\'' +
                ", country: " + country + '\'' +
                ", age: " + age;
    }

}
