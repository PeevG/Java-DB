import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private BufferedReader bufferedReader;

    public Engine(EntityManager entityManager) {

        this.entityManager = entityManager;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println("Please enter exercise number: ");

        try {
            int exerciseNumber = Integer.parseInt(bufferedReader.readLine());

            switch (exerciseNumber) {
                case 2 -> ExerciseTwoChangeCasing();
                case 3 -> ExerciseThreeContainsEmployee();
                case 4 -> ExerciseFourEmployeeWithSalaryOver();
                case 5 -> ExerciseFiveEmpFromDepartment();
                case 6 -> ExerciseSix();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ExerciseSix() throws IOException {
        System.out.println("Enter employee last name: ");
        String lastName = bufferedReader.readLine();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 16");

        employee.setAddress(address);

    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        return address;
    }

    private void ExerciseFiveEmpFromDepartment() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name = 'Research and Development' " +
                "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s Research and Development - %.2f%n", employee.getFirstName(), employee.getLastName(),
                            employee.getSalary());
                });


    }

    private void ExerciseFourEmployeeWithSalaryOver() {
        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
    }

    private void ExerciseThreeContainsEmployee() throws IOException {
        System.out.println("Please enter first and last name separated by space: ");

        String[] input = bufferedReader.readLine().split("\\s+");

        try {
            Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                    "WHERE e.firstName = :first_name AND e.lastName = :last_name", Employee.class)
                    .setParameter("first_name", input[0])
                    .setParameter("last_name", input[1])
                    .getSingleResult();

            System.out.println("Yes");

        } catch (NoResultException e) {
            System.out.println("No");
        }
    }

    private void ExerciseTwoChangeCasing() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t SET t.name = UPPER(t.name) WHERE length(t.name) <= 5");

        int updatedCities = query.executeUpdate();

        System.out.println(updatedCities + " cities was updated.");

        entityManager.getTransaction().commit();
    }
}















