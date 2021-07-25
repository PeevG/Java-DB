package com.example.springdto.dtoexercise.service.imp;

import com.example.springdto.dtoexercise.model.dto.GameAddDto;
import com.example.springdto.dtoexercise.model.entity.Game;
import com.example.springdto.dtoexercise.repository.GameRepository;
import com.example.springdto.dtoexercise.service.GameService;
import com.example.springdto.dtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository
                .save(game);

        System.out.println("Added game " + gameAddDto.getTitle());

    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);

        if (game == null) {
            System.out.println("Invalid game id");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        this.gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public void deleteGame(long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);

        if (game == null) {
            System.out.println("Invalid game id");
            return;
        }

        this.gameRepository.delete(game);
        System.out.println("Game " + game.getTitle() + " was deleted successfully.");
    }
}
