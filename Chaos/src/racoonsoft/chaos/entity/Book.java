package racoonsoft.chaos.entity;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Book
{
    private ArrayList<Chapter> chapters;
    private ArrayList<String> keywords;
    private String name;
    private String author;
    private Long id;
}
