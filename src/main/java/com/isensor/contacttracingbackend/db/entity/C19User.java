package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "c19_user")
public class C19User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "email")
    public String email;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "password")
    public String password;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "gender")
    public String gender;

    @Column(name = "date_of_birth")
    public String dateOfBirth;

    @Column(name = "address_line_1")
    public String addressLine1;

    @Column(name = "address_line_2")
    public String addressLine2;

    @Column(name = "city")
    public String city;

    @Column(name = "county")
    public String county;

    @Column(name = "state")
    public String state;

    @Column(name = "zipcode")
    public Integer zipcode;

    @Column(name = "country")
    public String country;

    public C19User() {
    }

    public C19User(Long id, String email, String phoneNumber, String password, String firstName, String middleName, String lastName, String gender, String dateOfBirth, String addressLine1, String addressLine2, String city, String county, String state, Integer zipcode, String country) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.firstName != null )stringBuilder.append(this.firstName);
        if(this.middleName != null ) {
            stringBuilder.append(" ");
            stringBuilder.append(this.middleName);
        }
        if(this.lastName != null ){
            stringBuilder.append(" ");
            stringBuilder.append(this.lastName);
        }
        return stringBuilder.toString();
    }

    public String getFullAddress() {
        return this.addressLine1 + ", " + this.addressLine2 + ", " + this.city + ", " + this.state + "-" + this.zipcode + ", " + this.country;
    }

    public Integer calculateAgeFromDOB(){
        int month = Integer.parseInt(dateOfBirth.split("-")[0]);
        int day = Integer.parseInt(dateOfBirth.split("-")[1]);
        int year = Integer.parseInt(dateOfBirth.split("-")[2]);
        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "C19User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", country='" + country + '\'' +
                '}';
    }
}

