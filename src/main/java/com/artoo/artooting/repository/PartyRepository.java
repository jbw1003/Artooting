package com.artoo.artooting.repository;

import com.artoo.artooting.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
