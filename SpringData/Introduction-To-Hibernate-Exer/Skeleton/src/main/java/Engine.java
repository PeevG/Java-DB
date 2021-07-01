import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable{

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
            }

        } catch (IOException e) {
            e.printStackTrace();
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















