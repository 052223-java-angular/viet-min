package com.revature.app;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;


public class eShop {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);

    RouterServices route = new RouterServices(new SessionUtil());
    route.navigate("/cart", scan);
    scan.close();
  }
}