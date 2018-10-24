package hello.api.statistic.service;

import hello.api.statistic.model.StatisticInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface StatisticService {










    @Nullable
    void createStatistic(@Nonnull String uuid,@Nonnull String vk);


    List<StatisticInfo>  findWeekStatsByUUID(@Nonnull String uuid);
    List<StatisticInfo>  findMonthStatsByUUID(@Nonnull String uuid);
    List<StatisticInfo>  findAllStatsByUUID(@Nonnull String uuid);

    StatisticInfo refreshStatistic(@Nonnull String uuid,@Nonnull String vk);





}
