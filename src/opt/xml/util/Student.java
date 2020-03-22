package opt.xml.util;

import java.io.Serializable;

/**
 * 2012-09-07 13:13 a java bean for simple test
 *
 * @author Randy
 */
public class Student implements Serializable {
  private static final long serialVersionUID = 1534645768679679L;
  private String name;
  private int age;
  private String address;
  private String phoneNumber;

  public Student(String name, int age, String address, String phoneNumber) {
    super();
    this.name = name;
    this.age = age;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "Student [name="
        + name
        + ", age="
        + age
        + ", address="
        + address
        + ", phoneNumber="
        + phoneNumber
        + "]";
  }
}
