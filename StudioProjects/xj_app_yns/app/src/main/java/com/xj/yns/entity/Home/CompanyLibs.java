package com.xj.yns.entity.Home;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class CompanyLibs {


    /**
     * id : 583
     * name : 余丽君调研蔡甸区农村能源项目
     * pic : /images/7e082ea4-eaf2-49d3-96b8-f088f10d9d7e.png
     */

    private int id;
    private String name;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "CompanyLibs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
