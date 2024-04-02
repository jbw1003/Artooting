package com.artoo.artooting.service;

import com.artoo.artooting.repository.ArtooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtooService {
    @Autowired
    private ArtooRepository artooRepository;

    // CRUD관련 만들 자리
}
