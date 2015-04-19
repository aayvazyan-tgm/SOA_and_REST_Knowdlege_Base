package model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by helmuthbrunner on 16/04/15.
 */

//@NamedQueries({
//        @NamedQuery(
//                name= "relatedEntries",
//                query = "from Tag t join t."
//        )
//})

@Entity
@Table(name= "Tag")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name= "tag_id")
    private Integer id;

    @ManyToMany(mappedBy="tags", fetch = FetchType.LAZY, targetEntity = Eintrag.class)
    private Set<String> tag= new HashSet<String>();

    public Tag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTags(Set<String> tag) {
        this.tag = tag;
    }

    public Set<String> getTags() {
        return tag;
    }

    public List<Eintrag> getEintraege() {
        //TODO Helmuth :) return the related Eintraege
        return null;
    }
}
