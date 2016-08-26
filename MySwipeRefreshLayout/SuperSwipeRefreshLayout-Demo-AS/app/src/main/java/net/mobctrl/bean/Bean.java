package net.mobctrl.bean;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Bean {
    private int page;
    private String title;

    public Bean(int page, String title) {
        this.page = page;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
