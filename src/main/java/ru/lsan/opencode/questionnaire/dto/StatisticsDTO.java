package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class StatisticsDTO {

    Long id;

    String username;

    Integer rate;

    public static StatisticsDTO fromStatisticsEntity(StatisticsEntity entity) {
        return StatisticsDTO.builder()
                .username(entity.getUser().getLogin())
                .rate(entity.getRate())
                .build();
    }

    public static List<StatisticsDTO> fromStatisticsEntityList(List<StatisticsEntity> entities) {
        List<StatisticsDTO> statisticsDTOList = new ArrayList<>(entities.size());
        for (StatisticsEntity e : entities) {
            statisticsDTOList.add(fromStatisticsEntity(e));
        }
        return statisticsDTOList;
    }

}
