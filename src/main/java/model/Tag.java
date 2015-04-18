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
    @Column
    @GeneratedValue
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, targetEntity = Eintrag.class)
    @JoinColumn(name= "tag_eintrag")
    private Set<String> tag= new HashSet<String>(0);

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
