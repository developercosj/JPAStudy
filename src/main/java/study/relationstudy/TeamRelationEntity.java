package study.relationstudy;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamRelationEntity {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberRelationEntity> members = new ArrayList<>();

    public void addMember (MemberRelationEntity member) {
        member.setTeam(this);
        members.add(member);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberRelationEntity> getMembers() {
        return members;
    }

    public void setMembers(List<MemberRelationEntity> members) {
        this.members = members;
    }
}
