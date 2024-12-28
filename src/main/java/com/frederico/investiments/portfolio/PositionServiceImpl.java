package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    @Transactional
    public Position save(Position position){
        return positionRepository.save(position);
    }
}
