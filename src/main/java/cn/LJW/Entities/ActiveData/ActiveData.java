package cn.LJW.Entities.ActiveData;

public class ActiveData {
    String userID;
    long support;
    long against;
    int inform;
    int reported;
    long follow;
    long fans;
    int post;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getSupport() {
        return support;
    }

    public void setSupport(long support) {
        this.support = support;
    }

    public long getAgainst() {
        return against;
    }

    public void setAgainst(long against) {
        this.against = against;
    }

    public int getInform() {
        return inform;
    }

    public void setInform(int inform) {
        this.inform = inform;
    }

    public int getReported() {
        return reported;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }

    public long getFollow() {
        return follow;
    }

    public void setFollow(long follow) {
        this.follow = follow;
    }

    public long getFans() {
        return fans;
    }

    public void setFans(long fans) {
        this.fans = fans;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "ActiveData{" +
                "userID='" + userID + '\'' +
                ", support=" + support +
                ", against=" + against +
                ", inform=" + inform +
                ", reported=" + reported +
                ", follow=" + follow +
                ", fans=" + fans +
                ", post=" + post +
                '}';
    }
}
