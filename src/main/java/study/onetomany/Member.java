package study.onetomany;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    // 일대다 양방향을 설정하기 위한 우회적 방법
    // 읽기 전용으로 (insertable, updatable 설정이 없으면 문제가 생김 양방향이나 FK 를 어디 객체에서 설정할지 알 수 없음)
    // @ManyToOne
    // @JoinColumn(name = "team_id", insertable = false, updatable = false)
    // private Team team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




}
