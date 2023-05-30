package com.revature.app;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class eShop {
  private static final Logger log = LogManager.getLogger(eShop.class);
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    log.info("Start eShop app");

    Scanner scan = new Scanner(System.in);

    RouterServices route = new RouterServices(new SessionUtil(), null);
    route.navigate("/home", scan);

    scan.close();
    log.info("End eShop app");
  }
}