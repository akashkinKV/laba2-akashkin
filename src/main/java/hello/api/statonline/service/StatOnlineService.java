package hello.api.statonline.service;

import hello.api.statonline.model.StatOnlineInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface StatOnlineService {


    @Nullable
    void createStatistic(@Nonnull UUID uuid, @Nonnull String vk);

    List<StatOnlineInfo> findDayStatsByUUID(@Nonnull UUID uuid);

    List<StatOnlineInfo> findWeekStatsByUUID(@Nonnull UUID uuid);

    List<StatOnlineInfo> findMonthStatsByUUID(@Nonnull UUID uuid);

    List<StatOnlineInfo> findAllStatsByUUID(@Nonnull UUID uuid);

    StatOnlineInfo refreshStatistic(@Nonnull UUID uuid, @Nonnull String vk);


    @Nullable
    void deleteStats(@Nonnull UUID uuid);

}
