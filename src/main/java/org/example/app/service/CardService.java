package org.example.app.service;

import lombok.RequiredArgsConstructor;
import org.example.app.domain.Card;
import org.example.app.repository.CardRepository;

import java.util.List;

@RequiredArgsConstructor
public class CardService {
  private final CardRepository cardRepository;

  public List<Card> getAllByOwnerId(long ownerId) {
    return cardRepository.getAllByOwnerId(ownerId);
  }
}
