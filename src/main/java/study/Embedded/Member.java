package study.Embedded;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column(name="user_name")
    private String name;

    @Embedded
    private Period workPeriod;


    // 값 타입
    // Member 테이블에 Address 필드값도 같이 들어감
    @Embedded
    private Address homeAddress;

    // 값 타입 컬렉션 생성
    // 테이블 생성됨
    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns =  @JoinColumn(name="member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="ADDRESS" , joinColumns =  @JoinColumn(name="member_id"))
    private List<Address> addressHistory = new ArrayList<>();


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


    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }


    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

}
