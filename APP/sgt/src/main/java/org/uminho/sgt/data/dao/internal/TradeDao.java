package org.uminho.sgt.data.dao.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uminho.sgt.business.trocas.PedidoTroca;
import org.uminho.sgt.business.trocas.TrocaNormal;
import org.uminho.sgt.data.PostgreSql;
import org.uminho.sgt.data.dao.TradeDaoApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TradeDao implements TradeDaoApi {
  private static final Logger logger = LogManager.getLogger(TradeDao.class);
  private Connection connection;

  public List<TrocaNormal> getDirectTrades() {
    List<TrocaNormal> directTrades = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_direct_trades()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        directTrades.add(
            new TrocaNormal(
                rows.getString("student"),
                rows.getString("trade_state"),
                rows.getString("intended_shift"),
                rows.getString("intended_subject")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return directTrades;
    }
  }

  public List<PedidoTroca> getTradesByRequest() {
    List<PedidoTroca> tradesByRequest = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_trades_by_request()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        tradesByRequest.add(
            new PedidoTroca(
                rows.getString("from_student"),
                rows.getString("to_student"),
                rows.getString("subject"),
                rows.getString("trade_state")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return tradesByRequest;
    }
  }

  public void addDirectTrade(
      final String student, final String intendedShift, final String intendedSubject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_direct_trade(?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student);
      statement.setString(2, intendedShift);
      statement.setString(3, intendedSubject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void addTradeByRequest(
      final String fromStudent, final String toStudent, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_trade_by_request(?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, fromStudent);
      statement.setString(2, toStudent);
      statement.setString(3, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteDirectTrade(final String student, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_direct_trade(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student);
      statement.setString(2, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteTradeByRequest(final String student, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_trade_by_request(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student);
      statement.setString(2, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public Optional<String> getDirectTradeIntendedShift(final String student, final String subject) {
    Optional<String> intendedShift = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_direct_trade_intended_shift(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student);
      statement.setString(2, subject);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        intendedShift = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return intendedShift;
    }
  }

  public Optional<String> getTradeByRequestStudentDestination(
      final String fromStudent, final String subject) {
    Optional<String> toStudent = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_trade_by_request_student_destination(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, fromStudent);
      statement.setString(2, subject);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        toStudent = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return toStudent;
    }
  }
}
