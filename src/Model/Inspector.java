package Model;

public class Inspector {
    int inspectorID; //primary key
    String fullName;
    String post;
    String inspectorRank;
    //boolean result;

    public void setInspectorID(int inspectorID) {
        this.inspectorID = inspectorID;
    }

    public int getInspectorID() {
        return inspectorID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setInspectorRank(String rank) {
        this.inspectorRank = rank;
    }

    public String getInspectorRankRank() {
        return inspectorRank;
    }
}
