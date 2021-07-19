import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

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
                case 7 -> ExerciseSevenEmployeeCount();
                case 8 -> ExerciseEightEmpWithProject();
                case 9 -> ExerciseNineLastTenProjects();
                case 10 -> ExerciseTen();
                case 11 -> ExerciseEleven();
                case 12 -> System.out.println("I`ll do it later maybe  :)");
                case 13 -> System.out.println("Same ...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void ExerciseThirteen() throws IOException {
//        System.out.println("Please enter town name: ");
//        String townName = bufferedReader.readLine();
//
//        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :t_name", Town.class)
//                .setParameter("t_name", townName)
//                .getSingleResult();
//
//        int affectedRows = removeAddressByTownId(town.getId());
//
//        System.out.printf("%d address in %s is deleted.",
//                affectedRows,
//                townName);
//    }
//    private int removeAddressByTownId(Integer id){
//
//        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.id = :param_id", Address.class)
//                .setParameter("param_id", id)
//                .getResultList();
//
//        entityManager.getTransaction().begin();
//        addresses.forEach(entityManager::remove);
//        entityManager.getTransaction().commit();
//
//        return addresses.size();
//    }

//    private void ExerciseTwelve() {
////        List resultList = entityManager.createNativeQuery("SELECT d.name, MAX(e.salary) AS max_salary " +
////                "FROM departments d " +
////                "JOIN employees e on d.department_id = e.department_id " +
////                "GROUP BY d.name " +
////                "HAVING max_salary NOT BETWEEN 30000 AND 70000;")
////                .getResultList();
//
////     entityManager.createQuery("SELECT e.department.name, MAX(e.salary) AS max_salary FROM Employee e " +
////                "GROUP BY e.department.name" +
////                " HAVING max_salary NOT BETWEEN 30000 AND 70000", Object[].class)
////             .getResultList()
////             .stream()
////             .forEach(obj -> {
////                 System.out.printf("%s %.2f", ...);
////             });
//
//        List<Object[]> resultList = this.entityManager
//                .createQuery("SELECT department.name, MAX(salary) " +
//                                "FROM Employee " +
//                                "GROUP BY department.name " +
//                                "HAVING MAX(salary) NOT BETWEEN 30000 AND 70000",
//                        Object[].class)
//                .getResultList();
//        //Todo: ...
//
//    }

    private void ExerciseEleven() throws IOException {
        System.out.println("Please enter input :)");
        String input = bufferedReader.readLine();

        TypedQuery<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.firstName LIKE :input_to_check", Employee.class);
        employees.setParameter("input_to_check", input + "%");

        employees.getResultList()
                .forEach(е -> {
                    System.out.printf("%s %s - %s - ($%.2f)%n", е.getFirstName(), е.getLastName(),
                            е.getJobTitle(), е.getSalary());
                });
    }

    private void ExerciseTen() {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("UPDATE Employee SET salary = salary * 1.12 WHERE department.id IN (1, 2, 4, 11)");

        query.executeUpdate();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.id IN (1, 2, 4, 11)", Employee.class)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s ($%.2f)%n", e.getFirstName(),
                            e.getLastName(), e.getSalary());
                });

        entityManager.getTransaction().commit();
    }

    private void ExerciseNineLastTenProjects() {

        entityManager.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class)
                .getResultStream()
                .limit(10)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("Project name: %s%n", project.getName());
                    System.out.printf("     Project Description: %s%n", project.getDescription());
                    System.out.println("     Project Start Date:" + project.getStartDate());
                    System.out.println("     Project End Date: " + project.getEndDate());
                });
    }

    private void ExerciseEightEmpWithProject() throws IOException {
        System.out.println("Please enter employee id: ");

        int employeeId = Integer.parseInt(bufferedReader.readLine());

        Employee employee = entityManager.find(Employee.class, employeeId);

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.println("      " + project.getName());
                });
    }

    private void ExerciseSevenEmployeeCount() {
        entityManager.createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC", Address.class)
                .getResultStream()
                .limit(10)
                .forEach(address -> {
                    System.out.printf("%s, %s - %d employees%n",
                            address.getText(), address.getTown().getName(),
                            address.getEmployees().size());
                });
        entityManager.close();
    }

    private void ExerciseSix() throws IOException {
        System.out.println("Enter employee last name: ");
        String lastName = bufferedReader.readLine();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 16");

        employee.setAddress(address);
        entityManager.close();
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















