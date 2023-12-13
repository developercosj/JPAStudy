package study.relationstudy;

import javax.persistence.*;

//@Entity
public class MemberRelationEntity {

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
    private TeamRelationEntity team;


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

    public TeamRelationEntity getTeam() {
        return team;
    }

    public void setTeam(TeamRelationEntity team) {
        this.team = team;
    }

    public void changeTeam(TeamRelationEntity team) {
        this.team = team;
        team.getMembers().add(this);
    }



}
