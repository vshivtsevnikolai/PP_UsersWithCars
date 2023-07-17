package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Max", "Payne", "feelpain@gmail.com");
      User user2 = new User("James", "Bond", "007@gmail.com");
      User user3 = new User("Vlad", "Pulling", "notpresident@mail.ru");
      User user4 = new User("SomeUser", "Just", "somemail@gmail.com");

      Car car1 = new Car("Ford", 211);
      Car car2 = new Car("Aston Martin", 007);
      Car car3 = new Car("Lada", 777);
      Car car4 = new Car("SomeCar", 101);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }


      try {
         System.out.println(userService.getUserByCar("SomCar", 101));
      } catch (NoResultException e) {
         System.out.println("User not found!");
      }

      context.close();
   }
}
