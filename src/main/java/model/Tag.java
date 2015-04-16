package model;


import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by helmuthbrunner on 16/04/15.
 */
@Entity
@Table(name= "Tag")
public class Tag {

    @Id
    private String tag_name;

    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, targetEntity = Eintrag.class)
    @JoinColumn(name= "tag_eintrag")
    private Set<String> tag;

    public Tag() {
    }

    public Set<String> getTags() {
        return tag;
    }

    public void setTags(Set<String> tag) {
        this.tag = tag;
    }

    public String getName() {
        return tag_name;
    }

    public void setName(String tag_name) {
        this.tag_name = tag_name;
    }

    public List<Eintrag> getEintraege(){
        //TODO Helmuth :) return the related Einträge
        return null;
    }
}
