package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.models.User;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogInScreen implements IScreen{
    private final RouterServices router;
    private final UserService userService;
    private SessionUtil session;
    private static final Logger log = LogManager.getLogger(LogInScreen.class);

    @Override
    public void start(Scanner scan) {
        log.info("navigated to login screen");
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Sign in here");
            System.out.println("[b] back");
            System.out.println("[x] Back to main menu");

            log.info("User input a username");
            username = getUsername(scan);

            if(username.equalsIgnoreCase("x")){
                log.info("Navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(username.equalsIgnoreCase("b")){
                log.info("Navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("User input a password");
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
            Optional<User> user = userService.login(username, password);
            if(user.isEmpty()){
                clearScreen();
                log.warn("no user pass found in database");
                System.out.println("\nNo user found with that combination of username and password found");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }else{
                log.info("User logged in, session created");
                session.setSession(user.get());
                System.out.println("success!");
                log.info("navigating to main menu screen screen");
                session.getScreenHistory().push("/login");
                router.navigate("/menu", scan);
                break;
            }
            
        }
    }

    public String getUsername(Scanner scan){
        String username = "";

        System.out.println("\nEnter your username: ");
        username = scan.nextLine();

        return username.equalsIgnoreCase("x") ? "x" : username.equalsIgnoreCase("b") ? "b" : username;
    }

    public String getPassword(Scanner scan){
        String password = "";
        
        System.out.println("\nEnter your password: ");
        password = scan.nextLine();

        return password.equalsIgnoreCase("x") ? "x" : password.equalsIgnoreCase("b") ? "b" : password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}