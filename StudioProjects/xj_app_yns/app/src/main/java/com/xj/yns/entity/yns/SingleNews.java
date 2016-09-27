package com.xj.yns.entity.yns;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class SingleNews {

    /**
     * id : 853
     * pic : /u/cms/www/201609/06143956pd4y.jpg
     * title : 生物刺激素火了 更要科学理性看待
     * release_date : 2016-09-06 15:21:29
     */

    private int id;
    private String pic;
    private String title;
    private String release_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
