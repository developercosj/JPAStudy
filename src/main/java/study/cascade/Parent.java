package study.cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    private String name;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();



    // 편의메서드
    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }




}
