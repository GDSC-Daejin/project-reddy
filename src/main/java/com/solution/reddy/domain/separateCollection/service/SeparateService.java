package com.solution.reddy.domain.separateCollection.service;

import com.solution.reddy.domain.separateCollection.repository.SeparateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeparateService {
    private final SeparateRepository separateRepository;


}
