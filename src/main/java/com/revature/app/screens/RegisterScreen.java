package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import com.revature.app.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterServices router;
    private final UserService userService;
    private SessionUtil session;
    private static final Logger log = LogManager.getLogger(RegisterScreen.class);

    @Override
    public void start(Scanner scan) {
        log.info("navigated to register screen");
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Register an account");
            System.out.println("[b] Back ");
            System.out.println("[x] Back to main menu");

            log.info("validating user input for username");
            username = getUsername(scan);

            if(username.equalsIgnoreCase("x")){
                log.info("navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(username.equalsIgnoreCase("b")){
                log.info("navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("validating user input for username");
            password = getPassword(scan);

            if(password.equalsIgnoreCase("x")){
                log.info("navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(password.equalsIgnoreCase("b")){
                log.info("navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("inserting new user data");
            userService.register(username, password);
            log.info("logging user in");
            Optional<User> user = userService.login(username, password);
            session.setSession(user.get());
            log.info("navigating to main menu screen");
            session.getScreenHistory().push("/register");
            router.navigate("/menu", scan);
            break;
            
        }
    }

    public String getUsername(Scanner scan){
        String username = "";
        while(true){
            clearScreen();
            System.out.println("\nEnter a username: ");
            username = scan.nextLine();

            if(username.equalsIgnoreCase("x")){
                return "x";
            }

            if(username.equalsIgnoreCase("b")){
                return "b";
            }
            if (!userService.isValidUserName(username)) {
                clearScreen();
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!userService.isUniqueUserName(username)) {
                clearScreen();
                System.out.println("Username already in use!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
            
        }
        return username;
    }

    public String getPassword(Scanner scan){
        String password = "";
        String confirmPassword = "";
        
        while(true){
            clearScreen();
            System.out.println("\nEnter a password: ");
            password = scan.nextLine();

            if(password.equalsIgnoreCase("x")){
                return "x";
            }

            if(password.equalsIgnoreCase("b")){
                return "b";
            }

            if (!userService.isValidPassword(password)) {
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            System.out.println("\nPlease confirm password: ");
            confirmPassword = scan.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }

            if(confirmPassword.equalsIgnoreCase("b")){
                return "b";
            }

            if (!userService.isSamePassword(password, confirmPassword)) {
                clearScreen();
                System.out.println("Passwords do not match");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }


            break;
        }
        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}