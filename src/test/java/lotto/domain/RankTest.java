package lotto.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    void 번호_6개_일치시_1등() {
        Rank rank = Rank.determineRank(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000L);
    }

    @Test
    void 번호_5개_일치하면서_보너스볼_일치시_2등() {
        Rank rank = Rank.determineRank(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000L);
    }

    @Test
    void 번호_5개_일치하면서_보너스볼_불일치시_3등() {
        Rank rank = Rank.determineRank(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000L);
    }

    @Test
    void 번호_4개_일치시_4등() {
        Rank rank = Rank.determineRank(4, false);

        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000L);
    }

    @Test
    void 번호_3개_일치시_5등() {
        Rank rank = Rank.determineRank(3, false);

        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000L);
    }

    @Test
    void 번호_2개_이하_일치시_미당첨() {
        assertThat(Rank.determineRank(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.determineRank(1, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.determineRank(0, false)).isEqualTo(Rank.NONE);
    }

    @Test
    void 보너스볼은_5개_일치시에만_의미있음() {
        // 6개 일치 시 보너스볼 무관
        assertThat(Rank.determineRank(6, true)).isEqualTo(Rank.FIRST);

        // 4개 이하 일치 시 보너스볼 무관
        assertThat(Rank.determineRank(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.determineRank(3, true)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 번호가_5개_일치_할_때_보너스볼에_따라_결과가_다르다() {
        assertThat(Rank.determineRank(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.determineRank(5, true)).isEqualTo(Rank.SECOND);
    }
}