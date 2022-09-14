package com.server.testplatform.testplatform.model.upload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.testplatform.testplatform.model.UserModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uploadimg")
public class UploadImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long upload_id;

    @Column
    private long size;

    @Column
    private String name;

    @Column
    private String path;

    @Column(name = "upload_data")
    private LocalDateTime createdata;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id"  , insertable = true )
    private UserModel user;


    public Long getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(Long upload_id) {
        this.upload_id = upload_id;
    }

    public LocalDateTime getCreatedata() {
        return createdata;
    }

    public void setCreatedata(LocalDateTime createdata) {
        this.createdata = createdata;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
