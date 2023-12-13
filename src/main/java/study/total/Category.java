package study.total;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;


    // 상위 카테고리 개념
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();



}
