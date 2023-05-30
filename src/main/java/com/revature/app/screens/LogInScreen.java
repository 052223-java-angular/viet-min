package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.models.User;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogInScreen implements IScreen{
    private final RouterServices router;
    private final UserService userService;
    private SessionUtil session;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Sign in here");
            System.out.println("[b] back");
            System.out.println("[x] Back to main menu");

            username = getUsername(scan);

            if(username.equals("x")){
                router.navigate("/home", scan);
                break;
            }

            if(username.equals("b")){
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            password = getPassword(scan);

            if(password.equals("x")){
                router.navigate("/home", scan);
                break;
            }

            if(password.equals("b")){
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }
            Optional<User> user = userService.login(username, password);
            if(user.isEmpty()){
                clearScreen();
                System.out.println("\nNo user found with that combination of username and password found");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }else{
                session.setSession(user.get());
                System.out.println("success!");
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