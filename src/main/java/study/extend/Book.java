package study.extend;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("Book")
public class Book extends Item {
    private String author;
    private String isbn;

}
