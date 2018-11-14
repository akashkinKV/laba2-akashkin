package hello.api.statonline.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "statonline")
public class StatOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "online")
    private boolean online;

    @Column(name = "mobile")
    private boolean mobile;

    @Column(name = "uid")
    private UUID uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatOnline that = (StatOnline) o;
        return online == that.online &&
                mobile == that.mobile &&
                Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, online, mobile, uid);
    }

    @Override
    public String toString() {
        return "StatOnline{" +
                "id=" + id +
                ", date=" + date +
                ", online=" + online +
                ", mobile=" + mobile +
                ", uid=" + uid +
                '}';
    }
}