# BDD in Action - Capítulo 2 - Train Timetables

Este repositório contém o exemplo prático do **Capítulo 2** do livro **BDD in Action** de John Ferguson Smart. O objetivo deste projeto é trabalhar com o exemplo do sistema de horários de trens, utilizando técnicas de **BDD (Behavior-Driven Development)**.

## Modificações Realizadas

Foram feitas diversas modificações e melhorias em relação ao repositório original para garantir a compatibilidade e expandir as funcionalidades do projeto. As principais alterações incluem:

### 1. Adição do arquivo `serenity.properties`
Para permitir a geração correta dos relatórios do JBehave, foi adicionado o arquivo `serenity.properties` com as seguintes configurações:

```
serenity.project.name=Train Timetables
webdriver.timeouts.implicitlywait=5000
webdriver.wait.for.timeouts=3000
serenity.take.screenshots=FOR_EACH_ACTION
serenity.full.page.screenshot.strategy=true
serenity.reports.show.step.details=true
```

### 2. Modificações na classe `ItineraryService`
A classe `ItineraryService` foi modificada para melhorar a lógica de cálculo dos horários e incluir um novo método `estimatedArrivalTime()`. As principais alterações incluem:
- Adição de um `Map<String, Integer>` para armazenar os tempos estimados de viagem.
- Alterado o método `findNextDepartures()` para aceitar objetos `Line` em vez de strings.
- Adição do método `estimatedArrivalTime(ScheduledService scheduled)`, que calcula o tempo estimado de chegada com base nos tempos de viagem pré-definidos.

### 3. Atualização da classe `InMemoryTimeTable`
Foram adicionados dois novos métodos:
- `scheduleService(Line line, ScheduledService scheduled)`: permite agendar serviços de trem.
- `getLineForScheduledService(ScheduledService scheduled)`: permite recuperar a linha associada a um serviço programado, necessário para o cálculo de tempo estimado de chegada.

### 4. Adição das classes `Line` 
- A classe `Line` existia no repositório antigo do autor, mas foi removida no repositório novo. Ela foi reintroduzida.

### 5. Atualização da classe `ScheduledService` e `LocalTimes`
Em `ScheduledService`, foram adicionados novos métodos para facilitar a manipulação de serviços de trem:
- `addTime(String time)`: adiciona um novo horário de partida.
- `getDeparture()`, `getDestination()`: retorna informações sobre a estação de origem e destino.
- Novo construtor `ScheduledService(String from, String to)`, necessário para integração com as classes de steps.

Já na classe `LocalTimes`:
- A classe foi movida para um pacote dentro de `main`, pois anteriormente estava dentro de `test`.
- Foi reformulada para incluir novos métodos:
  - `localTimesFrom(String listOfDepartureTimes)`: converte uma string separada por vírgulas em uma lista de `LocalTime`.
  - `at(String time)`: converte uma string de horário no formato "HH:mm" para um objeto `LocalTime`.

### 6. Reorganização dos pacotes do projeto
Os pacotes do projeto foram reorganizados de forma mais estruturada para melhorar a manutenção do código.

### 7. Atualização da classe de testes de aceitação
A classe de testes:

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/", glue="manning.bddinaction")
public class AcceptanceTestSuite {}
```

Foi substituída por:

```java
package com.bddinaction.chapter2.jbehave;

import net.serenitybdd.jbehave.SerenityStories;

public class TestStoryRunner extends SerenityStories {}
```

Isso reflete a mudança para JBehave.

### 8. Adição de novas classes de Steps
Foram adicionadas duas novas classes de steps:
- `ArrivalTimesSteps`: criada para implementar os testes de tempos de chegada, baseada na story do livro.
- `OptimalItinerarySteps`: uma classe mencionada no livro, mas que não estava no repositório original.

Também foram adicionados dois arquivos `.story` ao diretório `resources/stories/`:
- `find_next_train_departures.story`
- `calculate_estimated_arrival_times.story`

### 9. Adição de testes unitários em Groovy
Foram adicionados três arquivos `.groovy` para testes unitários baseados nos exemplos do livro, que não estavam presentes no repositório original.

## Repositório Original
O repositório original pode ser encontrado [aqui](https://github.com/bdd-in-action/chapter-2/tree/master/train-timetables).

## Dependências e Versões Utilizadas
As seguintes dependências foram utilizadas neste projeto:
- **Serenity Core**: 3.0.5
- **Serenity JUnit**: 3.0.5
- **Serenity JBehave**: 1.46.0
- **JUnit**: 4.12
- **Spock Framework**: 2.0-M4-groovy-3.0
- **Groovy**: 3.0.6
- **AssertJ**: 3.8.0
- **Hamcrest**: 1.3

Essas dependências foram aplicadas para implementar os cenários de BDD, realizar testes unitários e integrar o Serenity para facilitar a execução e verificação dos testes.