package pinomaker.mbti.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pinomaker.mbti.common.domain.BaseTimeEntity;
import javax.persistence.*;


@Entity
@Table(name = "tbl_answer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_idx")
    private Long idx;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "guest_mbti")
    private String guestMbti;

    @Column(name = "mbti")
    private String mbti;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_idx")
    private User user;
}
