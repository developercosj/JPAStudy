package study;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name="member_test")
public class MemberTest {


    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    private Integer age;

    // Enum 사용
    // 무조건 String 으로 사용하기 ( ORDINAL 은 문제 발생소지가 큼)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // Blob, Clob 타입생성
    @Lob
    private String description;


    public MemberTest(){}

    public MemberTest(Long id, String name, Integer age, RoleType roleType, Date createDate, Date lastModifiedDate, String description) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.roleType = roleType;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
