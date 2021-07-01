import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transaction;
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
                case 3 -> ExerciseThree3ContainsEmployee();
                case 4 -> ExerciseFourEmployeeWithSalaryOver();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ExerciseFourEmployeeWithSalaryOver() {

    }

    private void ExerciseThree3ContainsEmployee() throws IOException {
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















