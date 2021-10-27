package com.example.learn_demo.db.greendao.bean;


import android.graphics.Bitmap;

import com.example.learn_demo.db.greendao.converter.AddressConverter;
import com.example.learn_demo.db.greendao.converter.DateConverter;
import com.example.learn_demo.db.room.entity.Address;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * @author vianhuang
 * @date 2021/10/25 5:40 下午
 * <p>
 * greendao注释说明：
 * @Entity 用于标识这是一个需要Greendao帮我们生成代码的bean
 * @Id 标明主键，括号里可以指定是否自增, 这里的@Id 标记，只能使用Long 作为类型。
 * @Property 用于设置属性在数据库中的列名（默认不写就是保持一致）
 * @NotNull 非空
 * @Transient 标识这个字段是自定义的不会创建到数据库表里
 * @Convert 复杂数据类型需要转换为普通数据类型才能存储
 */
@Entity
public class User {
    @Id
    private long uid;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "age")
    private int age;

    @Transient
    public Bitmap picture;

    @Convert(converter = AddressConverter.class, columnType = String.class)
    @Property(nameInDb = "address")
    public Address address;

    @Convert(converter = DateConverter.class, columnType = Long.class)
    public Date birthday;

    @Generated(hash = 330078573)
    public User(long uid, String name, int age, Address address, Date birthday) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", picture=" + picture +
                ", address=" + address +
                ", birthday=" + birthday +
                '}';
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
