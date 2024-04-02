package com.artoo.artooting.repository;

import com.artoo.artooting.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtooRepository extends JpaRepository<Party, Long> {
}
