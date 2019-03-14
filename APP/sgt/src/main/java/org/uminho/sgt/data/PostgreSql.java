package org.uminho.sgt.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSql {

  private static final Logger logger = LogManager.getLogger(PostgreSql.class);
  private static final String POSTGRESQL_ENDPOINT =
      "uminho-dss1718-g6.cxewhvulmotm.eu-west-2.rds.amazonaws.com";
  private static final String POSTGRESQL_PORT = "5432";
  private static final String POSTGRESQL_DATABASE_NAME = "uminho_dss1718_g6";
  private static final String POSTGRESQL_DATABASE_USER = "g6";
  private static final String POSTGRESQL_DATABASE_PASSWORD = "Y+!Rfhd8pgHDr$[T";

  public static Connection connect() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://"
            + POSTGRESQL_ENDPOINT
            + ":"
            + POSTGRESQL_PORT
            + "/"
            + POSTGRESQL_DATABASE_NAME,
        POSTGRESQL_DATABASE_USER,
        POSTGRESQL_DATABASE_PASSWORD);
  }

  public static void close(Connection connection) {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }
}
