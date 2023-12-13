package study.total;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status;

    // order 와 양방향 원할 떄 추가
    @OneToOne(mappedBy = "delivery")
    private Order order;


}
