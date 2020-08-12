package ai.brace;

import java.util.Objects;

/**
 * Entity representing an employee
 */
public class Employee {
    public String firstName;
    public String middleInitial;
    public String lastName;
    public String socialSecurityNumber;

    public Employee(String lastName, String firstName, String middleInitial, String socialSecurityNumber) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                middleInitial.equals(employee.middleInitial) &&
                lastName.equals(employee.lastName) &&
                socialSecurityNumber.equals(employee.socialSecurityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleInitial, lastName, socialSecurityNumber);
    }
}
