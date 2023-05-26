package com.revature.app;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.app.services.RouterServices;


public class eShop {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);

    RouterServices route = new RouterServices();
    route.navigate("/browse", scan);
    scan.close();
  }
}