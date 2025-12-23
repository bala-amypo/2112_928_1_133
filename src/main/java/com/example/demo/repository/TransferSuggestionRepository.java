package com.example.demo.repository;

import com.example.demo.entity.TransferSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferSuggestionRepository extends JpaRepository<TransferSuggestion, Long> {

    @Query("FROM TransferSuggestion t WHERE t.sourceStore.id = :storeId")
    List<TransferSuggestion> findSuggestionsFromStore(@Param("storeId") Long storeId);
}
