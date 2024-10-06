package bookstore.dto;

import java.util.List;

public class BookObject {

    private String userId;
    private List<Isbn> collectionofIsbns;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Isbn> getCollectionofIsbns() {
        return collectionofIsbns;
    }

    public void setCollectionofIsbns(List<Isbn> collectionofIsbns) {
        this.collectionofIsbns = collectionofIsbns;
    }

    public BookObject(String userId, List<Isbn> collectionofIsbns) {
        this.userId = userId;
        this.collectionofIsbns = collectionofIsbns;


    }


}
