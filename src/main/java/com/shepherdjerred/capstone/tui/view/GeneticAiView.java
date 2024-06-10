package com.shepherdjerred.capstone.tui.view;

import com.shepherdjerred.capstone.ai.evaluator.EvaluatorWeights;
import com.shepherdjerred.capstone.ai.genetic.WeightsProblem;
import io.jenetics.DoubleGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import java.util.Optional;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class GeneticAiView implements View {

  private final Scanner scanner;

  @Override
  public Optional<View> display() {
    var problem = new WeightsProblem();

    final Engine<DoubleGene, Integer> engine = Engine
        .builder(problem)
        .build();

    final EvolutionStatistics<Integer, ?> statistics = EvolutionStatistics.ofNumber();

    final Genotype<DoubleGene> gt = engine.stream()
        .limit(100)
        .peek(statistics)
        .peek(result -> {
          var weights = problem.codec()
              .decoder()
              .apply(result.getBestPhenotype().getGenotype());
          log.info(weights);
        })
        .peek(r -> {
          log.info(statistics);
          System.out.println(statistics);
        })
        .collect(EvolutionResult.toBestGenotype());

    final EvaluatorWeights param = problem.decode(gt);
    System.out.println(String.format("Result: \t%s", param));
    log.info("== Result ==\n" + param);

    return Optional.of(new MainMenuView(scanner));
  }
}
