package pinomaker.mbti.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pinomaker.mbti.common.domain.BaseTimeEntity;
import pinomaker.mbti.common.dto.UserAuthority;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_idx")
    private Long idx;

    @Column(name ="user_id")
    private String id;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_mbti")
    private String mbti;

    @Column(nullable = false)
    private UserAuthority authority;
}
