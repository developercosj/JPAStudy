package tests.relation;

import javax.persistence.*;

@Entity
public class MemberRelationTest {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    //@Column(name = "team_id")
    //private Long teamId;


    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamRelationTest team;


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

    public TeamRelationTest getTeam() {
        return team;
    }

    public void setTeam(TeamRelationTest team) {
        this.team = team;
    }



}
