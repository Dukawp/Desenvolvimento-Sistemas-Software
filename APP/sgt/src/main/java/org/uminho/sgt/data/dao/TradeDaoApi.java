package org.uminho.sgt.data.dao;

import org.uminho.sgt.business.trocas.PedidoTroca;
import org.uminho.sgt.business.trocas.TrocaNormal;

import java.util.List;
import java.util.Optional;

public interface TradeDaoApi {
  /**
   * Fetch currently direct trades propositions from database.
   *
   * @return List of direct trades, wrapped into TrocaNormal objects.
   */
  List<TrocaNormal> getDirectTrades();

  /**
   * Fetch currently trades by request propositions from database.
   *
   * @return List of trades by request, wrapped into PedidoTroca objects.
   */
  List<PedidoTroca> getTradesByRequest();

  /**
   * Registers a new direct trade, if params are valid.
   *
   * @param student Student who wants to exchange shift.
   * @param intendedShift Student intended shift.
   * @param intendedSubject Subject to which the provided shift is associated with.
   */
  void addDirectTrade(final String student, final String intendedShift, final String intendedSubject);

  /**
   * Registers a new trade by request, if params are valid.
   *
   * @param fromStudent Student who wants to exchange shift.
   * @param toStudent Student who will be addressed to exchange shift.
   * @param subject Subject to exchange shifts.
   */
  void addTradeByRequest(final String fromStudent, final String toStudent, final String subject);

  /**
   * Deletes a direct trade request, if exists.
   *
   * @param student Student who asked for direct trade.
   * @param subject Subject related to student direct trade request.
   */
  void deleteDirectTrade(final String student, final String subject);

  /**
   * Deletes a trade by request proposition, if exists.
   *
   * @param student The student who asked for the trade.
   * @param subject The trade-related subject.
   */
  void deleteTradeByRequest(final String student, final String subject);

  /**
   * Gets the intended shift related to a direct trade request.
   *
   * @param student The student who asked for the trade.
   * @param subject The trade-related subject.
   * @return An optional string, containing either the intended shift code or an empty (if trade
   *     does not exist).
   */
  Optional<String> getDirectTradeIntendedShift(final String student, final String subject);

  /**
   * Gets the student who was asked for an exchange.
   *
   * @param fromStudent The student who asked for the exchange.
   * @param subject The subject related to trade request.
   * @return An optional string, containing either the addressed student or an empty (if request
   *     with provided params does not exist)
   */
  Optional<String> getTradeByRequestStudentDestination(final String fromStudent, final String subject);
}
